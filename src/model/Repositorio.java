package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Repositorio implements Serializable {

	private Map<String, Perfil> perfis;

	public Repositorio() {
		this.perfis = new HashMap<>();
		carregarPerfisJson();
		System.out.println("testando a ");
	}

	// Cadastra um novo perfil
	public void cadastrar(Perfil perfil) throws Exception {
		if (perfis.containsKey(perfil.getUsuario())) {
			throw new Exception("Usuário já cadastrado.");
		}
		perfis.put(perfil.getUsuario(), perfil);
		salvarPerfisJson();
	}

	// Busca um perfil pelo nome de usuário
	public Perfil buscar(String usuario) {
		return perfis.get(usuario);
	}

	public void salvarPerfisJson() {
		try (FileWriter file = new FileWriter("perfis.json")) {
			StringBuilder jsonBuilder = new StringBuilder();
			jsonBuilder.append("{\n\"perfis\": [\n");

			// Iterar sobre os perfis e criar uma string JSON manualmente
			for (Perfil perfil : perfis.values()) {
				jsonBuilder.append("{\n")
						.append("\"usuario\": \"").append(perfil.getUsuario()).append("\",\n")
						.append("\"ativo\": ").append(perfil.isAtivo()).append(",\n")
						.append("\"tweets\": [");

				// Adiciona os tweets
				for (Tweet tweet : perfil.getTimeline()) {
					jsonBuilder.append("\"").append(tweet.getMensagem()).append("\",");
				}
				// Remove última vírgula, se existir, e fecha o array de tweets
				if (!perfil.getTimeline().isEmpty()) {
					jsonBuilder.setLength(jsonBuilder.length() - 1);
				}
				jsonBuilder.append("],\n");

				// Adiciona os perfis seguidos
				jsonBuilder.append("\"seguidos\": [");
				for (Perfil seguido : perfil.getSeguidos()) {
					jsonBuilder.append("\"").append(seguido.getUsuario()).append("\",");
				}
				if (!perfil.getSeguidos().isEmpty()) {
					jsonBuilder.setLength(jsonBuilder.length() - 1);
				}
				jsonBuilder.append("],\n");

				// Adiciona os perfis que estão seguindo este perfil
				jsonBuilder.append("\"seguindo\": [");
				for (Perfil seguindo : perfil.getSeguidores()) {
					jsonBuilder.append("\"").append(seguindo.getUsuario()).append("\",");
				}
				if (!perfil.getSeguidores().isEmpty()) {
					jsonBuilder.setLength(jsonBuilder.length() - 1);
				}
				jsonBuilder.append("]\n},\n");
			}

			if (!perfis.isEmpty()) {
				jsonBuilder.setLength(jsonBuilder.length() - 2);
			}
			jsonBuilder.append("\n]\n}");

			// Escreve no arquivo JSON
			file.write(jsonBuilder.toString());
			System.out.println("Perfis salvos em JSON com sucesso!");

		} catch (IOException e) {
			System.err.println("Erro ao salvar perfis: " + e.getMessage());
		}
	}

	public void carregarPerfisJson() {
		try (BufferedReader br = new BufferedReader(new FileReader("perfis.json"))) {
			StringBuilder jsonBuilder = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				jsonBuilder.append(line);
			}
			String json = jsonBuilder.toString();

			String[] perfisArray = json.split("\\{\\s*\"usuario\":");

			for (int i = 1; i < perfisArray.length; i++) {
				String perfilJson = perfisArray[i];
				String usuario = perfilJson.split("\"")[1];
				boolean ativo = perfilJson.contains("\"ativo\": true");

				// Cria o perfil
				Perfil perfil = new Perfil(usuario);
				perfil.setAtivo(ativo);

				// Carrega os tweets
				if (perfilJson.contains("\"tweets\":")) {
					String tweetsSection = perfilJson.split("\"tweets\": \\[")[1].split("]")[0];
					String[] tweets = tweetsSection.split("\",\"");
					for (String tweetTexto : tweets) {
						tweetTexto = tweetTexto.replaceAll("\"", "").trim();
						if (!tweetTexto.isEmpty()) {
							perfil.postarTweet(new Tweet(tweetTexto));
						}
					}
				}

				// Carrega os seguidos
				if (perfilJson.contains("\"seguidos\":")) {
					String seguidosSection = perfilJson.split("\"seguidos\": \\[")[1].split("]")[0];
					String[] seguidos = seguidosSection.split("\",\"");
					for (String seguidoUsuario : seguidos) {
						seguidoUsuario = seguidoUsuario.replaceAll("\"", "").trim();
						if (!seguidoUsuario.isEmpty()) {
							Perfil seguidoPerfil = buscar(seguidoUsuario);  // Busca no repositório
							if (seguidoPerfil != null) {
								perfil.seguir(seguidoPerfil);
							}
						}
					}
				}

				// Carrega quem está seguindo o perfil atual
				if (perfilJson.contains("\"seguindo\":")) {
					String seguindoSection = perfilJson.split("\"seguindo\": \\[")[1].split("]")[0];
					String[] seguindo = seguindoSection.split("\",\"");
					for (String seguindoUsuario : seguindo) {
						seguindoUsuario = seguindoUsuario.replaceAll("\"", "").trim();
						if (!seguindoUsuario.isEmpty()) {
							Perfil seguindoPerfil = buscar(seguindoUsuario);  // Busca no repositório
							if (seguindoPerfil != null) {
								seguindoPerfil.seguir(perfil);  // O perfil atual está sendo seguido por outros perfis
							}
						}
					}
				}

				// Adiciona o perfil ao repositório
				perfis.put(perfil.getUsuario(), perfil);
			}
			System.out.println("Perfis carregados com sucesso!");

		} catch (IOException e) {
			System.err.println("Erro ao carregar perfis: " + e.getMessage());
		}
	}
}

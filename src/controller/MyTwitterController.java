package controller;

import model.Login;
import model.Perfil;
import model.Tweet;
import model.Repositorio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Vector;

public class MyTwitterController {
	private Repositorio repositorio;

	public MyTwitterController(Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	public void criarPerfil(String usuario) throws Exception {
		Perfil perfil = new Perfil(usuario);
		repositorio.cadastrar(perfil);
	}

	public void postarTweet(String usuario, String mensagem) throws Exception {
		Perfil perfil = repositorio.buscar(usuario);
		if (perfil == null) {
			throw new Exception("Usuário não encontrado.");
		}
		Tweet tweet = new Tweet(mensagem);
		perfil.postarTweet(tweet);
		repositorio.salvarPerfisJson();
	}

	public Vector<Tweet> verTimeline(String usuario) throws Exception {
		Perfil perfil = repositorio.buscar(usuario);
		if (perfil == null) {
			throw new Exception("Usuário não encontrado.");
		}
		return perfil.getTimeline();
	}

	public void seguir(String seguidor, String seguido) throws Exception {
		Perfil perfilSeguidor = repositorio.buscar(seguidor);
		Perfil perfilSeguido = repositorio.buscar(seguido);

		if (perfilSeguidor == null || perfilSeguido == null) {
			throw new Exception("Um ou mais perfis não foram encontrados.");
		}
		perfilSeguidor.seguir(perfilSeguido);
	}

	public Vector<Perfil> verSeguindo(String usuario) throws Exception {
		Perfil perfil = repositorio.buscar(usuario);
		if (perfil == null) {
			throw new Exception("Usuário não encontrado.");
		}
		return perfil.getSeguidos();
	}

	// Adicionar login ao arquivo
	public void adicionarLogin(String usuario, String senha) throws IOException {

		Login login = new Login(usuario, senha);
		String linha = login.toString() + "\n";
		Files.write(Paths.get("Logins.txt"), linha.getBytes(), StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}

	// Validar Login
	public boolean verificarLogin(String usuario, String senha) throws IOException {

		List<String> linhas = Files.readAllLines(Paths.get("Logins.txt"));

		for (String linha : linhas) {
			String[] partes = linha.split(":");
			String usuarioArmazenado = partes[0];
			String senhaArmazenada = partes[1];

			if (usuarioArmazenado.equals(usuario) && senhaArmazenada.equals(senha)) {
				return true; // Login válido
			}
		}
		return false; // Login inválido
	}
}

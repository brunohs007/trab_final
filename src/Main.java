import java.util.Scanner;
import java.util.Vector;

import Principal.Perfil;
import Principal.Tweet;
import Restriçoes.MFPException;
import Restriçoes.PDException;
import Restriçoes.PEException;
import Restriçoes.PIException;
import Restriçoes.SIException;
import Interfaces.MyTwitter;
import Interfaces.Repositorio;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Repositorio rep = new Repositorio();
		MyTwitter twitter = new MyTwitter(rep);
		boolean continuar = true;

		while (continuar) {
			Perfil user = null;
			System.out.println("Digite o nome de usuário:");
			String usuario = sc.nextLine();
			user = new Perfil(usuario);
			try {
				twitter.criarPerfil(user);
			} catch (PEException e) {
				e.printStackTrace();
			}
			user.setAtivo(true);

			System.out.println("Usuário " + user.getUsuario() + " criado com sucesso!\n");

			System.out.println("Deseja criar outro usuário? (s/n)");
			String resposta = sc.nextLine();
			if (!resposta.equalsIgnoreCase("s")) {
				continuar = false;
			}
		}

		continuar = true;

		while (continuar) {
			System.out.println("\nEscolha uma opção:");
			System.out.println("1. Seguir um usuário");
			System.out.println("2. Tweetar");
			System.out.println("3. Ler tweets de quem você segue");
			System.out.println("4. Ver lista de usuários que você segue");
			System.out.println("5. Sair");
			int opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
				case 1:
					System.out.println("Digite o nome do usuário que deseja seguir:");
					String seguidor = sc.nextLine();
					System.out.println("Digite o nome do usuário a ser seguido:");
					String seguido = sc.nextLine();

					try {
						twitter.seguir(seguidor, seguido);
						System.out.println(seguidor + " agora segue " + seguido);
					} catch (PIException | PDException | SIException e) {
						e.printStackTrace();
					}
					break;

				case 2:
					System.out.println("Digite seu nome de usuário:");
					String autor = sc.nextLine();
					System.out.println("Digite o tweet:");
					String conteudo = sc.nextLine();
					Tweet tweet = new Tweet();
					tweet.setMensagem(conteudo);

					try {
						twitter.tweetar(autor, tweet.getMensagem());
						System.out.println("Tweet enviado com sucesso!");
					} catch (MFPException e) {
						e.printStackTrace();
					} catch (PIException e) {
						throw new RuntimeException(e);
					}
					break;

				case 3:
					System.out.println("Digite seu nome de usuário:");
					String leitor = sc.nextLine();

					try {
						Vector<Tweet> timeline = twitter.timeline(leitor);
						if (timeline.isEmpty()) {
							System.out.println("Nenhum tweet encontrado.");
						} else {
							System.out.println("Tweets de quem você segue:");
							for (Tweet t : timeline) {
								System.out.println(t.getUsuario() + ": " + t.getMensagem());
							}
						}
					} catch (PIException | PDException e) {
						e.printStackTrace();
					}
					break;

				case 4:
					System.out.println("Digite seu nome de usuário:");
					String usuario = sc.nextLine();

					try {
						Vector<Perfil> seguindo = twitter.seguidos(usuario);
						if (seguindo.isEmpty()) {
							System.out.println("Você não está seguindo ninguém.");
						} else {
							System.out.println("Você está seguindo:");
							for (Perfil p : seguindo) {
								System.out.println(p.getUsuario());
							}
						}
					} catch (PIException | PDException e) {
						e.printStackTrace();
					}
					break;

				case 5:
					continuar = false;
					break;

				default:
					System.out.println("Opção inválida. Tente novamente.");
					break;
			}
		}

		sc.close();
	}
}

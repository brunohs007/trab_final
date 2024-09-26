package view;

import controller.MyTwitterController;
import model.Tweet;
import model.Repositorio;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class TwitterView {
    private MyTwitterController controller;
    private Scanner scanner;

    public TwitterView() {
        Repositorio repositorio = new Repositorio();
        this.controller = new MyTwitterController(repositorio);
        this.scanner = new Scanner(System.in);
    }

    public void telaInicial() throws IOException {
        while (true) {
            System.out.println("\nBem-vindo ao Twitter!");
            System.err.println("------------------------");
            System.out.println("1. Login:");
            System.out.println("2. Criar Perfil");
            System.out.println("0. Fechar");
            System.out.println("-----------------------");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    login();
                    break;
                case 2:
                    criarPerfil();
                    break;
                case 0:
                    System.out.println("Desligando...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void login() throws IOException {
        while (true) {
            System.out.println("\nLogin");
            System.err.println("------------------------");
            System.out.print("Usuário: ");
            String nomeUsuario = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            boolean loginValido = controller.verificarLogin(nomeUsuario, senha);

            if (loginValido) {
                exibirMenu(nomeUsuario);
                return;
            } else {
                System.out.println("Nome de usuário ou senha incorretos.");
                telaInicial();
            }
            // talvez tela inicial aqui ou uma forma de volta a ela.
        }
    }

    public void exibirMenu(String nomeUsuario) {
        while (true) {
            System.out.println("\nBem-vindo(a) " + nomeUsuario);
            System.out.println("-----------------------");
            // Criar perfil foi removida daqui
            System.out.println("O que deseja fazer hoje?\n");
            System.out.println("1. Postar tweet");
            System.out.println("2. Ver timeline");
            System.out.println("3. Seguir usuário");
            System.out.println("4. Ver quem está seguindo");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("-----------------------");

            switch (opcao) {
                case 1:
                    postarTweet(nomeUsuario);
                    break;
                case 2:
                    verTimeline(nomeUsuario);
                    break;
                case 3:
                    seguirUsuario(nomeUsuario);
                    break;
                case 4:
                    verSeguindo(nomeUsuario);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        }
    }

    private void criarPerfil() {
        System.out.println("\nCriação de Perfil");
        System.err.println("------------------------");
        System.out.print("Nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Crie uma senha: ");
        String senha = scanner.nextLine();
        try {
            ///
            controller.adicionarLogin(nomeUsuario, senha);
            ///
            controller.criarPerfil(nomeUsuario);
            System.out.println("\nPerfil criado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar perfil: " + e.getMessage());
        }
    }

    private void postarTweet(String nomeUsuario) {
        System.out.print("\nDigite o que está pensando, " + nomeUsuario + ": ");
        String mensagem = scanner.nextLine();
        try {
            controller.postarTweet(nomeUsuario, mensagem);
            System.out.println("Tweet postado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao postar tweet: " + e.getMessage());
        }
        System.err.println("------------------------");
    }

    private void verTimeline(String nomeUsuario) {
        System.err.println("\n------------------------");
        try {
            Vector<Tweet> timeline = controller.verTimeline(nomeUsuario);
            System.out.println("Timeline de " + nomeUsuario + ":");
            for (Tweet tweet : timeline) {
                System.out.println(tweet.getMensagem());
            }
        } catch (Exception e) {
            System.out.println("Erro ao ver timeline: " + e.getMessage());
        }
        System.err.println("------------------------");
    }

    private void seguirUsuario(String nomeUsuario) {
        System.out.print("\nDigite o nome do usuário a ser seguido: ");
        String seguido = scanner.nextLine();
        try {
            controller.seguir(nomeUsuario, seguido);
            System.out.println(nomeUsuario + " agora está seguindo " + seguido + "!");
        } catch (Exception e) {
            System.out.println("Erro ao seguir usuário: " + e.getMessage());
        }
        System.err.println("------------------------");
    }

    private void verSeguindo(String nomeUsuario) {
        System.err.println("\n------------------------");
        try {
            Vector<model.Perfil> seguindo = controller.verSeguindo(nomeUsuario);
            System.out.println(nomeUsuario + " está seguindo:");
            for (model.Perfil perfil : seguindo) {
                System.out.println(perfil.getUsuario());
            }
        } catch (Exception e) {
            System.out.println("Erro ao ver seguidores: " + e.getMessage());
        }
        System.err.println("------------------------");
    }
}

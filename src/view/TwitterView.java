package view;

import controller.MyTwitterController;
import model.Tweet;
import model.Repositorio;
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

    public void exibirMenu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Criar perfil");
            System.out.println("2. Postar tweet");
            System.out.println("3. Ver timeline");
            System.out.println("4. Seguir usuário");
            System.out.println("5. Ver quem está seguindo");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarPerfil();
                    break;
                case 2:
                    postarTweet();
                    break;
                case 3:
                    verTimeline();
                    break;
                case 4:
                    seguirUsuario();
                    break;
                case 5:
                    verSeguindo();
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
        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scanner.nextLine();
        try {
            controller.criarPerfil(nomeUsuario);
            System.out.println("Perfil criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar perfil: " + e.getMessage());
        }
    }

    private void postarTweet() {
        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scanner.nextLine();
        System.out.println("Digite o tweet:");
        String mensagem = scanner.nextLine();
        try {
            controller.postarTweet(nomeUsuario, mensagem);
            System.out.println("Tweet postado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao postar tweet: " + e.getMessage());
        }
    }

    private void verTimeline() {
        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scanner.nextLine();
        try {
            Vector<Tweet> timeline = controller.verTimeline(nomeUsuario);
            System.out.println("Timeline de " + nomeUsuario + ":");
            for (Tweet tweet : timeline) {
                System.out.println(tweet.getMensagem());
            }
        } catch (Exception e) {
            System.out.println("Erro ao ver timeline: " + e.getMessage());
        }
    }

    private void seguirUsuario() {
        System.out.println("Digite o nome do seguidor:");
        String seguidor = scanner.nextLine();
        System.out.println("Digite o nome do usuário a ser seguido:");
        String seguido = scanner.nextLine();
        try {
            controller.seguir(seguidor, seguido);
            System.out.println(seguidor + " agora está seguindo " + seguido + "!");
        } catch (Exception e) {
            System.out.println("Erro ao seguir usuário: " + e.getMessage());
        }
    }

    private void verSeguindo() {
        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scanner.nextLine();
        try {
            Vector<model.Perfil> seguindo = controller.verSeguindo(nomeUsuario);
            System.out.println(nomeUsuario + " está seguindo:");
            for (model.Perfil perfil : seguindo) {
                System.out.println(perfil.getUsuario());
            }
        } catch (Exception e) {
            System.out.println("Erro ao ver seguidores: " + e.getMessage());
        }
    }
}

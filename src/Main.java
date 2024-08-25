import java.util.Scanner;
import java.util.Vector;

import Principal.Perfil;
import Principal.PessoaFisica;
import Principal.PessoaJuridica;
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
	String usuario;
	int aux;
	Perfil user = null;
	Perfil user2 = null;
	Repositorio rep = new Repositorio();
	MyTwitter twitter = new MyTwitter(rep);
	String mensagem = null;

	System.out.println("Digite 1 para pessoa física:\nDigite 2 para pessoa jurídica:\n");
	aux = sc.nextInt();
	sc.nextLine();
	while(aux != 1 && aux !=2) {
	    System.out.println("Insira um valor valido:\n");
	    System.out.println("Digite 1 para pessoa física:\nDigite 2 para pessoa jurídica:\n");
	    aux = sc.nextInt();
	    sc.nextLine();
	}

	switch(aux) {
	case 1:
	    System.out.println("Digite o nome de usuario:\n");
	    usuario = sc.nextLine();
	    user = new PessoaFisica(usuario);
	    try {
		System.out.println("Informe o CPF:\n");
		long cpf = sc.nextLong();
		((PessoaFisica) user).setCpf(cpf);
		twitter.criarPerfil(user);
	    } catch (PEException e) {
		e.printStackTrace();
	    }
	    user.setAtivo(true);
	    break;
	case 2:
	    System.out.println("Digite o nome de usuario:\n");
	    usuario = sc.nextLine();
	    user = new PessoaJuridica(usuario);
	    try {
		System.out.println("Informe o CNPJ:\n");
		int cnpj = sc.nextInt();
		((PessoaJuridica) user).setCnpj(cnpj);
		twitter.criarPerfil(user);
	    } catch (PEException e) {
		e.printStackTrace();
	    }
	    user.setAtivo(true);
	    break;
	}

	System.out.println("Digite 1 para pessoa física:\nDigite 2 para pessoa jurídica:\n");
	aux = sc.nextInt();
	sc.nextLine();
	while(aux != 1 && aux !=2) {
	    System.out.println("Insira um valor valido:\n");
	    System.out.println("Digite 1 para pessoa física:\nDigite 2 para pessoa jurídica:\n");
	    aux = sc.nextInt();
	    sc.nextLine();
	}

	switch(aux) {
	case 1:
	    System.out.println("Digite o nome de usuario:\n");
	    usuario = sc.nextLine();
	    user2 = new PessoaFisica(usuario);
	    try {
		System.out.println("Informe o CPF:\n");
		int cpf = sc.nextInt();
		((PessoaFisica) user2).setCpf(cpf);
		twitter.criarPerfil(user2);
	    } catch (PEException e) {
		e.printStackTrace();
	    }

	    user2.setAtivo(true);
	    break;
	case 2:
	    System.out.println("Digite o nome de usuario:\n");
	    usuario = sc.nextLine();
	    user2 = new PessoaJuridica(usuario);
	    try {
		System.out.println("Informe o CNPJ:\n");
		int cnpj = sc.nextInt();
		((PessoaJuridica) user2).setCnpj(cnpj);
		twitter.criarPerfil(user2);
	    } catch (PEException e) {
		e.printStackTrace();
	    }
	    user2.setAtivo(true);
	    break;
	}

	try {
	    System.out.println("Função seguir:");
	    twitter.seguir(rep.buscar(user.getUsuario()).getUsuario(),rep.buscar(user2.getUsuario()).getUsuario());
	} catch (PIException e) {
	    e.printStackTrace();
	} catch (PDException e) {
	    e.printStackTrace();
	} catch (SIException e) {
	    e.printStackTrace();
	}

	try {
	    System.out.println("Função número de seguidores:");
	    int a = twitter.numeroSeguidores(rep.buscar(user2.getUsuario()).getUsuario());
	    System.out.println("Numero de seguidores de " + rep.buscar(user2.getUsuario()).getUsuario() + ": " + a + "\n");
	} catch (PIException e) {
	    e.printStackTrace();
	} catch (PDException e) {
	    e.printStackTrace();
	}

	try {
	    System.out.println("Função seguidores:");
	    Vector<Perfil> a = twitter.seguidores(rep.buscar(user2.getUsuario()).getUsuario());
	    System.out.println("seguidores de " + rep.buscar(user2.getUsuario()).getUsuario() + ":");
	    for(int i=0;i<a.size();i++) {
		System.out.println("  " + (i+1) + ":" + a.get(i).getUsuario()+"\n");
	    }
	} catch (PIException e) {
	    e.printStackTrace();
	} catch (PDException e) {
	    e.printStackTrace();
	}

	try {
	    System.out.println("Função seguidos:");
	    Vector<Perfil> a = twitter.seguidos(rep.buscar(user.getUsuario()).getUsuario());
	    System.out.println("seguidos de " + rep.buscar(user.getUsuario()).getUsuario() + ":");
	    for(int i=0;i<a.size();i++) {
		System.out.println("  " + (i+1) + ":" + a.get(i).getUsuario()+"\n");
	    }
	} catch (PIException e) {
	    e.printStackTrace();
	} catch (PDException e) {
	    e.printStackTrace();
	}

	try {
	    System.out.println(rep.buscar(user.getUsuario()).getUsuario() + " digite seu tweet\n");
	    sc.nextLine();
	    mensagem=sc.nextLine();
	    twitter.tweetar(rep.buscar(user.getUsuario()).getUsuario(), mensagem);

	} catch (PIException e) {
	    e.printStackTrace();
	} catch (MFPException e) {
	    e.printStackTrace();
	}

	try {
	    System.out.println(rep.buscar(user2.getUsuario()).getUsuario() + " digite seu tweet\n");
	    mensagem=sc.nextLine();
	    twitter.tweetar(rep.buscar(user2.getUsuario()).getUsuario(), mensagem);

	} catch (PIException e) {
	    e.printStackTrace();
	} catch (MFPException e) {
	    e.printStackTrace();
	}

	try {
	    System.out.println("Função Timeline do " + rep.buscar(user.getUsuario()).getUsuario() + ":");
	    Vector<Tweet> c	= twitter.timeline(rep.buscar(user.getUsuario()).getUsuario());
	    for(int i=0;i<c.size();i++) {
		System.out.println(c.get(i).getUsuario());
		System.out.println("  " + c.get(i).getMensagem()+"\n");
	    }
	} catch (PIException e) {
	    e.printStackTrace();
	} catch (PDException e) {
	    e.printStackTrace();
	}

	try {
	    System.out.println("Função Timeline do " + rep.buscar(user2.getUsuario()).getUsuario() + ":");
	    Vector<Tweet> c	= twitter.timeline(rep.buscar(user2.getUsuario()).getUsuario());
	    for(int i=0;i<c.size();i++) {
		System.out.println(c.get(i).getUsuario());
		System.out.println("  " + c.get(i).getMensagem()+"\n");
	    }
	} catch (PIException e) {
	    e.printStackTrace();
	} catch (PDException e) {
	    e.printStackTrace();
	}

	try {
	    System.out.println("Função Tweets do " + rep.buscar(user.getUsuario()).getUsuario() + ":");
	    Vector<Tweet> c	= twitter.tweets(rep.buscar(user.getUsuario()).getUsuario());
	    for(int i=0;i<c.size();i++) {
		System.out.println(c.get(i).getUsuario());
		System.out.println("  " + c.get(i).getMensagem()+"\n");
	    }
	} catch (PIException e) {
	    e.printStackTrace();
	} catch (PDException e) {

	    e.printStackTrace();
	}


	try {
	    System.out.println("Função Tweets do " + rep.buscar(user2.getUsuario()).getUsuario() + ":");
	    Vector<Tweet> c	= twitter.tweets(rep.buscar(user2.getUsuario()).getUsuario());
	    for(int i=0;i<c.size();i++) {
		System.out.println(c.get(i).getUsuario());
		System.out.println("  " + c.get(i).getMensagem()+"\n");
	    }
	} catch (PIException e) {
	    e.printStackTrace();
	} catch (PDException e) {
	    e.printStackTrace();
	}


	try {
	    System.out.println("Função Cancelar:");
	    twitter.cancelarPerfil(rep.buscar(user2.getUsuario()).getUsuario());
	} catch (PIException e) {
	    e.printStackTrace();
	} catch (PDException e) {
	    e.printStackTrace();
	}

	sc.close();

    }
}

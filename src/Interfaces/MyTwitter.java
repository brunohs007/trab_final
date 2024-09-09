package Interfaces;

import java.util.Vector;

import Principal.Perfil;
import Principal.Tweet;
import Restriçoes.SIException;
import Restriçoes.MFPException;
import Restriçoes.PDException;
import Restriçoes.PIException;
import Restriçoes.UJCException;
import Restriçoes.PEException;


public class MyTwitter implements ITwitter {

    private IRepositorioUsuario repositorio;

    public MyTwitter(IRepositorioUsuario repositorio) {
	super();
	this.repositorio = repositorio;
    }


    @Override
    public void criarPerfil(Perfil usuario) throws PEException {
	try {
	    repositorio.cadastrar(usuario);
	}
	catch (UJCException abc){
	    System.out.println("usuario ja cadastrado");
	    throw new PEException(usuario);
	}
    }


    @Override
    public void cancelarPerfil(String usuario) throws PIException, PDException {
		Perfil perfil = repositorio.buscar(usuario);
		if(usuario.equals(perfil.getUsuario())){
			if(perfil.isAtivo()){
				perfil.setAtivo(false);
				System.out.println("Perfil cancelado com sucesso!");
			}
			else {
			throw new PDException(perfil);
			}
		} else{
			throw new PIException(perfil);
		}
	}

    @Override
    public void tweetar(String usuario, String mensagem) throws PIException, MFPException {
		Perfil perfil = repositorio.buscar(usuario);
		if(perfil != null){
			if(mensagem.length()<=140 && mensagem.length() >0) {
			Tweet tweet = new Tweet();
			tweet.setMensagem(mensagem);
			tweet.setUsuario(usuario);
			perfil.addTweet(tweet);
			Vector<Perfil> seguidores  = perfil.getSeguidores();
			for (Perfil seguidor : seguidores) {
				seguidor.addTweet(tweet);
			}
			System.out.println("Tweet realizado com sucesso!\n");
			}
			else {
			throw new MFPException(mensagem);
			}
		} else {
			throw new PIException(repositorio.buscar(usuario));
		}
	}

	@Override
	public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
		Perfil perfil = repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				Vector<Tweet> timeline = new Vector<>();
				Vector<Perfil> seguindo = perfil.getSeguidos();

				for (Perfil seguido : seguindo) {
					timeline.addAll(seguido.getTimeline());
				}

				return timeline;
			} else {
				throw new PDException(perfil);
			}
		} else {
			throw new PIException(perfil);
		}
	}


	@Override
	public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
		Perfil perfil = repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				Vector<Tweet> tweetsDosSeguidos = new Vector<>();
				Vector<Perfil> seguidos = perfil.getSeguidos();
				for (Perfil seguido : seguidos) {
					tweetsDosSeguidos.addAll(seguido.getTimeline());
				}
				return tweetsDosSeguidos;
			} else {
				throw new PDException(perfil);
			}
		} else {
			throw new PIException(perfil);
		}
	}


	@Override
	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
		Perfil perfilSeguidor = repositorio.buscar(seguidor);
		Perfil perfilSeguido = repositorio.buscar(seguido);
		if (perfilSeguidor != null && perfilSeguido != null) {
			if (perfilSeguidor.isAtivo() && perfilSeguido.isAtivo()) {
				if (!seguidor.equals(seguido)) {
					perfilSeguido.addSeguidores(perfilSeguidor);
					perfilSeguidor.addSeguidos(perfilSeguido);
					System.out.println(seguidor + " seguiu " + seguido + "\n");
				} else {
					throw new SIException(perfilSeguido);
				}
			} else if (!perfilSeguidor.isAtivo()) {
				throw new PDException(perfilSeguidor);
			} else {
				throw new PDException(perfilSeguido);
			}
		} else if (perfilSeguidor == null) {
			throw new PIException(perfilSeguidor);
		} else {
			throw new PIException(perfilSeguido);
		}
	}


	@Override
	public int numeroSeguidores(String usuario) throws PIException, PDException {
		Perfil perfil = repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				return perfil.getSeguidores().size();
			} else {
				throw new PDException(perfil);
			}
		} else {
			throw new PIException(perfil);
		}
	}

	@Override
	public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
		Perfil perfil = repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				return perfil.getSeguidores();
			} else {
				throw new PDException(perfil);
			}
		} else {
			throw new PIException(perfil);
		}
	}


	@Override
	public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
		Perfil perfil = repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				return perfil.getSeguidos();
			} else {
				throw new PDException(perfil);
			}
		} else {
			throw new PIException(perfil);
		}
	}

}

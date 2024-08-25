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
	if(usuario.equals(repositorio.buscar(usuario).getUsuario())){
	    if(repositorio.buscar(usuario).isAtivo()){
		repositorio.buscar(usuario).setAtivo(false);
		System.out.println("Perfil cancelado com sucesso!");
	    }
	    else {
		throw new PDException(repositorio.buscar(usuario));
	    }
	}

	else{
	    throw new PIException(repositorio.buscar(usuario));
	}
    }


    @Override
    public void tweetar(String Usuario, String mensagem) throws PIException, MFPException {
	if(repositorio.buscar(Usuario) != null){
	    if(mensagem.length()<=140 && mensagem.length() >0) {
		Tweet weet = new Tweet();
		weet.setMensagem(mensagem);
		weet.setUsuario(Usuario);
		repositorio.buscar(Usuario).addTweet(weet);
		Vector<Perfil> a = repositorio.buscar(Usuario).getSeguidores();
		for(int i=0;i<a.size();i++) {
		    a.get(i).addTweet(weet);
		}
		System.out.println("Tweet realizado com sucesso!\n");
	    }
	    else {
		throw new MFPException(mensagem);
	    }
	}
	else {
	    throw new PIException(repositorio.buscar(Usuario));
	}
    }


    @Override
    public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
	if(repositorio.buscar(usuario) != null){
	    if(repositorio.buscar(usuario).isAtivo()){
		return repositorio.buscar(usuario).getTimeline();
	    }
	    else {
		throw new PDException(repositorio.buscar(usuario));
	    }
	}

	else{
	    throw new PIException(repositorio.buscar(usuario));
	}

    }


    @Override
    public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
	if(repositorio.buscar(usuario) != null){
	    if(repositorio.buscar(usuario).isAtivo()){
		Vector<Tweet> a = repositorio.buscar(usuario).getTimeline();
		Vector<Tweet> b = new Vector<Tweet>();
		for(int i=0;i<a.size();i++) {
		    if(a.elementAt(i).getUsuario() == usuario) {
			b.add(a.elementAt(i));
		    }
		}
		return b;
	    }
	    else {
		throw new PDException(repositorio.buscar(usuario));
	    }
	}

	else{
	    throw new PIException(repositorio.buscar(usuario));
	}
    }


    @Override
    public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
	if(repositorio.buscar(seguidor) != null) {
	    if(repositorio.buscar(seguido) != null) {
		if(repositorio.buscar(seguidor).isAtivo()) {
		    if(repositorio.buscar(seguido).isAtivo()) {
			if(seguidor != seguido) {
			    repositorio.buscar(seguido).addSeguidores(repositorio.buscar(seguidor));
			    repositorio.buscar(seguidor).addSeguidos(repositorio.buscar(seguido));
			    System.out.println(seguidor + " seguiu " + seguido +"\n");
			}
			else {
			    throw new SIException(repositorio.buscar(seguido));
			}
		    }
		    else {
			throw new PDException(repositorio.buscar(seguido));
		    }
		}
		else {
		    throw new PDException(repositorio.buscar(seguidor));
		}
	    }
	    else {
		throw new PIException(repositorio.buscar(seguido));
	    }
	}

	else{
	    throw new PIException(repositorio.buscar(seguidor));
	}
    }


    @Override
    public int numeroSeguidores(String usuario) throws PIException, PDException {
	if(repositorio.buscar(usuario) != null){
	    if(repositorio.buscar(usuario).isAtivo()){
		return repositorio.buscar(usuario).getSeguidores().size();
	    }
	    else {
		throw new PDException(repositorio.buscar(usuario));
	    }
	}

	else{
	    throw new PIException(repositorio.buscar(usuario));
	}
    }


    @Override
    public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
	if(repositorio.buscar(usuario) != null){
	    if(repositorio.buscar(usuario).isAtivo()){
		return repositorio.buscar(usuario).getSeguidores();
	    }
	    else {
		throw new PDException(repositorio.buscar(usuario));
	    }
	}

	else{
	    throw new PIException(repositorio.buscar(usuario));
	}
    }


    @Override
    public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
	if(repositorio.buscar(usuario) != null){
	    if(repositorio.buscar(usuario).isAtivo()){
		return repositorio.buscar(usuario).getSeguidos();
	    }
	    else {
		throw new PDException(repositorio.buscar(usuario));
	    }
	}

	else{
	    throw new PIException(repositorio.buscar(usuario));
	}
    }

}

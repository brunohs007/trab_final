package Principal;

import java.util.Vector;
import Principal.Tweet;

public class Perfil {

    private String usuario;
    private Vector<Perfil> seguidos;
    private Vector<Perfil> seguidores;
    private Vector<Tweet> timeline;
    private boolean ativo;

    public Perfil(String usuario) {
//	this.usuario = new String();
	this.usuario = usuario;
	ativo = true;
	timeline = new Vector<Tweet>();
	seguidores = new Vector<Perfil>();
	seguidos = new Vector<Perfil>();
    }

    public void addSeguidos(Perfil usuario) {
	seguidos.add(usuario);
    }

    public void addSeguidores(Perfil usuario) {
	seguidores.add(usuario);
    }
    public void addTweet(Tweet tweet) {
	timeline.add(tweet);
    }
    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }
    public Vector<Perfil> getSeguidos() {
	return seguidos;
    }

    public Vector<Perfil> getSeguidores() {
	return seguidores;
    }
    public Vector<Tweet> getTimeline() {
	return timeline;
    }
    public void setAtivo(boolean valor) {
	ativo = valor;
    }
    public boolean isAtivo() {
	return ativo;
    }

}

package model;

import java.util.Vector;

public class Perfil {
    private String usuario;
    private Vector<Perfil> seguidos;
    private Vector<Perfil> seguidores;
    private Vector<Tweet> timeline;
    private boolean ativo;

    public Perfil(String usuario) {
        this.usuario = usuario;
        this.ativo = true;
        this.timeline = new Vector<>();
        this.seguidores = new Vector<>();
        this.seguidos = new Vector<>();
    }

    // Método para seguir outro usuário
    public void seguir(Perfil seguido) {
        if (!seguidos.contains(seguido)) {
            seguidos.add(seguido);
            seguido.adicionarSeguidor(this);
        }
    }

    // Método para adicionar um seguidor ao perfil
    public void adicionarSeguidor(Perfil seguidor) {
        if (!seguidores.contains(seguidor)) {
            seguidores.add(seguidor);
        }
    }

    // Método para postar tweet
    public void postarTweet(Tweet tweet) {
        timeline.add(tweet);
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

package controller;

import model.Perfil;
import model.Tweet;
import model.Repositorio;
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
}

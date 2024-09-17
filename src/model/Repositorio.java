package model;

import java.util.HashMap;
import java.util.Map;

public class Repositorio {
	private Map<String, Perfil> perfis;

	public Repositorio() {
		this.perfis = new HashMap<>();
	}

	// Cadastra um novo perfil
	public void cadastrar(Perfil perfil) throws Exception {
		if (perfis.containsKey(perfil.getUsuario())) {
			throw new Exception("Usuário já cadastrado.");
		}
		perfis.put(perfil.getUsuario(), perfil);
	}

	// Busca um perfil pelo nome de usuário
	public Perfil buscar(String usuario) {
		return perfis.get(usuario);
	}
}

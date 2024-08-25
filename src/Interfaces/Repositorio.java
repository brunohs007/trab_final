package Interfaces;

import java.util.Vector;

import Principal.Perfil;
import Restriçoes.UJCException;
import Restriçoes.UNCException;


public class Repositorio implements IRepositorioUsuario {

    private Vector<Perfil> users = new Vector<Perfil>();

    @Override
    public void cadastrar(Perfil usuario) throws UJCException {
	if(buscar(usuario.getUsuario())!=null) {
	    throw new UJCException(usuario);
	}

	else{
	    users.add(usuario);
	    System.out.println("usuario cadastrado com sucesso!\n");
	}
    }

    @Override
    public Perfil buscar(String usuario) {
	Vector<Perfil> busca = users;
	for(int i=0; i<busca.size();i++) {
	    if(usuario.equals(busca.elementAt(i).getUsuario())){
		return busca.elementAt(i);
	    }
	}
	return null;
    }

    @Override
    public void atualizar(Perfil usuario) throws UNCException {
	Perfil atualizar;
	atualizar = buscar(usuario.getUsuario());
	if(buscar(usuario.getUsuario()) == usuario) {
	    atualizar = usuario;
	    System.out.println("usuario atualizado com sucesso!");
	}
	else {
	    throw new UNCException(usuario);
	}
    }

}

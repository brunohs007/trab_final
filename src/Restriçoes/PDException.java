package Restriçoes;

import Principal.Perfil;

public class PDException extends Exception{

    private Perfil perfil;


    public PDException (Perfil p){
	this.perfil = p;
    }
    public void setPerfil(Perfil p){
	this.perfil = p;
    }
    public Perfil getPerfil(){
	return this.perfil;
    }
}

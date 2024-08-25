package Restriçoes;

import Principal.Perfil;


public class PIException extends Exception{
    private Perfil perfil;

    public PIException(Perfil p){
	this.perfil = p;
    }
    public void setPerfil(Perfil p){
	this.perfil = p;
    }
    public Perfil getPerfil(){
	return this.perfil;
    }
}

package Restriçoes;

import Principal.Perfil;


public class SIException extends Exception{
    private Perfil perfil;

    public SIException(Perfil p){
	this.perfil = p;
    }
    public void setPerfil(Perfil p){
	this.perfil = p;
    }
    public Perfil getPerfil(){
	return this.perfil;
    }
}

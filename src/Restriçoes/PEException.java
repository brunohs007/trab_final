package Restriçoes;

import Principal.Perfil;


public class PEException extends Exception{

    private Perfil perfil;


    public PEException(Perfil p){
	this.perfil = p;
    }
    public void setPerfil(Perfil p){
	this.perfil = p;
    }
    public Perfil getPerfil(){
	return this.perfil;
    }
}

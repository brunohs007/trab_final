package Restriçoes;

import Principal.Perfil;


public class UJCException extends Exception{
    private Perfil perfil;


    public UJCException(Perfil p){
	this.perfil = p;
    }
    public void setPerfil(Perfil p){
	this.perfil = p;
    }
    public Perfil getPerfil(){
	return this.perfil;
    }
}

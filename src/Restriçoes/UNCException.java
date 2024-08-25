package Restriçoes;

import Principal.Perfil;

public class UNCException extends Exception{
    private Perfil perfil;


    public UNCException(Perfil p){
	this.perfil = p;
    }
    public void setPerfil(Perfil p){
	this.perfil = p;
    }
    public Perfil getPerfil(){
	return this.perfil;
    }
}

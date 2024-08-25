package Restriçoes;

import Principal.Perfil;


public class MFPException extends Exception{
    private String mensagem;


    public MFPException(String mensagem){
	this.mensagem = mensagem;
    }
    public void setPerfil(String p){
	this.mensagem = p;
    }
    public String getPerfil(){
	return this.mensagem;
    }
}

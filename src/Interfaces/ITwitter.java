package Interfaces;

import java.util.Vector;

import Principal.Perfil;
import Principal.Tweet;
import Restriçoes.MFPException;
import Restriçoes.PDException;
import Restriçoes.PEException;
import Restriçoes.PIException;
import Restriçoes.SIException;

public interface ITwitter {


    public void criarPerfil(Perfil usuario) throws PEException;

    public void cancelarPerfil(String usuario) throws PIException,PDException;

    public void tweetar(String Usuario, String mensagem) throws PIException,MFPException;

    public Vector<Tweet> timeline(String usuario) throws PIException,PDException;

    public Vector<Tweet> tweets(String usuario) throws PIException,PDException;

    public void seguir(String seguidor, String seguindo) throws PIException,PDException,SIException;

    public int numeroSeguidores(String usuario) throws PIException,PDException;

    public Vector<Perfil> seguidores(String usuario) throws PIException,PDException;

    public Vector<Perfil> seguidos(String usuario) throws PIException,PDException;
}

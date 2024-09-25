package model;

import java.io.Serializable;

public class Tweet implements Serializable {
    private String mensagem;

    public Tweet(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

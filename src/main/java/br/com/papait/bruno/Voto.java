package br.com.papait.bruno;

import java.io.Serializable;

public class Voto implements Serializable {
    private String nomeCandidato;

    public Voto() {
    }

    public Voto(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }
}

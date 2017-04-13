package com.guiigos.androidhelpers.model;

import java.io.Serializable;

public class Aluno
        implements Serializable {

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private double nota;

    public Aluno() {
        this.id = (long)0;
        this.nome = "";
        this.endereco = "";
        this.telefone = "";
        this.nota = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return nome;
    }
}

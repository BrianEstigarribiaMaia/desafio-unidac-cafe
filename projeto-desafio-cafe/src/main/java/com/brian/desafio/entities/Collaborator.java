package com.brian.desafio.entities;

import javax.persistence.Entity;

@Entity
public class Collaborator extends BaseEntity {

    private String fullName;
    private String cpf;

    public Collaborator() {
    }

    public Collaborator(String fullName, String cpf) {
        this.fullName = fullName;
        this.cpf = cpf;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

package com.brian.desafio.entities.dto;

public class CollaboratorItems {

    private String fullName;
    private String cpf;
    private String[] itemNames;

    public CollaboratorItems(String fullName, String cpf, String[] itemNames) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.itemNames = itemNames;
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

    public String[] getItemNames() {
        return itemNames;
    }

    public void setItemNames(String[] itemNames) {
        this.itemNames = itemNames;
    }
}

package database.models;

import entities.Endereco;

public class Cliente {
    private String username;
    private String password;
    private String contato;
    private Endereco endereco;

    public Cliente() {

    }

    public Cliente(String username, String password, String contato, Endereco endereco) {
        this.username = username;
        this.password = password;
        this.contato = contato;
        this.endereco = endereco;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}

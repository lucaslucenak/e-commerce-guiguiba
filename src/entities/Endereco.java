package entities;

public class Endereco {
    private String username;
    private String password;
    private String endereco1;
    private String endereco2;
    private String endereco3;
    private String contato;

    public Endereco() {

    }

    public Endereco(String username, String password, String endereco1, String endereco2, String endereco3, String contato) {
        this.username = username;
        this.password = password;
        this.endereco1 = endereco1;
        this.endereco2 = endereco2;
        this.endereco3 = endereco3;
        this.contato = contato;
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

    public String getEndereco1() {
        return endereco1;
    }

    public void setEndereco1(String endereco1) {
        this.endereco1 = endereco1;
    }

    public String getEndereco2() {
        return endereco2;
    }

    public void setEndereco2(String endereco2) {
        this.endereco2 = endereco2;
    }

    public String getEndereco3() {
        return endereco3;
    }

    public void setEndereco3(String endereco3) {
        this.endereco3 = endereco3;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}

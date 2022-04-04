package database.models;

public class Vendedor {
    private String username;
    private String password;
    private Double vendasRealizadas;

    public Vendedor() {

    }

    public Vendedor(String username, String password, Double vendasRealizadas) {
        this.username = username;
        this.password = password;
        this.vendasRealizadas = vendasRealizadas;
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

    public Double getVendasRealizadas() {
        return vendasRealizadas;
    }

    public void setVendasRealizadas(Double vendasRealizadas) {
        this.vendasRealizadas = vendasRealizadas;
    }
}

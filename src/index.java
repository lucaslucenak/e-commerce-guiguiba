
import database.dao.AdministradorDAO;
import database.dao.ClienteDAO;
import database.dao.VendedorDAO;
import database.models.Administrador;
import database.models.Cliente;
import database.models.Vendedor;
import entities.Endereco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import static database.factory.ConnectionFactory.getConnection;

public class index {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        try {
//            Connection con = getConnection();
//            System.out.println("Conexão com o banco de dados realizada com sucesso.");
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            getConnection().close();
//        }

        Scanner scStr = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        int optionMenu = 0;
        do {
            AdministradorDAO administradorDAO = new AdministradorDAO();
            VendedorDAO vendedorDAO = new VendedorDAO();
            ClienteDAO clienteDAO = new ClienteDAO();

            System.out.println("Bem vindo ao E-Commerce do Guiguiba!\n" +
                    "1. Fazer Login como Administrador\n" +
                    "2. Fazer Login como Vendedor\n" +
                    "3. Fazer Login como Cliente\n" +
                    "4. Criar nova conta\n" +
                    "5. Sair");

            System.out.print("Opção: ");
            optionMenu = scInt.nextInt();

            if (optionMenu == 1) {
                String username;
                String password;
                boolean hasAdministrador = false;

                System.out.print("Username: ");
                username = scStr.nextLine();
                System.out.print("Password: ");
                password = scStr.nextLine();

                //Faz a pesquisa no DB para validar as credenciais
                for (Administrador administrador : administradorDAO.read()) {
                    if (Objects.equals(administrador.getUsername(), username) && Objects.equals(administrador.getPassword(), password)) {
                        hasAdministrador = true;
                        break;
                    }
                }
                
                if (hasAdministrador) {
                    System.out.println("Login realizado com sucesso.");
                }
                else {
                    System.out.println("Username ou password inválido.");
                }

            }
            else if (optionMenu == 2) {
                String username;
                String password;
                boolean hasVendedor = false;

                System.out.print("Username: ");
                username = scStr.nextLine();
                System.out.print("Password: ");
                password = scStr.nextLine();

                //Faz a pesquisa no DB para validar as credenciais
                for (Vendedor vendedor : vendedorDAO.read()) {
                    if (Objects.equals(vendedor.getUsername(), username) && Objects.equals(vendedor.getPassword(), password)) {
                        hasVendedor = true;
                        break;
                    }
                }

                if (hasVendedor) {
                    System.out.println("Login realizado com sucesso.");
                }
                else {
                    System.out.println("Username ou password inválido.");
                }

            }
            else if (optionMenu == 3) {

            }
            else if (optionMenu == 4) {
                int acountType;
                System.out.println("1. Administrador\n" +
                        "2. Vendedor\n" +
                        "3. Cliente");
                System.out.print("Selecione o tipo de conta: ");
                acountType = scInt.nextInt();

                if (acountType == 1) {
                    String username;
                    String password;
                    Administrador administrador = new Administrador();

                    System.out.print("Username: ");
                    username = scStr.nextLine();
                    System.out.print("Password: ");
                    password = scStr.nextLine();

                    administrador.setUsername(username);
                    administrador.setPassword(password);
                    try {
                        administradorDAO.create(administrador); //Cria o administrador no DB
                        System.out.println("Administrador criado com sucesso.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                else if (acountType == 2) {
                    String username;
                    String password;
                    Vendedor vendedor = new Vendedor();

                    System.out.print("Username: ");
                    username = scStr.nextLine();
                    System.out.print("Password: ");
                    password = scStr.nextLine();

                    vendedor.setUsername(username);
                    vendedor.setPassword(password);
                    vendedor.setVendasRealizadas(0.0);
                    try {
                        vendedorDAO.create(vendedor); //Cria o vendedor no DB
                        System.out.println("Vendedor criado com sucesso.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (acountType == 3) {
                    String username;
                    String password;
                    String contato;
                    String endereco1, endereco2, endereco3;
                    Cliente cliente = new Cliente();
                    Endereco endereco = new Endereco();


                    System.out.print("Username: ");
                    username = scStr.nextLine();
                    System.out.print("Password: ");
                    password = scStr.nextLine();
                    System.out.print("Contato: ");
                    contato = scStr.nextLine();
                    System.out.print("Enderço 1: ");
                    endereco1 = scStr.nextLine();
                    System.out.print("Enderço 2: ");
                    endereco2 = scStr.nextLine();
                    System.out.print("Enderço 3: ");
                    endereco3 = scStr.nextLine();

                    endereco.setEndereco1(endereco1);
                    endereco.setEndereco2(endereco2);
                    endereco.setEndereco3(endereco3);

                    cliente.setUsername(username);
                    cliente.setPassword(password);
                    cliente.setContato(contato);
                    cliente.setEndereco(endereco);
                    try {
                        clienteDAO.create(cliente); //Cria o vendedor no DB
                        System.out.println("Cliente criado com sucesso.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (optionMenu == 5) {
                System.out.println("xau");
            }
            else {
                System.out.println("Opção Inválida!");
            }
        } while (optionMenu != 5);
    }

}

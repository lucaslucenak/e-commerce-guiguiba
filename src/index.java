
import database.dao.AdministradorDAO;
import database.models.Administrador;

import java.sql.Connection;
import java.sql.SQLException;
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
            System.out.println("Bem vindo ao E-Commerce do Guiguiba!\n" +
                    "1. Fazer Login como Administrador\n" +
                    "2. Fazer Login como Vendedor\n" +
                    "3. Fazer Login como Cliente\n" +
                    "4. Criar nova conta\n" +
                    "5. Sair");

            System.out.print("Opção: ");
            optionMenu = scInt.nextInt();

            if (optionMenu == 1) {

            }
            else if (optionMenu == 2) {

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
                    AdministradorDAO administradorDAO = new AdministradorDAO();

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

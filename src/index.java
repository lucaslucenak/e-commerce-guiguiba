import java.sql.Connection;
import java.sql.ConnectionBuilder;
import java.sql.DriverManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class index {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int optionMenu = 0;
        do {
            System.out.println("Bem vindo ao E-Commerce do Guiguiba!\n" +
                    "1. Fazer Login como Administrador\n" +
                    "2. Fazer Login como Vendedor\n" +
                    "3. Fazer Login como Cliente\n" +
                    "4. Criar nova conta\n" +
                    "5. Sair");

            System.out.print("Opção: ");
            optionMenu = sc.nextInt();

            if (optionMenu == 1) {

            }
            else if (optionMenu == 2) {

            }
            else if (optionMenu == 3) {

            }
            else if (optionMenu == 4) {
                int acountType = 0;

                if (acountType == 1) {

                }
                else if (acountType == 2) {

                }
            }
            else {
                System.out.println("Opção Inválida!");
            }
        } while (optionMenu != 5);
    }

}

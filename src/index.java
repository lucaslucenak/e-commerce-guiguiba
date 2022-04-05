
import database.dao.*;
import database.models.*;
import entities.CarrinhoDeCompras;
import entities.Endereco;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            VendedorDAO vendedorDAO = new VendedorDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            System.out.println("Bem vindo ao E-Commerce do Guiguiba!\n" +
                    "1. Fazer Login como Administrador\n" +
                    "2. Fazer Login como Vendedor\n" +
                    "3. Fazer Login como Cliente\n" +
                    "4. Criar nova conta\n" +
                    "5. Sair");

            System.out.print("Opção: ");
            optionMenu = scInt.nextInt();

            if (optionMenu == 1) { //OK
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
                    int crudOption = 0;

                    do {
                        System.out.println("1. Criar Categoria\n" +
                                "2. Pesquisar Categoria\n" +
                                "3. Listar Categorias\n" +
                                "4. Atualizar Categoria\n" +
                                "5. Deletar Categoria\n" +
                                "6. Logout");

                        System.out.print("Opção: ");
                        crudOption = scInt.nextInt();

                        if (crudOption == 1) { //OK
                            String nome;
                            Categoria categoria = new Categoria();

                            System.out.print("Nome Categoria: ");
                            nome = scStr.nextLine();
                            categoria.setNome(nome);

                            try {
                                categoriaDAO.create(categoria); //Cria o administrador no DB
                                System.out.println("Categoria criada com sucesso.");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        else if (crudOption == 2) { //OK
                            String nome;
                            boolean hasCategoria = false;
                            
                            System.out.print("Nome Categoria: ");
                            nome = scStr.nextLine();

                            for (Categoria categoria : categoriaDAO.read()) {
                                if (categoria.getNome().equals(nome)) {
                                    hasCategoria = true;
                                    break;
                                }
                            }
                            if (hasCategoria) {
                                System.out.println("Categoria existente.");
                            }
                            else {
                                System.out.println("Categoria não existente.");
                            }
                        }

                        else if (crudOption == 3) { //OK
                            int aux = 0;
                            for (Categoria categoria : categoriaDAO.read()) {
                                System.out.println((aux+1) + ". " + categoria.getNome());
                                aux++;
                            }
                            System.out.println("---------------------");
                        }

                        else if (crudOption == 4) {

                        }

                        else if (crudOption == 5) {

                        }

                        else if (crudOption == 6) {
                            System.out.println("Deslogado com sucesso.");
                        }

                        else {
                            System.out.println("Opção inválida.");
                        }

                    } while (crudOption != 6);
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
                    int crudOption = 0;

                    do {
                        System.out.println("1. Criar Produto\n" +
                                "2. Pesquisar Produto\n" +
                                "3. Listar Produtos\n" +
                                "4. Atualizar Produto\n" +
                                "5. Deletar Produto\n" +
                                "6. Logout");

                        System.out.print("Opção: ");
                        crudOption = scInt.nextInt();

                        if (crudOption == 1) { //OK
                            int categoriaOption;
                            List<Categoria> categorias = new ArrayList<>();
                            int aux = 0;

                            System.out.println("Categorias disponíveis para associar ao produto: ");
                            for (Categoria categoria : categoriaDAO.read()) {
                                categorias.add(categoria);
                                System.out.println((aux+1) + ". " + categoria.getNome());
                                aux++;
                            }
                            System.out.println("---------------------");

                            System.out.print("Categoria desejada (Cancelar -> -1): ");
                            categoriaOption = scInt.nextInt();

                            if (categoriaOption <= aux && categoriaOption >= 1) {
                                String nome;
                                String categoria = categorias.get(categoriaOption - 1).getNome();
                                Double preco;
                                Integer quantidade;
                                Produto produto = new Produto();

                                System.out.print("Nome: ");
                                nome = scStr.nextLine();
                                System.out.print("Preço: ");
                                preco = scInt.nextDouble();
                                System.out.print("Quantidade: ");
                                quantidade = scInt.nextInt();

                                produto.setNome(nome);
                                produto.setCategoria(categoria);
                                produto.setPreco(preco);
                                produto.setQuantidade(quantidade);

                                try {
                                    produtoDAO.create(produto); //Cria o administrador no DB
                                    System.out.println("Produto criado com sucesso.");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            if (categoriaOption == -1) {
                                System.out.println("Cancelando operação.");
                            }

                            if (categoriaOption > aux || categoriaOption < -1 || categoriaOption == 0) {
                                System.out.println("Opção inválida.");
                            }
                        }

                        else if (crudOption == 2) { //OK
                            String nome;
                            boolean hasProduto = false;
                            Produto produtoEcontrado = new Produto();

                            System.out.print("Nome Produto: ");
                            nome = scStr.nextLine();

                            for (Produto produto : produtoDAO.read()) {
                                if (produto.getNome().equals(nome)) {
                                    produtoEcontrado = produto;
                                    hasProduto = true;
                                    break;
                                }
                            }

                            if (hasProduto) {
                                System.out.println("Produto Encontrado. Descrição:");
                                System.out.printf("Nome: %s\nCategoria: %s\nPreço: %f\nQuantidade: %d\n", produtoEcontrado.getNome(),
                                        produtoEcontrado.getCategoria(), produtoEcontrado.getPreco(), produtoEcontrado.getQuantidade());
                            }
                            else {
                                System.out.println("Produto não existente.");
                            }
                        }

                        else if (crudOption == 3) { //OK
                            int aux = 0;
                            for (Produto produto : produtoDAO.read()) {
                                System.out.println((aux+1) + ". " + produto.getNome());
                                aux++;
                            }
                            System.out.println("---------------------");
                        }

                        else if (crudOption == 4) {

                        }

                        else if (crudOption == 5) {

                        }

                        else if (crudOption == 6) {
                            System.out.println("Deslogado com sucesso.");
                        }

                        else {
                            System.out.println("Opção inválida.");
                        }

                    } while (crudOption != 6);
                }

                else {
                    System.out.println("Username ou password inválido.");
                }
            }
            else if (optionMenu == 3) {
                String username;
                String password;
                boolean hasCliente = false;

                System.out.print("Username: ");
                username = scStr.nextLine();
                System.out.print("Password: ");
                password = scStr.nextLine();

                //Faz a pesquisa no DB para validar as credenciais
                for (Cliente cliente : clienteDAO.read()) {
                    if (Objects.equals(cliente.getUsername(), username) && Objects.equals(cliente.getPassword(), password)) {
                        hasCliente = true;
                        break;
                    }
                }

                if (hasCliente) {
                    System.out.println("Login realizado com sucesso.");
                    CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
                    List<Produto> produtosSet = new ArrayList<>();
                    carrinhoDeCompras.setProdutos(produtosSet);
                    List<Categoria> categorias = new ArrayList<>();
                    Double valorCarrinho = 0.0;
                    int option = 0;

                    do {
                        System.out.println("1. Pesquisar Produto\n" +
                                "2. Listar Produtos\n" +
                                "3. Esvaziar carrinho\n" +
                                "4. Visualizar carrinho\n" +
                                "5. Logout\n");

                        System.out.print("Opção: ");
                        option = scInt.nextInt();

                        if (option == 1) {
                            int aux = 0;
                            int aux1 = 0;

                            List<Produto> produtos = new ArrayList<>();
                            Integer categoriaPesquisa;
                            Integer adicionarProduto;
                            Integer idProduto;

                            System.out.println("Categorias disponíveis: ");
                            for (Categoria categoria : categoriaDAO.read()) {
                                categorias.add(categoria);
                                System.out.println((aux+1) + ". " + categoria.getNome());
                                aux++;
                            }
                            System.out.println("---------------------");
                            System.out.print("Opção: ");
                            categoriaPesquisa = scInt.nextInt();

                            for (Produto produto : produtoDAO.read(categorias.get(categoriaPesquisa-1).getNome())) {
                                produtos.add(produto);
                                System.out.println((aux1+1) + ". " + produto.getNome());
                                System.out.println("   Preço: " + produto.getPreco());
                                System.out.println("   Quantidade: " + produto.getQuantidade());
                                aux1++;
                            }
                            System.out.println("---------------------");
                            System.out.println("Deseja adicionar algum dos produtos listos ao carrinho? ");
                            System.out.println("1. Sim\n" +
                                    "2. Não");
                            System.out.print("Opção: ");
                            adicionarProduto = scInt.nextInt();

                            if (adicionarProduto == 1) {

                                System.out.print("Número do Produto: ");
                                idProduto = scInt.nextInt();

                                if (produtos.get(idProduto-1).getQuantidade() > 0) {
                                    Produto produtoEscolhido;
                                    produtoEscolhido = produtos.get(idProduto-1);
                                    carrinhoDeCompras.adicionarProduto(produtoEscolhido);
//                                    carrinhoDeCompras.adicionarProduto(produtoEscolhido);
                                    //FAZER UPDATE DA QUANTIDADE DOS PRODUTOS NO DB
                                }

                                else {
                                    System.out.println("Produto insdisponível.");
                                }

                            }

                            else if (adicionarProduto == 2) {
                                System.out.println("Voltando ao menu...");
                            }

                            else {
                                System.out.println("Opção inválida.");
                            }
                        }

                        else if (option == 2) { //OK
                            int aux = 0;
                            System.out.println("Lista de Produtos:");
                            for (Produto produto : produtoDAO.read()) {
                                System.out.println((aux+1) + ". " + produto.getNome());
                                aux++;
                            }
                            System.out.println("---------------------");
                        }

                        else if (option == 3) { //OK
                            carrinhoDeCompras.esvaziarCarrinho();
                            valorCarrinho = 0.0;
                        }

                        else if (option == 4) {
                            int aux3 = 0;
                            System.out.println("Lista de produtos presentes no carrinho: ");
                            for (Produto produto : carrinhoDeCompras.getProdutos()) {
                                System.out.println((aux3+1) + ". " + produto.getNome());
                                aux3++;
                            }
                            System.out.println("---------------------");
                        }

                    } while (option != 5);

                }
                else {
                    System.out.println("Username ou password inválido.");
                }
            }
            else if (optionMenu == 4) { //OK
                int acountType;
                System.out.println("1. Administrador\n" +
                        "2. Vendedor\n" +
                        "3. Cliente");
                System.out.print("Selecione o tipo de conta: ");
                acountType = scInt.nextInt();

                if (acountType == 1) { //OK
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
                else if (acountType == 2) { //OK
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
                else if (acountType == 3) { //OK
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
                    System.out.println("Volte sempre!");
            }
            else {
                System.out.println("Opção Inválida!");
            }
        } while (optionMenu != 5);
    }

}

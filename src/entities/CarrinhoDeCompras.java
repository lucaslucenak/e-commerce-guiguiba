package entities;

import database.models.Produto;

import java.util.List;

public class CarrinhoDeCompras {
    private List<Produto> produtos;
    private Double valorCarrinho;

    public CarrinhoDeCompras() {

    }

    public CarrinhoDeCompras(List<Produto> produtos, Double valorCarrinho) {
        this.produtos = produtos;
        this.valorCarrinho = valorCarrinho;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public Double getValorCarrinho() {
        return valorCarrinho;
    }

    public void setValorCarrinho(Double valorCarrinho) {
        this.valorCarrinho = valorCarrinho;
    }

    public void esvaziarCarrinho() {
        this.produtos.clear();
        this.valorCarrinho = 0.0;
    }

}

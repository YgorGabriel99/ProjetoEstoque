/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

/**
 *
 * @author ygorf
 */
public class Produto {
    
    private int id;
    private String  nome;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private int quantidade;
    private String liberado;
    private int categoria;

   
    public int getId() {
        return id;
    }

   
    public void setId(int id) {
        this.id = id;
    }

   
    public String getNome() {
        return nome;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    public double getPrecoCompra() {
        return precoCompra;
    }

    
    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    
    public double getPrecoVenda() {
        return precoVenda;
    }

    
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    
    public int getQuantidade() {
        return quantidade;
    }

   
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

   
    public String getLiberado() {
        return liberado;
    }

    
    public void setLiberado(String liberado) {
        this.liberado = liberado;
    }

   
    public int getCategoria() {
        return categoria;
    }

    
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    
    
}

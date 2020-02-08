/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.stockProdutoTenda;

/**
 *
 * @author carlos
 */
public class StockProdutosTenda {

    String nomeTenda;
    String nomeProduto;
    Double stock;

    public StockProdutosTenda(String nomeTenda, String nomeProduto, Double stock) {
        this.nomeTenda = nomeTenda;
        this.nomeProduto = nomeProduto;
        this.stock = stock;
    }

    public String getNomeTenda() {
        return nomeTenda;
    }

    public void setNomeTenda(String nomeTenda) {
        this.nomeTenda = nomeTenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "StockProdutosTenda{" + "nomeTenda=" + nomeTenda + ", nomeProduto=" + nomeProduto + ", stock=" + stock + '}';
    }

}

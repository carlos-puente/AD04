/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.obxectos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class StockProdutoJSON implements Serializable{

    private String produto;
    private String descricion;
    private int stockTotal = 0;
    private List<StockProdutoTendaJSON> stockPorTenda = new ArrayList<StockProdutoTendaJSON>();

    public StockProdutoJSON(String produto, String descricion) {
        this.produto = produto;
        this.descricion = descricion;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }

    public void addStockTenda(StockProdutoTendaJSON stockEnTenda) {
        this.stockTotal += stockEnTenda.getStock();
        this.stockPorTenda.add(stockEnTenda);
    }

}

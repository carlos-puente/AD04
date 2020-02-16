/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.obxectos;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class StockProdutoTendaJSON implements Serializable{
    private String tenda;
    private int stock;

    public StockProdutoTendaJSON(String tenda, int stock) {
        this.tenda = tenda;
        this.stock = stock;
    }

    public StockProdutoTendaJSON() {
    }

    public String getTenda() {
        return tenda;
    }

    public void setTenda(String tenda) {
        this.tenda = tenda;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}

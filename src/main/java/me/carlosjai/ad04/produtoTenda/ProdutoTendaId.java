/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.produtoTenda;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author carlos
 */
@Embeddable
public class ProdutoTendaId implements Serializable {

    @Column(name = "produto_nome")
    private String produto;
    @Column(name = "tenda_nome")
    private String tenda;

    public ProdutoTendaId(String produto, String tenda) {
        this.produto = produto;
        this.tenda = tenda;
    }

    
    
    
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getTenda() {
        return tenda;
    }

    public void setTenda(String tenda) {
        this.tenda = tenda;
    }

    public ProdutoTendaId() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.produto);
        hash = 67 * hash + Objects.hashCode(this.tenda);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProdutoTendaId other = (ProdutoTendaId) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.tenda, other.tenda)) {
            return false;
        }
        return true;
    }

}

package me.carlosjai.ad04.produtoTenda;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import me.carlosjai.ad04.obxectos.Produto;
import me.carlosjai.ad04.obxectos.Tenda;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "produto_tenda")
public class ProdutoTenda implements Serializable {

    @EmbeddedId
    private ProdutoTendaId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("produto_nome")
    Produto produto;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("tenda_nome")
    Tenda tenda;
    @Column(name = "stock")
    int stock;

    public ProdutoTenda() {
    }

    public ProdutoTenda(Produto produto, Tenda tenda, int stock) {
        this.produto = produto;
        this.tenda = tenda;
        this.stock = stock;
        this.id = new ProdutoTendaId(produto.getNome(), tenda.getNome());
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Tenda getTenda() {
        return tenda;
    }

    public void setTenda(Tenda tenda) {
        this.tenda = tenda;
    }

    public int getStock() {
        return stock;
    }
    
    public int getStockFormatted(){
        
        return this.stock > 0 ? this.stock : 0;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProdutoTendaId getId() {
        return id;
    }

    public void setId(ProdutoTendaId id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.produto.getNome());
        hash = 89 * hash + Objects.hashCode(this.tenda.getNome());
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
        final ProdutoTenda other = (ProdutoTenda) obj;
        if (!Objects.equals(this.produto.getNome(), other.produto.getNome())) {
            return false;
        }
        if (!Objects.equals(this.tenda.getNome(), other.tenda.getNome())) {
            return false;
        }
        return true;
    }
    
    

}

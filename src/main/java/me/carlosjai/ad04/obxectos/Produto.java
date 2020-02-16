package me.carlosjai.ad04.obxectos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import me.carlosjai.ad04.produtoTenda.ProdutoTenda;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

/**
 *
 * @author carlos
 */
@Entity(name = "Produto")
@Table(name = "Produto")
@NaturalIdCache
@Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Produto implements Serializable {

    @Id
    @Column(name = "nome")
    private String nome;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "prezo")
    private double prezo;

     @OneToMany(
        mappedBy = "produto",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<ProdutoTenda> produtoTenda = new HashSet<ProdutoTenda>();

    public Produto(String nome, String descripcion, double prezo) {
        this.nome = nome;
        this.descripcion = descripcion;
        this.prezo = prezo;
    }

    public Produto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrezo() {
        return prezo;
    }

    public void setPrezo(double prezo) {
        this.prezo = prezo;
    }

    public Set<ProdutoTenda> getProdutoTenda() {
        return produtoTenda;
    }

    public void setProdutoTenda(Set<ProdutoTenda> produtoTenda) {
        this.produtoTenda = produtoTenda;
    }
    
    

    @Override
    public String toString() {
        return "- Produto: " + "nome: " + nome + ", descripcion: " + descripcion + ", prezo: " + prezo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.descripcion);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.prezo) ^ (Double.doubleToLongBits(this.prezo) >>> 32));
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
        final Produto other = (Produto) obj;
        if (Double.doubleToLongBits(this.prezo) != Double.doubleToLongBits(other.prezo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }

}

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
import me.carlosjai.ad04.empregadoTenda.EmpregadoTenda;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

/**
 *
 * @author carlos
 */
@Entity(name = "Empregado")
@Table(name = "Empregado")
@NaturalIdCache
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Empregado implements Serializable {

    @Id
    @Column(name = "nif")
    private String nif;

    @Column(name = "nome")
    private String nome;

    @Column(name = "apelidos")
    private String apelidos;
    @OneToMany(
            mappedBy = "empregado",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<EmpregadoTenda> empregadoTenda = new HashSet<EmpregadoTenda>();

    public Empregado() {
    }

    public Empregado(String nif, String nome, String apelidos, Set<EmpregadoTenda> empregadoTenda) {
        this.nif = nif;
        this.nome = nome;
        this.apelidos = apelidos;
        this.empregadoTenda = empregadoTenda;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public Set<EmpregadoTenda> getEmpregadoTenda() {
        return empregadoTenda;
    }

    public void setEmpregadoTenda(Set<EmpregadoTenda> empregadoTenda) {
        this.empregadoTenda = empregadoTenda;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.nif);
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + Objects.hashCode(this.apelidos);
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
        final Empregado other = (Empregado) obj;
        if (!Objects.equals(this.nif, other.nif)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.apelidos, other.apelidos)) {
            return false;
        }
        return true;
    }

}

package me.carlosjai.ad04.obxectos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import me.carlosjai.ad04.empregadoTenda.EmpregadoTenda;
import me.carlosjai.ad04.produtoTenda.ProdutoTenda;

/**
 *
 * @author carlos
 */
@Entity(name = "Tenda")
@Table(name = "Tenda")
public class Tenda {

    @Id
    @Column(name = "nome")
    private String nome;
    @Column(name = "cidade")
    private String cidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @OneToMany(
            mappedBy = "tenda",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<EmpregadoTenda> empregadoTenda = new HashSet<EmpregadoTenda>();

    @OneToMany(
            mappedBy = "tenda",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProdutoTenda> produtoTenda = new HashSet<ProdutoTenda>();

    public Tenda() {
    }

    public Tenda(String nome, String cidade, Provincia provincia) {
        this.nome = nome;
        this.cidade = cidade;
        this.provincia = provincia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Set<EmpregadoTenda> getEmpregadoTenda() {
        return empregadoTenda;
    }

    public void setEmpregadoTenda(Set<EmpregadoTenda> empregadoTenda) {
        this.empregadoTenda = empregadoTenda;
    }

    public Set<ProdutoTenda> getProdutoTenda() {
        return produtoTenda;
    }

    public void setProdutoTenda(Set<ProdutoTenda> produtoTenda) {
        this.produtoTenda = produtoTenda;
    }

    public void addProduto(Produto prod, int stock) {
        ProdutoTenda pT = new ProdutoTenda(prod, this, stock);
        if (produtoTenda.contains(pT)) {
            produtoTenda.remove(pT);
            prod.getProdutoTenda().remove(pT);
        }
        produtoTenda.add(pT);
        prod.getProdutoTenda().add(pT);

    }

    public void addEmpregado(Empregado emp, int horasSemanais) {
        EmpregadoTenda eT = new EmpregadoTenda(emp, this, horasSemanais);
        if (empregadoTenda.contains(eT)) {
            produtoTenda.remove(eT);
            emp.getEmpregadoTenda().remove(eT);
        }
        empregadoTenda.add(eT);
        emp.getEmpregadoTenda().add(eT);
    }

    public void removeProduto(Produto prod) {
        for (Iterator<ProdutoTenda> iterator = produtoTenda.iterator();
                iterator.hasNext();) {
            ProdutoTenda pT = iterator.next();

            if (pT.getTenda().getNome().equals(this.nome)
                    && pT.getProduto().getNome().equals(prod.getNome())) {
                produtoTenda.remove(pT);
                pT.getProduto().getProdutoTenda().remove(pT);

            }
        }
    }

    public void removeEmpregado(Empregado emp) {
        for (Iterator<EmpregadoTenda> iterator = empregadoTenda.iterator();
                iterator.hasNext();) {
            EmpregadoTenda eT = iterator.next();

            if (eT.getTenda().nome.equals(this.getNome())
                    && eT.getEmpregado().getNif().equals(emp.getNif())) {
                empregadoTenda.remove(eT);
                eT.getEmpregado().getEmpregadoTenda().remove(eT);
            }
        }
    }

    @Override
    public String toString() {
        return "Tenda{" + "nome=" + nome + ", cidade=" + cidade + ", provincia=" + provincia + '}';
    }

    public void listarProdutos() {
        System.out.println("PRODUTOS NA TENDA: " + this.nome);
        if (!this.tenProdutos()) {
            System.out.println("Non hai produtos rexistrados.");
        } else {
            System.out.printf("%-25s %-30s %-10s %-10s\n", "Nome", "Descripción", "Prezo", "Stock");
            System.out.println("--------------------------------------------------------------------------------");
            int i = 1;
            for (ProdutoTenda pT : this.produtoTenda) {
                Produto pro = pT.getProduto();
                System.out.printf("%-25s %-30s %-10s %-10s\n", pro.getNome(), pro.getDescripcion().length() > 25 ? pro.getDescripcion().substring(0, 25) + "..." : pro.getDescripcion(), pro.getPrezo() + "€", pT.getStock());
                i++;
            }
        }
        System.out.println("\n\n\n");
    }

    public boolean tenProdutos() {
        return this.produtoTenda != null && !this.produtoTenda.isEmpty();
    }

    public boolean tenEmpregados() {
        return this.empregadoTenda != null && !this.empregadoTenda.isEmpty();
    }

    public int getStockProduto(Produto p) {
        int stock = -1;
        for (ProdutoTenda pt : this.produtoTenda) {
            if (pt.getProduto().equals(p)) {
                stock = pt.getStock();
                break;
            }
        }
        return stock;
    }

    public int getHorasEmpregado(Empregado e) {
        int horas = -1;
        for (EmpregadoTenda et : this.empregadoTenda) {
            if (et.getEmpregado().equals(e)) {
                horas = et.getHorasSemanais();
                break;
            }
        }
        return horas;
    }

    public void amosarStockProduto(Produto p) {
        if (!this.tenProdutos()) {
            System.out.println("Non hai produtos rexistrados.");
        } else {
            int stock = this.getStockProduto(p);
            if (stock == -1) {
                System.out.println("O produto non está rexistrado nesta tenda.");
            } else {
                System.out.println("\n- Stock: " + stock + "\n");
            }
        }

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.nome);
        hash = 61 * hash + Objects.hashCode(this.cidade);
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
        final Tenda other = (Tenda) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        return true;
    }

}

package me.carlosjai.ad04.obxectos;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author carlos
 */
public class Tenda {

    private String nome;
    private String cidade;
    private Provincia provincia;
    private Map<Produto, Double> stockProdutos;

    public Tenda() {
        stockProdutos = new HashMap<>();
    }

    public Tenda(String nome, String cidade, Provincia provincia) {
        this.nome = nome;
        this.cidade = cidade;
        this.provincia = provincia;
        this.stockProdutos = new HashMap<>();
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

    public Map<Produto, Double> getStockProdutos() {
        return stockProdutos;
    }

    public void setStockProdutos(Map<Produto, Double> stockProdutos) {
        this.stockProdutos = stockProdutos;
    }

    @Override
    public String toString() {
        return "Tenda{" + "nome=" + nome + ", cidade=" + cidade + ", provincia=" + provincia + '}';
    }

    public void listarProdutos() {
        System.out.println("PRODUTOS NA TENDA: "+this.nome);
        if (this.stockProdutos == null || this.stockProdutos.isEmpty()) {
            System.out.println("Non hai produtos rexistrados.");
        } else {
             System.out.printf("%-25s %-30s %-10s %-10s\n", "Nome", "Descripción", "Prezo", "Stock");
            System.out.println("--------------------------------------------------------------------------------");

            int i = 1;
            for (Entry<Produto, Double> e : stockProdutos.entrySet()) {
                Produto pro = e.getKey();
                System.out.printf("%-25s %-30s %-10s %-10s\n", pro.getNome(), pro.getDescripcion().length() > 25 ? pro.getDescripcion().substring(0, 25)+"..." : pro.getDescripcion(), pro.getPrezo() + "€", this.stockProdutos.get(pro));
                i++;
            }
        }
        System.out.println("\n\n\n");
    }

    public void amosarStockProduto(Produto p) {
        if (this.stockProdutos == null || this.stockProdutos.isEmpty()) {
            System.out.println("Non hai produtos rexistrados.");
        } else if (stockProdutos.containsKey(p)) {
            System.out.println("\n- Stock: " + this.stockProdutos.get(p) + "\n");
        } else {
            System.out.println("O produto non está rexistrado nesta tenda.");
        }

    }

}

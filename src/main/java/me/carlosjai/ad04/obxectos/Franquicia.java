package me.carlosjai.ad04.obxectos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase xeral da aplicación. Aquí atoparanse a lista de tendas e os clientes da
 * compañía.
 *
 * @author carlos
 */
public class Franquicia implements Serializable {

    private List<Tenda> tendas;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Provincia> provincias;
    private List<Empregado> empregados;

    public Franquicia() {
        tendas = new ArrayList<>();
        clientes = new ArrayList<>();
        produtos = new ArrayList<>();
        provincias = new ArrayList<>();
        empregados = new ArrayList<>();
    }

    public List<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(List<Empregado> empregados) {
        this.empregados = empregados;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<Tenda> getTendas() {
        return tendas;
    }

    public void setTendas(List<Tenda> tendas) {
        this.tendas = tendas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void listarProdutos() {
        System.out.println("PRODUTOS NA FRANQUICIA: ");
        if (produtos.isEmpty()) {
            System.out.println("Non hai elementos rexistrados");
        } else {
            System.out.printf("%-25s %-30s %-10s\n", "Nome", "Descripción", "Prezo");
            System.out.println("--------------------------------------------------------------");
            for (Produto pro : produtos) {
                System.out.printf("%-25s %-30s %-10s\n", pro.getNome(), pro.getDescripcion().length() > 25 ? pro.getDescripcion().substring(0, 25)+"..." : pro.getDescripcion(), pro.getPrezo() + "€");

            }
        }
        System.out.println("\n\n\n");
    }

    /**
     * Amosa por pantalla información de todas as tendas da franquicia
     *
     * @return
     */
    public void listarTendas() {
        System.out.println("TENDAS NA FRANQUICIA");
        if (tendas.isEmpty()) {
            System.out.println("Non hai elementos rexistrados");
        } else {
            System.out.printf("%-25s %-30s %-30s %-15s %-10s\n", "Nome", "Cidade", "Provincia", "Empregados", "Produtos");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            for (Tenda t : tendas) {
                System.out.printf("%-25s %-30s %-30s %-15s %-10s\n", t.getNome(), t.getCidade(), t.getProvincia().getNome(), getNumEmpregadosTenda(t), t.getStockProdutos().size());

            }
        }
        System.out.println("\n\n\n");

    }

    /**
     * Amosa por pantalla información de todos os clientes da franquicia
     *
     * @return
     */
    public void listarClientes() {
        System.out.println("CLIENTES NA FRANQUICIA");
        if (clientes.isEmpty()) {
            System.out.println("Non hai elementos rexistrados");
        } else {
            System.out.printf("%-25s %-30s %-10s\n", "Nome", "Apelidos", "Email");
            System.out.println("--------------------------------------------------------------");
            for (Cliente c : clientes) {
                System.out.printf("%-25s %-30s %-10s\n", c.getNome(), c.getApelidos(), c.getEmail());

            }
        }
        System.out.println("\n\n\n");
    }

    public void listarProvincias() {
        System.out.println("PROVINCIAS");
        System.out.printf("%-5s %-40s\n", "ID", "PROVINCIA");
        System.out.println("--------------------------------------------------------------");
        for (Provincia p : provincias) {
            System.out.printf("%-5s %-40s\n", p.getId(), p.getNome());

        }
        System.out.println("\n\n\n");
    }

    public void listarEmpregados() {
        System.out.println("EMPREGADOS NA FRANQUICIA");
        if (empregados.isEmpty()) {
            System.out.println("Non hai elementos rexistrados");
        } else {
            System.out.printf("%-25s %-30s %-10s\n", "Nome", "Apelidos", "nif");
            System.out.println("--------------------------------------------------------------");
            for (Empregado e : empregados) {
                System.out.printf("%-25s %-30s %-10s\n", e.getNome(), e.getApelidos(), e.getNif());

            }
        }
        System.out.println("\n\n\n");
    }

    /**
     * Devolve unha tenda polo seu nome
     *
     * @param nomeTenda
     * @return
     */
    public Tenda getTenda(String nomeTenda) {
        Tenda tenda = null;
        if (this.tendas != null && !this.tendas.isEmpty()) {
            for (Tenda t : this.tendas) {
                if (t.getNome().equalsIgnoreCase(nomeTenda)) {
                    tenda = t;
                    break;
                }
            }
        }
        return tenda;
    }

    /**
     * Devolve un cliente polo seu email
     *
     * @param email
     * @return
     */
    public Cliente getCliente(String email) {
        Cliente c = null;
        if (this.clientes != null && !this.clientes.isEmpty()) {
            for (Cliente cli : this.clientes) {
                if (cli.getEmail().equalsIgnoreCase(email)) {
                    c = cli;
                    break;
                }
            }
        }
        return c;
    }

    public Provincia getProvincia(String id) {
        Provincia p = null;
        for (Provincia provincia : provincias) {
            if (provincia.getId().equalsIgnoreCase(id)) {
                p = provincia;
                break;
            }
        }
        return p;
    }

    public Produto getProduto(String nomeProduto) {
        Produto p = null;
        for (Produto pro : produtos) {
            if (pro.getNome().equalsIgnoreCase(nomeProduto)) {
                p = pro;
                break;
            }
        }
        return p;
    }

    public Empregado getEmpregado(String nif) {
        Empregado e = null;
        for (Empregado emp : empregados) {
            if (emp.getNif().equalsIgnoreCase(nif)) {
                e = emp;
                break;
            }
        }
        return e;
    }
    
     public int getNumEmpregadosTenda(Tenda t){
         int num = 0;
          for (Empregado e : empregados) {
                    if (e.getHorasSemanaisPorTenda().containsKey(t)) {
                     num++;  
                    }
                }
          return num;
     }

    public void listarEmpregadosDeTenda(Tenda tenda) {
        if (tenda != null) {
            System.out.println("EMPREGADOS NA TENDA: " + tenda.getNome());
            if (empregados.isEmpty() || tenda.getStockProdutos().isEmpty()) {
                System.out.println("Non hai elementos rexistrados.");
            } else {
                System.out.printf("%-25s %-30s %-10s %-10s\n", "Nome", "Apelidos", "nif", "horas");
                System.out.println("--------------------------------------------------------------------------");
                for (Empregado e : empregados) {
                    if (e.getHorasSemanaisPorTenda().containsKey(tenda)) {
                        System.out.printf("%-25s %-30s %-10s %-10s\n", e.getNome(), e.getApelidos(), e.getNif(), e.getHorasSemanaisPorTenda().get(tenda));
                    }
                }
            }
        } else {
            System.out.println("Tenda non atopada.");
        }
        System.out.println("\n\n\n");
    }

    public void listarEmpregado(Empregado e) {
        if (empregados.isEmpty()) {
            System.out.println("Non hai empregados rexistrados.");
        } else {
            for (Empregado emp : empregados) {
                if (emp.getNif().equalsIgnoreCase(e.getNif())) {
                    System.out.println(emp);
                    break;
                }

            }
        }
    }

}

package me.carlosjai.ad04.obxectos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author carlos
 */
public class Empregado implements Serializable {
    private String nif; //Engado un campo nif para utilizalo como identificador
    private String nome;
    private String apelidos;
    private Map<Tenda, Double> horasSemanaisPorTenda;
    public Empregado() {
    }

    public Empregado(String nif, String nome, String apelidos) {
        this.nif = nif;
        this.nome = nome;
        this.apelidos = apelidos;
        this.horasSemanaisPorTenda = new HashMap<>();
    }

    public Map<Tenda, Double> getHorasSemanaisPorTenda() {
        return horasSemanaisPorTenda;
    }

    public void setHorasSemanaisPorTenda(Map<Tenda, Double> horasSemanaisPorTenda) {
        this.horasSemanaisPorTenda = horasSemanaisPorTenda;
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
    
    @Override
    public String toString() {
        return "- Empregado: nif: "+nif + "nome: " + nome + ", apelidos: " + apelidos;
    }
}

package me.carlosjai.ad04.obxectos;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class Produto implements Serializable {

    private String nome;
    private String descripcion;
    private double prezo;

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


    @Override
    public String toString() {
        return "- Produto: " + "nome: " + nome + ", descripcion: " + descripcion + ", prezo: " + prezo;
    }

}

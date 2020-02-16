/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.empregadoTenda;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import me.carlosjai.ad04.obxectos.Empregado;
import me.carlosjai.ad04.obxectos.Tenda;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "empregado_tenda")
public class EmpregadoTenda {

    @EmbeddedId
    private EmpregadoTendaId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("empregado_nif")
    private Empregado empregado;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("tenda_nome")
    private Tenda tenda;

    @Column(name = "horas_semanais")
    private int horasSemanais;

    public EmpregadoTenda() {
    }

    public EmpregadoTenda(Empregado empregado, Tenda tenda, int horasSemanais) {
        this.empregado = empregado;
        this.tenda = tenda;
        this.horasSemanais = horasSemanais;
        this.id = new EmpregadoTendaId(empregado.getNif(), tenda.getNome());
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public Tenda getTenda() {
        return tenda;
    }

    public void setTenda(Tenda tenda) {
        this.tenda = tenda;
    }

    public int getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(int horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    public EmpregadoTendaId getId() {
        return id;
    }

    public void setId(EmpregadoTendaId id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.empregado.getNif());
        hash = 31 * hash + Objects.hashCode(this.tenda.getNome());
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
        final EmpregadoTenda other = (EmpregadoTenda) obj;
        if (!Objects.equals(this.empregado.getNif(), other.empregado.getNif())) {
            return false;
        }
        if (!Objects.equals(this.tenda.getNome(), other.tenda.getNome())) {
            return false;
        }
        return true;
    }
    
    

}

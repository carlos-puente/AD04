/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.empregadoTenda;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author carlos
 */
@Embeddable
public class EmpregadoTendaId implements Serializable {

    @Column(name = "empregado_nif")
    private String empregado;
    
    @Column(name = "tenda_nome")
    private String tenda;

    public String getEmpregado() {
        return empregado;
    }

    public void setEmpregado(String empregado) {
        this.empregado = empregado;
    }

    public String getTenda() {
        return tenda;
    }

    public void setTenda(String tenda) {
        this.tenda = tenda;
    }

    public EmpregadoTendaId() {
    }

    public EmpregadoTendaId(String empregado, String tenda) {
        this.empregado = empregado;
        this.tenda = tenda;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.empregado);
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
        final EmpregadoTendaId other = (EmpregadoTendaId) obj;
        if (!Objects.equals(this.empregado, other.empregado)) {
            return false;
        }
        if (!Objects.equals(this.tenda, other.tenda)) {
            return false;
        }
        return true;
    }

}

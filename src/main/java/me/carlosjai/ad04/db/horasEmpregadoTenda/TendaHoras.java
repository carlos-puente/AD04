/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.horasEmpregadoTenda;

/**
 *
 * @author carlos
 */
public class TendaHoras {

    private String nomeTenda;
    private String nifEmpregado;
    private Double horasSemanais;

    public TendaHoras(String nomeTenda, String nifEmpregado, Double horasSemanais) {
        this.nomeTenda = nomeTenda;
        this.nifEmpregado = nifEmpregado;
        this.horasSemanais = horasSemanais;
    }

    public String getNomeTenda() {
        return nomeTenda;
    }

    public void setNomeTenda(String nomeTenda) {
        this.nomeTenda = nomeTenda;
    }

    public String getNifEmpregado() {
        return nifEmpregado;
    }

    public void setNifEmpregado(String nifEmpregado) {
        this.nifEmpregado = nifEmpregado;
    }

    public Double getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(Double horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    @Override
    public String toString() {
        return "TendaHoras{" + "nomeTenda=" + nomeTenda + ", nifEmpregado=" + nifEmpregado + ", horasSemanais=" + horasSemanais + '}';
    }

}

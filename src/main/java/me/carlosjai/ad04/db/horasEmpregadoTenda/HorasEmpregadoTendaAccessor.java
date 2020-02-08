/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.horasEmpregadoTenda;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author carlos
 */
public class HorasEmpregadoTendaAccessor {

    public static List<TendaHoras> obterPorEmpregado(String nif) throws SQLException, ClassNotFoundException {
        return HorasEmpregadoTendaDAO.obterPorEmpregado(nif);
    }

    public static void insertarHoras(String nifEmpregado, String nomeTenda, Double numHoras) throws SQLException, ClassNotFoundException {
        HorasEmpregadoTendaDAO.insertarHorasTenda(nifEmpregado, nomeTenda, numHoras);
    }

    public static void actualizarHorasTenda(String nifEmpregado, String nomeTenda, Double numHoras) throws SQLException, ClassNotFoundException {
        HorasEmpregadoTendaDAO.actualizarHorasTenda(nifEmpregado, nomeTenda, numHoras);
    }

    public static void eliminarHorasTenda(String nifEmpregado, String nomeTenda) throws SQLException, ClassNotFoundException {
        HorasEmpregadoTendaDAO.eliminarHorasTenda(nifEmpregado, nomeTenda);
    }
}

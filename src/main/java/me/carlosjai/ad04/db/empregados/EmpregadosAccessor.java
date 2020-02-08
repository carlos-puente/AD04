/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.empregados;

import java.sql.SQLException;
import java.util.List;
import me.carlosjai.ad04.obxectos.Empregado;
import me.carlosjai.ad04.obxectos.Tenda;

/**
 *
 * @author carlos
 */
public class EmpregadosAccessor {

    public static List<Empregado> getEmpregados(List<Tenda> tendas) throws SQLException, ClassNotFoundException {
        return EmpregadosDAO.getEmpregados(tendas);
    }

    public static Empregado getEmpregado(String nif, List<Tenda> tendas) throws SQLException, ClassNotFoundException {
        return EmpregadosDAO.getEmpregado(nif, tendas);
    }

    public static void insertarEmpregado(Empregado e) throws SQLException, ClassNotFoundException {
        EmpregadosDAO.insertarEmpregado(e);
    }

    public static void actualizarEmpregado(String nif, Empregado novaInfo) throws SQLException, ClassNotFoundException {
        EmpregadosDAO.actualizarEmpregado(nif, novaInfo);
    }

    public static void eliminarEmpregado(String nif) throws SQLException, ClassNotFoundException {
        EmpregadosDAO.eliminarEmpregado(nif);
    }
}

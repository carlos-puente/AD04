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
import org.hibernate.HibernateException;

/**
 *
 * @author carlos
 */
public class EmpregadosAccessor {

    public static List<Empregado> getEmpregados() throws HibernateException {
        return EmpregadosDAO.getEmpregados();
    }

    public static Empregado getEmpregado(String nif, List<Tenda> tendas) throws HibernateException {
        return EmpregadosDAO.getEmpregado(nif);
    }

    public static void insertarEmpregado(Empregado e) throws HibernateException {
        EmpregadosDAO.insertarEmpregado(e);
    }

    public static void actualizarEmpregado(String nif, Empregado novaInfo) throws HibernateException {
    }

    public static void eliminarEmpregado(String nif) throws HibernateException {
    }
}

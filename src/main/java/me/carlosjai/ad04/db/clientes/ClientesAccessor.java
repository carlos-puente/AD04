/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.clientes;

import java.sql.SQLException;
import java.util.List;
import me.carlosjai.ad04.obxectos.Cliente;
import org.hibernate.HibernateException;

/**
 *
 * @author carlos
 */
public class ClientesAccessor {

    public static List<Cliente> getClientes() throws HibernateException {
        return ClientesDAO.getClientes();
    }

    public static Cliente getCliente(String nif) throws HibernateException {
        return ClientesDAO.getCliente(nif);
    }

    public static void insertarCliente(Cliente c) throws HibernateException {
        ClientesDAO.insertarCliente(c);
    }

    public static void actualizarCliente(String email, Cliente novaInfo) throws HibernateException {
        ClientesDAO.actualizarCliente(email, novaInfo);
    }

    public static void eliminarCliente(Cliente c) throws HibernateException {
        ClientesDAO.eliminarCliente(c);
    }
}

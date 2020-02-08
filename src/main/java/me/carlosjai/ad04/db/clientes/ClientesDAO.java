/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.clientes;

import java.util.List;
import me.carlosjai.ad04.obxectos.Cliente;
import me.carlosjai.ad04.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlos
 */
public class ClientesDAO {

    protected static List<Cliente> getClientes() throws HibernateException {
        List<Cliente> clientes = null;

        clientes = HibernateUtil.createQuery("SELECT c FROM Cliente c").list();

        return clientes;
    }

    protected static Cliente getCliente(String email) throws HibernateException {
        Cliente cliente = null;
        Query query = HibernateUtil.getSession().createQuery("SELECT c FROM Cliente c WHERE c.email=:email");
        query.setParameter("email", email);
        List<Cliente> clientes = query.list();
        if (clientes != null && !clientes.isEmpty()) {
            cliente = clientes.get(0);
        }
        return cliente;
    }

    protected static void insertarCliente(Cliente cliente) throws HibernateException {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.save(cliente);
        tran.commit();

    }

    protected static void actualizarCliente(String email, Cliente c) throws HibernateException {

        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        Query query = session.createQuery("UPDATE Cliente set email=:novoEmail, nome=:nome, apelidos=:apelidos WHERE email=:velloEmail");
        query.setParameter("novoEmail", c.getEmail());
        query.setParameter("nome", c.getNome());
        query.setParameter("apelidos", c.getApelidos());
        query.setParameter("velloEmail", email);
        tran.commit();
    }

    protected static void eliminarCliente(Cliente cliente) throws HibernateException {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.delete(cliente);
        tran.commit();
    }
}

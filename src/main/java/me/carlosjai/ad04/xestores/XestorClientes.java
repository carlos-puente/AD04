/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.xestores;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.carlosjai.ad04.db.clientes.ClientesAccessor;
import me.carlosjai.ad04.obxectos.Cliente;
import me.carlosjai.ad04.obxectos.Franquicia;
import me.carlosjai.ad04.util.Util;
import org.hibernate.HibernateException;

/**
 *
 * @author carlos
 */
public class XestorClientes {

    /**
     * Engade un novo cliente
     *
     * @param f
     */
    public static void engadirCliente(Franquicia f) {

        listarClientes(f);
        String email = Util.pedirCadea("Introduza o email do cliente a engadir: ");
        Cliente c = f.getCliente(email);
        if (c != null) {
            System.out.println("O cliente xa existe: " + c.toString());
        } else {
            try {
                c = new Cliente();
                c.setEmail(email);
                c.setNome(Util.pedirCadea("Introduza o nome do cliente: "));
                c.setApelidos(Util.pedirCadea("Introduza os apelidos do cliente: "));
                ClientesAccessor.insertarCliente(c);
                f.getClientes().add(c);
                System.out.println("Cliente insertado correctamente.");
            } catch (HibernateException ex) {
                Logger.getLogger(XestorClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Elimina un cliente
     *
     * @param f
     */
    public static void eliminarCliente(Franquicia f) {
        if (f.getClientes() != null && !f.getClientes().isEmpty()) {
            listarClientes(f);
            String email = Util.pedirCadea("Introduza o email do cliente a eliminar: ");
            Cliente c = f.getCliente(email);
            if (c != null) {
                if (Util.confirmar("Confirme o borrado de :" + c.toString())) {
                    try {
                        ClientesAccessor.eliminarCliente(c);
                        f.getClientes().remove(c);
                        System.out.println("Cliente eliminado.");
                    } catch (HibernateException ex) {
                        Logger.getLogger(XestorClientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Borrado cancelado");
                }

            } else {
                System.out.println("Non existe o cliente");
            }
        } else {
            System.out.println("Non hai clientes rexistrados na franquicia");
        }

    }

    /**
     * Lista os clientes da franquicia
     *
     * @param f
     */
    public static void listarClientes(Franquicia f) {
        f.listarClientes();
    }

    /**
     * Lista a un cliente da tenda polo seu identificador
     *
     * @param f
     * @param email
     */
    public static void listarCliente(Franquicia f, String email) {
        if (f.getClientes() != null && !f.getClientes().isEmpty()) {
            System.out.println(f.getCliente(email));
        } else {
            System.out.println("Non existen clientes con ese email");
        }
    }

}

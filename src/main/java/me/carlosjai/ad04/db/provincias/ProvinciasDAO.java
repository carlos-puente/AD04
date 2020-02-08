/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.provincias;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import me.carlosjai.ad04.db.Conector;
import me.carlosjai.ad04.obxectos.Provincia;

/**
 *
 * @author carlos
 */
public class ProvinciasDAO {

    protected static Provincia getProvinciaPorId(int id) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        Provincia p = null;
        String sql = "SELECT * FROM Provincias WHERE id=" + id;
        
        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            p = new Provincia(Integer.toString(s.getInt("id")), s.getString("nome"));
            break;
        }

        c.close();
        return p;
    }

    protected static List<Provincia> getProvincias() throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        List<Provincia> lp = new ArrayList<>();
        String sql = "SELECT * FROM Provincias ORDER BY NOME";
        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            lp.add(new Provincia(Integer.toString(s.getInt("id")), s.getString("nome")));
        }

        c.close();
        return lp;
    }

    protected static void insertarProvincia(Provincia p) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        String sql = "INSERT INTO PROVINCIAS (id, nome) values(" + p.getId() + ", '" + p.getNome() + "') ";
        stmt.executeUpdate(sql);
        stmt.close();
        c.close();
    }
}

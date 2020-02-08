/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author carlos
 */
public class Conector {

    public static Connection getConexion() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:franquicia.db");
    }

    public static void crearTablas() throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = getConexion();
        stmt = c.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS Provincias "
                + "(id INTEGER PRIMARY KEY,"
                + " nome TEXT NOT NULL UNIQUE )";
        stmt.executeUpdate(sql);

        stmt = c.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS Tendas "
                + "(nome TEXT PRIMARY KEY,"
                + " cidade TEXT NOT NULL,"
                + "id_provincia INTEGER NOT NULL, "
                + "FOREIGN KEY(id_provincia) REFERENCES provincias(id) ON DELETE CASCADE ON UPDATE CASCADE)";
        stmt.executeUpdate(sql);

        stmt = c.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS Produtos "
                + "(nome TEXT PRIMARY KEY,"
                + " descripcion TEXT NOT NULL,"
                + "prezo NUMERIC NOT NULL) ";

        stmt.executeUpdate(sql);

        stmt = c.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS Empregados "
                + "(nif TEXT PRIMARY KEY,"
                + " nome TEXT NOT NULL,"
                + "apelidos TEXT NOT NULL) ";

        stmt.executeUpdate(sql);

        stmt = c.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS Clientes "
                + "(email TEXT PRIMARY KEY,"
                + " nome TEXT NOT NULL,"
                + "apelidos TEXT NOT NULL) ";

        stmt.executeUpdate(sql);

        stmt = c.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS Stock_produto_tenda "
                + "(nome_produto TEXT,"
                + " nome_tenda TEXT,"
                + "stock NUMERIC NOT NULL,"
                + "PRIMARY KEY (nome_produto, nome_tenda),"
                + "FOREIGN KEY(nome_produto) REFERENCES produtos(nome) ON DELETE CASCADE ON UPDATE CASCADE,"
                + "FOREIGN KEY(nome_tenda) REFERENCES tendas(nome) ON DELETE CASCADE ON UPDATE CASCADE) ";

        stmt.executeUpdate(sql);

        stmt.close();

        stmt = c.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS horas_semanais_empregado_tenda "
                + "(nif TEXT,"
                + " nome_tenda TEXT,"
                + "horas_semanais NUMERIC NOT NULL,"
                + "PRIMARY KEY (nif, nome_tenda),"
                + "FOREIGN KEY(nif) REFERENCES empregados(nif) ON DELETE CASCADE ON UPDATE CASCADE,"
                + "FOREIGN KEY(nome_tenda) REFERENCES tendas(nome) ON DELETE CASCADE ON UPDATE CASCADE) ";

        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    public static void engadirPragma(Statement stmt) throws SQLException {
        stmt.executeUpdate("PRAGMA foreign_keys = ON; ");
    }
}

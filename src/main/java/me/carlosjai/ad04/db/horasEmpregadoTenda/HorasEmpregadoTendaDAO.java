/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.horasEmpregadoTenda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import me.carlosjai.ad04.db.Conector;

/**
 *
 * @author carlos
 */
public class HorasEmpregadoTendaDAO {

    protected static List<TendaHoras> obterPorEmpregado(String nif) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        List<TendaHoras> lp = new ArrayList<>();
        String sql = "SELECT * FROM horas_semanais_empregado_tenda WHERE nif='" + nif + "' ORDER BY NOME_TENDA";
        ;
        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            lp.add(new TendaHoras(s.getString("nome_tenda"), s.getString("nif"), s.getDouble("horas_semanais")));
        }

        c.close();
        return lp;
    }

    protected static void insertarHorasTenda(String nifEmpregado, String nomeTenda, Double numHoras) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        String sql = "INSERT INTO horas_semanais_empregado_tenda (nif, nome_tenda, horas_semanais) VALUES ('" + nifEmpregado + "' , '" + nomeTenda + "', " + numHoras + ")";
        stmt.executeUpdate(sql);
        c.close();

    }

    protected static void actualizarHorasTenda(String nifEmpregado, String nomeTenda, Double numHoras) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE horas_semanais_empregado_tenda SET horas_semanais= " + numHoras
                + " WHERE nif='" + nifEmpregado + "' AND nome_tenda='" + nomeTenda + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }

    protected static void eliminarHorasTenda(String nifEmpregado, String nomeTenda) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM horas_semanais_empregado_tenda WHERE nif='" + nifEmpregado + "' AND nome_tenda='" + nomeTenda + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }

}

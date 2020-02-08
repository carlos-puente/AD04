package me.carlosjai.ad04.db.empregados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import me.carlosjai.ad04.db.Conector;
import me.carlosjai.ad04.db.horasEmpregadoTenda.HorasEmpregadoTendaAccessor;
import me.carlosjai.ad04.db.horasEmpregadoTenda.TendaHoras;
import me.carlosjai.ad04.obxectos.Empregado;
import me.carlosjai.ad04.obxectos.Tenda;

/**
 *
 * @author carlos
 */
public class EmpregadosDAO {

    protected static List<Empregado> getEmpregados(List<Tenda> tendas) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        List<Empregado> lp = new ArrayList<>();
        String sql = "SELECT * FROM Empregados ORDER BY NOME, APELIDOS, NIF";
        ;
        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            Empregado e = new Empregado(s.getString("nif"), s.getString("nome"), s.getString("apelidos"));
            List<TendaHoras> lTh = HorasEmpregadoTendaAccessor.obterPorEmpregado(e.getNif());
            if (lTh != null && !lTh.isEmpty()) {
                for (TendaHoras th : lTh) {
                    Tenda t = getTendaPorNome(th.getNomeTenda(), tendas);
                    if (t != null) {
                        e.getHorasSemanaisPorTenda().put(t, th.getHorasSemanais());
                    }
                }

            }
            lp.add(e);
        }

        c.close();
        return lp;
    }

    private static Tenda getTendaPorNome(String nomeTenda, List<Tenda> tendas) {
        Tenda t = null;
        if (tendas != null && !tendas.isEmpty()) {
            for (Tenda tenda : tendas) {
                if (tenda.getNome().equalsIgnoreCase(nomeTenda)) {
                    t = tenda;
                    break;
                }
            }
        }
        return t;
    }

    protected static Empregado getEmpregado(String nif, List<Tenda> tendas) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        Empregado e = null;
        String sql = "SELECT * FROM Empregados WHERE nif='" + nif + "'";

        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            e = new Empregado(s.getString("nif"), s.getString("nome"), s.getString("apelidos"));
            if (tendas != null && !tendas.isEmpty()) {
                List<TendaHoras> lTh = HorasEmpregadoTendaAccessor.obterPorEmpregado(e.getNif());
                if (lTh != null && !lTh.isEmpty()) {
                    for (TendaHoras th : lTh) {
                        Tenda t = getTendaPorNome(th.getNomeTenda(), tendas);
                        if (t != null) {
                            e.getHorasSemanaisPorTenda().put(t, th.getHorasSemanais());
                        }
                    }
                }
            }
            break;
        }
        c.close();
        return e;
    }

    protected static void insertarEmpregado(Empregado e) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        String sql = "INSERT INTO Empregados (nif, nome, apelidos) VALUES ('" + e.getNif() + "' , '" + e.getNome() + "', '" + e.getApelidos() + "')";
        stmt.executeUpdate(sql);
        c.close();
    }

    protected static void actualizarEmpregado(String nif, Empregado novaInfo) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Empregados SET nif = '" + novaInfo.getNif() + "', nome = '" + novaInfo.getNome() + "', apelidos= '" + novaInfo.getApelidos() + "' WHERE nif='" + nif + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }

    protected static void eliminarEmpregado(String nif) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Empregados WHERE nif='" + nif + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }
}

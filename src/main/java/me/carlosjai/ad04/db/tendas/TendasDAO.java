package me.carlosjai.ad04.db.tendas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.carlosjai.ad04.db.Conector;
import me.carlosjai.ad04.db.provincias.ProvinciasAccessor;
import me.carlosjai.ad04.db.stockProdutoTenda.StockProdutosTenda;
import me.carlosjai.ad04.db.stockProdutoTenda.StockProdutosTendasAccessor;
import me.carlosjai.ad04.obxectos.Produto;
import me.carlosjai.ad04.obxectos.Tenda;

/**
 *
 * @author carlos
 */
public class TendasDAO {

    protected static List<Tenda> getTendas(List<Produto> lProdutos) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        List<Tenda> lp = new ArrayList<>();
        String sql = "SELECT * FROM Tendas ORDER BY NOME, CIDADE";
        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            Tenda e = new Tenda(s.getString("nome"), s.getString("cidade"), ProvinciasAccessor.getProvinciaPorId(s.getInt("id_provincia")));
            List<StockProdutosTenda> lStockProdutos = StockProdutosTendasAccessor.obterPorTenda(e.getNome());
            if (lStockProdutos != null && !lStockProdutos.isEmpty()) {
                for (StockProdutosTenda stock : lStockProdutos) {
                    Produto p = getProdutoPorNome(stock.getNomeProduto(), lProdutos);
                    if (p != null) {
                        e.getStockProdutos().put(p, stock.getStock());
                    }
                }

            }
            lp.add(e);
        }
        c.close();
        return lp;
    }

    protected static Tenda getTenda(String nomeTenda, List<Produto> lProdutos) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        Tenda t = null;
        String sql = "SELECT * FROM Tendas where nome='" + nomeTenda + "'";
        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            t = new Tenda(s.getString("nome"), s.getString("cidade"), ProvinciasAccessor.getProvinciaPorId(s.getInt("id_provincia")));
            if (t != null && lProdutos != null && !lProdutos.isEmpty()) {
                List<StockProdutosTenda> lStockProdutos = StockProdutosTendasAccessor.obterPorTenda(t.getNome());
                if (lStockProdutos != null && !lStockProdutos.isEmpty()) {
                    for (StockProdutosTenda stock : lStockProdutos) {
                        Produto p = getProdutoPorNome(stock.getNomeProduto(), lProdutos);
                        if (p != null) {
                            t.getStockProdutos().put(p, stock.getStock());
                        }
                    }
                }
                break;
            }
        }
        c.close();
        return t;
    }

    protected static void insertarTenda(Tenda t) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        String sql = "INSERT INTO Tendas (nome, cidade, id_provincia) VALUES ('" + t.getNome() + "' , '" + t.getCidade() + "', " + t.getProvincia().getId() + ")";
        stmt.executeUpdate(sql);
        c.close();
    }

    private static Produto getProdutoPorNome(String nomeProduto, List<Produto> lProdutos) {
        Produto p = null;
        if (lProdutos != null && !lProdutos.isEmpty()) {
            for (Produto prod : lProdutos) {
                if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                    p = prod;
                    break;
                }
            }
        }
        return p;
    }

    protected static void actualizarTenda(String nome, Tenda novaInfo) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Tendas SET nome = '" + novaInfo.getNome() + "', cidade = '" + novaInfo.getCidade() + "', id_provincia= " + novaInfo.getProvincia().getId() + " WHERE nome='" + nome + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }

    protected static void eliminarTenda(String nome) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Tendas WHERE nome='" + nome + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }
}

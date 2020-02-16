package me.carlosjai.ad04.xestores;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.carlosjai.ad04.db.produtos.ProdutosAccessor;
import me.carlosjai.ad04.obxectos.Franquicia;
import me.carlosjai.ad04.obxectos.Produto;
import me.carlosjai.ad04.obxectos.StockProdutoJSON;
import me.carlosjai.ad04.obxectos.StockProdutoTendaJSON;
import me.carlosjai.ad04.produtoTenda.ProdutoTenda;
import me.carlosjai.ad04.util.Util;

/**
 *
 * @author carlos
 */
public class XestorProdutos {

    public static void engadirProduto(Franquicia f) {
        f.listarProdutos();
        String nomeProduto = Util.pedirCadea("Introduza nome do produto: ");

        if (f.getProduto(nomeProduto) != null || ProdutosAccessor.obterProduto(nomeProduto) != null) {
            System.out.println("Error: xa existe o produto introducida");
        } else {

            String descripcion = Util.pedirCadea("Introduza a descripcion do produto: ");
            Double prezo = Util.pedirDouble("Introduza o prezo do produto: ");
            Produto produto = new Produto(nomeProduto, descripcion, prezo);
            ProdutosAccessor.insertarProduto(produto);
            f.getProdutos().add(produto);
        }
    }

    public static void eliminarProduto(Franquicia f) {
        f.listarProdutos();
        if (!f.getProdutos().isEmpty()) {
            Produto produto = pedirProduto(f, "", "Introduza o nome do produto a eliminar: ");
            if (produto == null) {
                System.out.println("Non existe un produto có nome especificado");
            } else {
                if (Util.confirmar("Confirme o borrado de: " + produto.getNome())) {
                    f.getProdutos().remove(produto);
                    ProdutosAccessor.eliminarProduto(produto);
                    System.out.println("Eliminado: " + produto);
                } else {
                    System.out.println("Borrado cancelado.");
                }
            }
        } else {
            System.out.println("Non hai produtos rexistrados.");
        }
    }

    public static void listarProdutos(Franquicia f) {
        f.listarProdutos();
    }

    public static void listarProduto(Franquicia f) {
        Produto produto = pedirProduto(f, "", "Introduza o nome do produto: ");
        System.out.println(produto == null ? "Non existe o produto có nome especificado" : produto.toString());
    }

    public static Produto pedirProduto(Franquicia f, String nome, String mensaxe) {
        return f.getProduto(Util.estaBaleira(nome) ? Util.pedirCadea(mensaxe) : nome);
    }

    public static void xerarInformeStock(Franquicia f) {

        if (f.getProdutos() != null && !f.getProdutos().isEmpty()) {
            List<StockProdutoJSON> lStockProduto = new ArrayList<StockProdutoJSON>();
            for (Produto p : f.getProdutos()) {
                System.out.println("1");
                StockProdutoJSON stockProduto = new StockProdutoJSON(p.getNome(), p.getDescripcion());
                if (p.getProdutoTenda() != null && !p.getProdutoTenda().isEmpty()) {
                    for (ProdutoTenda pT : p.getProdutoTenda()) {
                        System.out.println("2");
                        stockProduto.addStockTenda(new StockProdutoTendaJSON(pT.getTenda().getNome(), pT.getStockFormatted()));
                    }
                }
                lStockProduto.add(stockProduto);
            }
            Gson gson = new Gson();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("reporteStock.json"));
                writer.write(gson.toJson(lStockProduto));
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(XestorProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Non hai produtos rexistrados.");
        }
    }

}

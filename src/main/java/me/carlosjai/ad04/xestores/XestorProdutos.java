package me.carlosjai.ad04.xestores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.carlosjai.ad04.db.produtos.ProdutosAccessor;
import me.carlosjai.ad04.obxectos.Franquicia;
import me.carlosjai.ad04.obxectos.Produto;
import me.carlosjai.ad04.util.Util;

/**
 *
 * @author carlos
 */
public class XestorProdutos {

    public static void engadirProduto(Franquicia f) {
        f.listarProdutos();
        String nomeProduto = Util.pedirCadea("Introduza nome do produto: ");
        try {
            if (f.getProduto(nomeProduto) != null || ProdutosAccessor.obterProduto(nomeProduto) != null) {
                System.out.println("Error: xa existe o produto introducida");
            } else {

                String descripcion = Util.pedirCadea("Introduza a descripcion do produto: ");
                Double prezo = Util.pedirDouble("Introduza o prezo do produto: ");
                Produto produto = new Produto(nomeProduto, descripcion, prezo);
                ProdutosAccessor.insertarProduto(produto);
                f.getProdutos().add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XestorRSS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(XestorRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void eliminarProduto(Franquicia f) {
        f.listarProdutos();
        if (!f.getProdutos().isEmpty()) {
            try {
                Produto produto = pedirProduto(f, "", "Introduza o nome do produto a eliminar: ");
                if (produto == null) {
                    System.out.println("Non existe un produto có nome especificado");
                } else {
                    if (Util.confirmar("Confirme o borrado de: " + produto.getNome())) {
                        f.getProdutos().remove(produto);
                        ProdutosAccessor.eliminarProduto(produto.getNome());
                        System.out.println("Eliminado: " + produto);
                    } else {
                        System.out.println("Borrado cancelado.");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(XestorRSS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(XestorRSS.class.getName()).log(Level.SEVERE, null, ex);
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

}

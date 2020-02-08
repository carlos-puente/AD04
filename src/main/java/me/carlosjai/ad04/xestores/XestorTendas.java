/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.xestores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.carlosjai.ad04.db.stockProdutoTenda.StockProdutosTendasAccessor;
import me.carlosjai.ad04.db.tendas.TendasAccessor;
import me.carlosjai.ad04.obxectos.Franquicia;
import me.carlosjai.ad04.obxectos.Produto;
import me.carlosjai.ad04.obxectos.Provincia;
import me.carlosjai.ad04.obxectos.Tenda;
import me.carlosjai.ad04.util.Util;

/**
 *
 * @author carlos
 */
public class XestorTendas {

    /**
     * Engade unha tenda nunha franquicia. Se xa existe unha tenda có nome
     * solicitado, dase a opción de actualizala.
     *
     * @param f
     */
    public static void engadirTenda(Franquicia f) {
        listarTendas(f);
        String nomeTenda = Util.pedirCadea("Introduza nome da tenda: ");
        try {
            if (f.getTenda(nomeTenda) != null || TendasAccessor.getTenda(nomeTenda, null) != null) {
                System.out.println("Error: xa existe a tenda introducida");
            } else {

                String cidade = Util.pedirCadea("Introduza a localidade da tenda: ");
                f.listarProvincias();

                Provincia provincia = null;
                do {
                    String idProvincia = Util.pedirCadea("Introduza un id de provincia: ");
                    provincia = f.getProvincia(idProvincia);
                    if (provincia == null) {
                        System.out.println("Error: non existe a provincia có id especificado ('" + idProvincia + "'), volva a intentalo.");
                    }
                } while (provincia == null);
                Tenda t = new Tenda(nomeTenda, cidade, provincia);
                TendasAccessor.insertarTenda(t);
                f.getTendas().add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XestorRSS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(XestorRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Elimina unha tenda da franquicia
     *
     * @param f
     */
    public static void eliminarTenda(Franquicia f) {
        listarTendas(f);
        if (!f.getTendas().isEmpty()) {
            Tenda tenda = pedirTenda(f, "", "Introduza o nome da tenda a eliminar: ");
            if (tenda != null) {
                if (Util.confirmar("Confirme o borrado de :" + tenda.toString())) {
                    try {
                        TendasAccessor.eliminarTenda(tenda.getNome());
                        System.out.println("Tenda " + tenda.getNome() + " eliminada");
                        f.getTendas().remove(tenda);
                    } catch (SQLException ex) {
                        Logger.getLogger(XestorRSS.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(XestorRSS.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Borrado cancelado");
                }
            } else {
                System.out.println("ERROR: A tenda solicitada non existe.");
            }
        } else {
            System.out.println("Non hai tendas rexistradas.");
        }

    }

    /**
     * Obtén unha tenda polo seu nome.
     *
     * @param f Franquicia
     * @param nome Nome da tenda
     * @param mensaxe Mensaxe a amosar (non se utiliza se recibimos nome)
     * @return
     */
    static Tenda pedirTenda(Franquicia f, String nome, String mensaxe) {
        return f.getTenda(Util.estaBaleira(nome) ? Util.pedirCadea(mensaxe) : nome);
    }

    /**
     * Lista todas as tendas da Franquicia
     *
     * @param f
     */
    public static void listarTendas(Franquicia f) {
        f.listarTendas();
    }

    /**
     * Lista a información dunha tenda polo seu nome
     *
     * @param f
     * @param nomeTenda
     */
    public static void listarTenda(Franquicia f, String nomeTenda) {
        Tenda tenda = pedirTenda(f, nomeTenda, "");
        if (tenda != null) {
            System.out.println(tenda.toString());
        } else {
            System.out.println("Non existe a tenda solicitada");
        }
    }

    public static void amosarProdutos(Franquicia f) {
        listarTendas(f);
        if (!f.getTendas().isEmpty()) {
            Tenda tenda = pedirTenda(f, "", "Introduza o nome da tenda: ");
            if (tenda != null) {
                if (tenda.getStockProdutos() == null || tenda.getStockProdutos().isEmpty()) {
                    System.out.println("Non hai produtos rexistrados na tenda.");
                } else {
                    tenda.listarProdutos();
                }
            } else {
                System.out.println("ERROR: A tenda solicitada non existe.");
            }
        } else {
            System.out.println("Non hai tendas rexistradas.");
        }
    }

    public static void engadirProduto(Franquicia f) {
        listarTendas(f);
        if (!f.getTendas().isEmpty()) {
            Tenda tenda = pedirTenda(f, "", "Introduza o nome da tenda: ");
            if (tenda != null) {
                System.out.println("Lista de produtos da franquicia: ");
                f.listarProdutos();
                System.out.println("Lista de productos da tenda: ");
                tenda.listarProdutos();
                Produto p = XestorProdutos.pedirProduto(f, "", "introduza nome do produto a engadir: ");
                if (p == null) {
                    System.out.println("Non existe un produto có nome especificado");
                } else if (tenda.getStockProdutos().containsKey(p)) {
                    System.out.println("Erro: O produto xa existe na tenda");
                } else {
                    try {
                        Double stock = Util.pedirDouble("introduza stock a rexistrar: ");
                        StockProdutosTendasAccessor.insertarStock(p.getNome(), tenda.getNome(), stock);
                        tenda.getStockProdutos().put(p, stock);
                        System.out.println("Produto rexistrado correctamente, cun stock de " + stock);
                    } catch (SQLException ex) {
                        Logger.getLogger(XestorTendas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(XestorTendas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                System.out.println("ERROR: A tenda solicitada non existe.");
            }
        } else {
            System.out.println("Non hai tendas rexistradas.");
        }
    }

    public static void actualizarStockProduto(Franquicia f) {
        listarTendas(f);
        if (!f.getTendas().isEmpty()) {
            Tenda tenda = pedirTenda(f, "", "Introduza o nome da tenda: ");
            if (tenda != null) {
                System.out.println("Lista de productos da tenda: ");
                tenda.listarProdutos();
                Produto p = XestorProdutos.pedirProduto(f, "", "introduza nome do produto a modificar stock: ");
                if (p == null) {
                    System.out.println("Non existe un produto có nome especificado");
                } else if (!tenda.getStockProdutos().containsKey(p)) {
                    System.out.println("Erro: O produto non existe na tenda");
                } else {
                    try {
                        Double stock = Util.pedirDouble("introduza stock a rexistrar: ");
                        StockProdutosTendasAccessor.actualizarStockProdutoTenda(p.getNome(), tenda.getNome(), stock);
                        tenda.getStockProdutos().put(p, stock);
                        System.out.println("Produto rexistrado correctamente, cun stock de " + stock);
                    } catch (SQLException ex) {
                        Logger.getLogger(XestorTendas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(XestorTendas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                System.out.println("ERROR: A tenda solicitada non existe.");
            }
        } else {
            System.out.println("Non hai tendas rexistradas.");
        }
    }

    public static void amosarStockProduto(Franquicia f) {
        listarTendas(f);
        if (!f.getTendas().isEmpty()) {
            Tenda tenda = pedirTenda(f, "", "Introduza o nome da tenda: ");
            if (tenda != null) {
                System.out.println("Lista de productos da tenda: ");
                tenda.listarProdutos();
                Produto p = XestorProdutos.pedirProduto(f, "", "introduza nome do produto:");
                if (p == null) {
                    System.out.println("Non existe un produto có nome especificado");
                } else if (!tenda.getStockProdutos().containsKey(p)) {
                    System.out.println("Erro: O produto non existe na tenda");
                } else {
                    tenda.amosarStockProduto(p);
                }
            } else {
                System.out.println("ERROR: A tenda solicitada non existe.");
            }
        } else {
            System.out.println("Non hai tendas rexistradas.");
        }
    }

    public static void eliminarProduto(Franquicia f) {
        listarTendas(f);
        if (!f.getTendas().isEmpty()) {
            Tenda tenda = pedirTenda(f, "", "Introduza o nome da tenda: ");
            if (tenda != null) {
                System.out.println("Lista de produtos da franquicia: ");
                f.listarProdutos();
                System.out.println("Lista de productos da tenda: ");
                tenda.listarProdutos();
                Produto p = XestorProdutos.pedirProduto(f, "", "introduza nome do produto a eliminar da tenda: ");
                if (p == null) {
                    System.out.println("Non existe un produto có nome especificado");
                } else if (!tenda.getStockProdutos().containsKey(p)) {
                    System.out.println("Erro: O produto non existe na tenda");
                } else {
                    try {
                        if (Util.confirmar("Confirme o borrado de :" + p.getNome() + " na tenda: " + tenda.getNome())) {
                            StockProdutosTendasAccessor.eliminarStockProdutoTenda(p.getNome(), tenda.getNome());
                            tenda.getStockProdutos().remove(p);
                            System.out.println("Produto eliminado correctamente da tenda.");
                        } else {
                            System.out.println("Borrado cancelado.");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(XestorTendas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(XestorTendas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                System.out.println("ERROR: A tenda solicitada non existe.");
            }
        } else {
            System.out.println("Non hai tendas rexistradas.");
        }
    }

    public static void listarEmpregadosDeTenda(Franquicia f) {
        f.listarTendas();
        f.listarEmpregadosDeTenda(pedirTenda(f, "", "Introduza o nome da tenda "));
    }

}

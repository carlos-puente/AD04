/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.xestores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.carlosjai.ad04.db.empregados.EmpregadosAccessor;
import me.carlosjai.ad04.db.horasEmpregadoTenda.HorasEmpregadoTendaAccessor;
import me.carlosjai.ad04.obxectos.Empregado;
import me.carlosjai.ad04.obxectos.Franquicia;
import me.carlosjai.ad04.obxectos.Tenda;
import me.carlosjai.ad04.util.Util;

/**
 *
 * @author carlos
 */
public class XestorEmpregados {

    public static void engadirEmpregado(Franquicia f) {
        listarEmpregados(f);
        String nif = Util.pedirCadea("Introduza o nif do empregado a engadir: ");
        Empregado e = f.getEmpregado(nif);
        if (e != null) {
            System.out.println("Error, xa existe o empregado: " + e.toString());
        } else {
            try {
                e = new Empregado();
                e.setNif(nif);
                e.setNome(Util.pedirCadea("Introduza o nome do empregado: "));
                e.setApelidos(Util.pedirCadea("Introduza os apelidos do empregado: "));
                EmpregadosAccessor.insertarEmpregado(e);
                f.getEmpregados().add(e);
                System.out.println("Empregado rexistrado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(XestorEmpregados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(XestorEmpregados.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void eliminarEmpregado(Franquicia f) {
        if (f.getClientes() != null && !f.getClientes().isEmpty()) {
            listarEmpregados(f);
            String nif = Util.pedirCadea("Introduza o nif do empregado a eliminar: ");
            Empregado e = f.getEmpregado(nif);
            if (e != null) {
                if (Util.confirmar("Confirme o borrado de :" + e.toString())) {
                    try {
                        EmpregadosAccessor.eliminarEmpregado(nif);
                        f.getEmpregados().remove(e);
                        System.out.println("Empregado eliminado.");
                    } catch (SQLException ex) {
                        Logger.getLogger(XestorClientes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(XestorClientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Borrado cancelado");
                }

            } else {
                System.out.println("Non existe o empregado");
            }
        } else {
            System.out.println("Non hai clientes rexistrados na franquicia");
        }
    }

    public static void rexistrarHorasTenda(Franquicia f) {
        XestorTendas.listarTendas(f);
        if (!f.getTendas().isEmpty()) {
            Tenda tenda = XestorTendas.pedirTenda(f, "", "Introduza o nome da tenda: ");
            if (tenda != null) {
                f.listarEmpregados();
                f.listarEmpregadosDeTenda(tenda);
                Empregado e = pedirEmpregado(f, "", "introduza NIF do empregado a rexistrar: ");
                if (e == null) {
                    System.out.println("Non existe un empregado có NIF especificado");
                } else if (e.getHorasSemanaisPorTenda().containsKey(tenda)) {
                    System.out.println("Erro: O empregado xa existe na tenda");
                } else {
                    try {
                        Double horas = Util.pedirDouble("introduza horas a rexistrar: ");
                        HorasEmpregadoTendaAccessor.insertarHoras(e.getNif(), tenda.getNome(), horas);
                        e.getHorasSemanaisPorTenda().put(tenda, horas);
                        System.out.println("Empregado rexistrado correctamente, cunhas horas semanais de " + horas);
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

    public static void modificarHorasTenda(Franquicia f) {
        XestorTendas.listarTendas(f);
        if (!f.getTendas().isEmpty()) {
            Tenda tenda = XestorTendas.pedirTenda(f, "", "Introduza o nome da tenda: ");
            if (tenda != null) {
                f.listarEmpregadosDeTenda(tenda);
                Empregado e = pedirEmpregado(f, "", "introduza nif do empregado do que modificar horas: ");
                if (e == null) {
                    System.out.println("Non existe un empregado có nif especificado");
                } else if (!e.getHorasSemanaisPorTenda().containsKey(tenda)) {
                    System.out.println("Erro: O empregado non existe na tenda");
                } else {
                    try {
                        Double horas = Util.pedirDouble("introduza horas a rexistrar: ");
                        HorasEmpregadoTendaAccessor.actualizarHorasTenda(e.getNif(), tenda.getNome(), horas);
                        e.getHorasSemanaisPorTenda().put(tenda, horas);
                        System.out.println("Empregado actualizado correctamente, cunhas horas semanais de " + horas);
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

    public static void eliminarHorasTenda(Franquicia f) {
        XestorTendas.listarTendas(f);
        if (!f.getTendas().isEmpty()) {
            Tenda tenda = XestorTendas.pedirTenda(f, "", "Introduza o nome da tenda: ");
            if (tenda != null) {
                f.listarEmpregadosDeTenda(tenda);
                Empregado e = pedirEmpregado(f, "", "introduza nome do empregado do que eliminar horas: ");
                if (e == null) {
                    System.out.println("Non existe un empregado có nome especificado");
                } else if (!e.getHorasSemanaisPorTenda().containsKey(tenda)) {
                    System.out.println("Erro: O empregado non existe na tenda");
                } else {
                    try {
                        if (Util.confirmar("Confirme o borrado de :" + e.getNif() + " na tenda: " + tenda.getNome())) {
                            HorasEmpregadoTendaAccessor.eliminarHorasTenda(e.getNif(), tenda.getNome());
                            e.getHorasSemanaisPorTenda().remove(tenda);
                            System.out.println("Empregado eliminado correctamente da tenda.");
                        } else {
                            System.out.println("Borrado cancelado.");
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(XestorEmpregados.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(XestorEmpregados.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                System.out.println("ERROR: A tenda solicitada non existe.");
            }
        } else {
            System.out.println("Non hai tendas rexistradas.");
        }
    }

    public static void listarEmpregados(Franquicia f) {
        f.listarEmpregados();
    }

    public static void listarEmpregado(Franquicia f) {
        f.listarEmpregado(pedirEmpregado(f, "", "introduza empregado: "));
    }

    private static Empregado pedirEmpregado(Franquicia f, String nome, String mensaxe) {
        return f.getEmpregado(Util.estaBaleira(nome) ? Util.pedirCadea(mensaxe) : nome);
    }

}

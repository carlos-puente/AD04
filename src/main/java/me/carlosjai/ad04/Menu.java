package me.carlosjai.ad04;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.carlosjai.ad04.db.FranquiciaDB;
import me.carlosjai.ad04.xestores.XestorRSS;
import me.carlosjai.ad04.obxectos.Franquicia;
import me.carlosjai.ad04.util.Util;
import me.carlosjai.ad04.xestores.XestorClientes;
import me.carlosjai.ad04.xestores.XestorEmpregados;
import me.carlosjai.ad04.xestores.XestorProdutos;
import me.carlosjai.ad04.xestores.XestorTendas;

/**
 *
 *
 *
 * Productos:
 *
 * Tendas:
 *
 * Clientes:
 *
 * Ler os titulares do periódico El Pais. (Explícase máis abaixo) Sair da
 * aplicación.
 *
 *
 * @author carlos
 */
public class Menu {

    private static Franquicia f = null;

    public static void menuPrincipal(Franquicia franquicia) {
        int opcion = -1;
        f = franquicia != null ? franquicia : new Franquicia();
        do {
            try {
                Util.limparPantalla();
                System.out.println("1. Xestionar tendas");
                System.out.println("2. Xestionar produtos");
                System.out.println("3. Xestionar empregados");
                System.out.println("4. Xestionar clientes");
                System.out.println("5. Xerar informe de stock");
                System.out.println("6. Ver titulares de El País (online)");
                System.out.println("0. Saír");
                do {
                    opcion = Util.pedirEnteiro("introduza unha opción: ");
                    if (opcion < 0 || opcion > 6) {
                        System.out.println("Error: introduza unha opción entre 0 e 6");
                    }
                } while (opcion < 0 || opcion > 6);
                switch (opcion) {
                    case 1:
                        menuTendas(f);
                        break;
                    case 2:
                        menuProdutos(f);
                        break;
                    case 3:
                        menuEmpregados(f);
                        break;
                    case 4:
                        menuClientes(f);
                        break;
                    case 6:
                        XestorRSS.amosarTitulares();
                        Util.pausa();
                        break;
                    case 5:
                        XestorProdutos.xerarInformeStock(f);
                        break;
                    case 0:
                        System.out.println("Saíndo...");
                        break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (opcion != 0);

    }

    /**
     *
     * @param f - Franquicia.
     *
     *
     * Engadir unha nova tenda. Mostrar as tendas. Eliminar unha tenda. Mostrar
     * os productos dunha tenda. Engadir un producto a unha tenda. Actualizar o
     * stock dun producto nunha determinada tenda. Mostrar o stock dun producto
     * dunha tenda. Eliminar un producto dunha determinada tenda.
     *
     */
    public static void menuTendas(Franquicia f) throws SQLException, ClassNotFoundException {
        int opcion = -1;
        do {
            Util.limparPantalla();
            System.out.println("1. Engadir unha tenda");
            System.out.println("2. Eliminar unha tenda");
            System.out.println("3. Amosar produtos dunha tenda");
            System.out.println("4. Engadir produto a tenda");
            System.out.println("5. Actualizar stock de produto nunha tenda");
            System.out.println("6. Amosar stock de un produto nunha tenda");
            System.out.println("7. Eliminar produto de tenda");
            System.out.println("8. Listar tendas");
            System.out.println("9. Listar unha tenda");
            System.out.println("10. Listar empregados tenda");
            System.out.println("0. Volver");
            do {
                opcion = Util.pedirEnteiro("introduza unha opción: ");
                if (opcion < 0 || opcion > 10) {
                    System.out.println("Error: introduza unha opción entre 0 e 10");
                    Util.pausa();
                }
            } while (opcion < 0 || opcion > 10);
            Util.limparPantalla();
            switch (opcion) {
                case 1:
                    XestorTendas.engadirTenda(f);
                    FranquiciaDB.recargarDatosTendas(f);
                    break;
                case 2:
                    XestorTendas.eliminarTenda(f);
                    FranquiciaDB.recargarDatosTendas(f);
                    break;
                case 3:
                    XestorTendas.amosarProdutos(f);
                    break;
                case 4:
                    XestorTendas.engadirProduto(f);
                    break;
                case 5:
                    XestorTendas.actualizarStockProduto(f);
                    FranquiciaDB.recargarDatosTendas(f);
                    break;
                case 6:
                    XestorTendas.amosarStockProduto(f);
                    break;
                case 7:
                    XestorTendas.eliminarProduto(f);
                    FranquiciaDB.recargarDatosTendas(f);
                    break;
                case 8:
                    XestorTendas.listarTendas(f);
                    break;
                case 9:
                    XestorTendas.listarTenda(f, Util.pedirCadea("Introduza nome da tenda: "));
                    break;
                case 10:
                    XestorTendas.listarEmpregadosDeTenda(f);
                    break;
            }
            if (opcion != 0) {
                Util.pausa();
            }
        } while (opcion != 0);
    }

    /**
     * Engadir un producto. Mostrar os productos da franquicia. Eliminar un
     * producto.
     *
     * @param f
     */
    public static void menuProdutos(Franquicia f) throws SQLException, ClassNotFoundException {
        int opcion = -1;
        do {
            Util.limparPantalla();
            System.out.println("1. Engadir un producto.");
            System.out.println("2. Eliminar un producto.");
            System.out.println("3. Listar produtos");
            System.out.println("4. Listar un produto");
            System.out.println("0. Volver");
            do {
                opcion = Util.pedirEnteiro("introduza unha opción: ");
                if (opcion < 0 || opcion > 4) {
                    System.out.println("Error: introduza unha opción entre 0 e 4");
                    Util.pausa();
                }
            } while (opcion < 0 || opcion > 4);
            Util.limparPantalla();
            switch (opcion) {
                case 1:
                    XestorProdutos.engadirProduto(f);
                    FranquiciaDB.recargarDatosProdutos(f);
                    break;
                case 2:
                    XestorProdutos.eliminarProduto(f);
                    FranquiciaDB.recargarDatosProdutos(f);
                    break;
                case 3:
                    XestorProdutos.listarProdutos(f);
                    break;
                case 4:
                    XestorProdutos.listarProduto(f);
                    break;
            }
            if (opcion != 0) {
                Util.pausa();
            }
        } while (opcion != 0);
    }

    private static void menuEmpregados(Franquicia f) throws SQLException, ClassNotFoundException {
        int opcion = -1;
        do {
            Util.limparPantalla();
            System.out.println("1. Engadir un empregado.");
            System.out.println("2. Eliminar un empregado.");
            System.out.println("3. Rexistrar horas empregado en tenda.");
            System.out.println("4. Modificar horas empregado en tenda.");
            System.out.println("5. Eliminar horas empregado en tenda.");
            System.out.println("6. Listar empregados");
            System.out.println("7. Listar un empregado");
            System.out.println("0. Volver");
            do {
                opcion = Util.pedirEnteiro("introduza unha opción: ");
                if (opcion < 0 || opcion > 7) {
                    System.out.println("Error: introduza unha opción entre 0 e 7");
                    Util.pausa();
                }
            } while (opcion < 0 || opcion > 7);
            Util.limparPantalla();
            switch (opcion) {
                case 1:
                    XestorEmpregados.engadirEmpregado(f);
                    FranquiciaDB.recargarDatosEmpregados(f);
                    break;
                case 2:
                    XestorEmpregados.eliminarEmpregado(f);
                    FranquiciaDB.recargarDatosEmpregados(f);
                    break;
                case 3:
                    XestorEmpregados.rexistrarHorasTenda(f);
                    FranquiciaDB.recargarDatosEmpregados(f);
                    break;
                case 4:
                    XestorEmpregados.modificarHorasTenda(f);
                    FranquiciaDB.recargarDatosEmpregados(f);
                    break;
                case 5:
                    XestorEmpregados.eliminarHorasTenda(f);
                    FranquiciaDB.recargarDatosEmpregados(f);
                    break;
                case 6:
                    XestorEmpregados.listarEmpregados(f);
                    break;
                case 7:
                    XestorEmpregados.listarEmpregado(f);
                    break;
            }
            if (opcion != 0) {
                Util.pausa();
            }
        } while (opcion != 0);
    }

    private static void menuClientes(Franquicia f) {
        int opcion = -1;
        do {
            Util.limparPantalla();
            System.out.println("1. Engadir un cliente á franquicia.");
            System.out.println("2. Eliminar un cliente da franquicia.");
            System.out.println("3. Listar clientes");
            System.out.println("4. Listar cliente por email");
            System.out.println("0. Volver");
            do {
                opcion = Util.pedirEnteiro("introduza unha opción: ");
                if (opcion < 0 || opcion > 4) {
                    System.out.println("Error: introduza unha opción entre 0 e 4");
                    Util.pausa();
                }
            } while (opcion < 0 || opcion > 4);
            Util.limparPantalla();
            switch (opcion) {
                case 1:
                    XestorClientes.engadirCliente(f);
                    break;
                case 2:
                    XestorClientes.eliminarCliente(f);
                    break;
                case 3:
                    XestorClientes.listarClientes(f);
                    break;
                case 4:
                    XestorClientes.listarCliente(f, Util.pedirCadea("Introduza email do cliente: "));
                    break;
            }
            if (opcion != 0) {
                Util.pausa();
            }
        } while (opcion != 0);
    }
}

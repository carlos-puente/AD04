/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.provincias;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import me.carlosjai.ad04.obxectos.Provincia;
import me.carlosjai.ad04.util.JsonUtils;

/**
 *
 * @author carlos
 */
public class ProvinciasAccessor {

    public static Provincia getProvinciaPorId(int id) throws ClassNotFoundException, SQLException {
        return ProvinciasDAO.getProvinciaPorId(id);
    }

    public static List<Provincia> getProvincias() throws SQLException, ClassNotFoundException {
        return ProvinciasDAO.getProvincias();
    }

    /**
     * Carga as provincias dun JSON. 1. Cargamos as provincias de JSON 2.
     * Comprobamos se cada un dos id das provincias xa existe na base de datos.
     * Se non existe o insertamos.
     *
     * @throws FileNotFoundException
     */
    public static void cargarProvinciasDeJson() throws FileNotFoundException, ClassNotFoundException, SQLException {
        Provincia[] aProvincias = JsonUtils.recuperarProvincias();
        if (aProvincias != null && aProvincias.length > 0) {
            for (Provincia p : aProvincias) {
                Provincia pDB = getProvinciaPorId(Integer.parseInt(p.getId()));
                if (pDB == null) {
                    insertarProvincia(p);
                }
            }
        }
    }

    private static void insertarProvincia(Provincia p) throws SQLException, ClassNotFoundException {
        ProvinciasDAO.insertarProvincia(p);
    }
}

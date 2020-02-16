/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.provincias;

import java.io.FileNotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import me.carlosjai.ad04.obxectos.Provincia;
import me.carlosjai.ad04.util.JsonUtils;
import org.hibernate.HibernateException;

/**
 *
 * @author carlos
 */
@Transactional
public class ProvinciasAccessor {

    public static Provincia getProvincia(int id) throws HibernateException {
        return ProvinciasDAO.getProvincia(id);
    }

    public static List<Provincia> getProvincias() throws HibernateException {
        return ProvinciasDAO.getProvincias();
    }

    /**
     * Carga as provincias dun JSON. 1. Cargamos as provincias de JSON 2.
     * Comprobamos se cada un dos id das provincias xa existe na base de datos.
     * Se non existe o insertamos.
     *
     * @throws FileNotFoundException
     */
    public static void cargarProvinciasDeJson() throws HibernateException, FileNotFoundException {
        Provincia[] aProvincias = JsonUtils.recuperarProvincias();
        if (aProvincias != null && aProvincias.length > 0) {
            for (Provincia p : aProvincias) {
                Provincia pDB = getProvincia(Integer.parseInt(p.getId()));
                if (pDB == null) {
                    insertarProvincia(p);
                }
            }
        }
    }

    private static void insertarProvincia(Provincia p) throws HibernateException {
        ProvinciasDAO.insertarProvincia(p);
    }

    /**
     * Realizamos a creación de provincias. A creación realízase se o número de
     * elementos no JSON, é diferente ós existentes na Base de Datos. 
     *
     * @throws FileNotFoundException
     * @throws Exception
     */
    public static void crearProvincias() throws HibernateException, Exception {
        Provincia[] aProvinciasJSON = JsonUtils.recuperarProvincias();
        if (aProvinciasJSON != null && aProvinciasJSON.length > 0) {
            System.out.println("Entramos");
            List<Provincia> lProvinciasDB = ProvinciasDAO.getProvincias();
            if (lProvinciasDB == null || lProvinciasDB.size() != aProvinciasJSON.length) {
                System.out.println("If tamanho");
                recrearProvincias(aProvinciasJSON);
            }

        } else {
            throw new Exception("Erro no json de provincias");
        }
    }

    /**
     * Elimina os elementos existentes na táboa e inserta de novo os obtidos do
     * JSON
     *
     * @param aProvincias
     */
    private static void recrearProvincias(Provincia[] aProvincias) {
        ProvinciasDAO.eliminarTodas();
        for (Provincia p : aProvincias) {
            ProvinciasDAO.insertarProvincia(p);
        }
    }
}

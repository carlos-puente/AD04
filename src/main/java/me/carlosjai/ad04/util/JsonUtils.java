package me.carlosjai.ad04.util;

import me.carlosjai.ad04.obxectos.Franquicia;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import me.carlosjai.ad04.json.ProvinciaJSON;
import me.carlosjai.ad04.json.ProvinciasJSON;
import me.carlosjai.ad04.obxectos.Provincia;

/*
 * Utilidades para o manexo de JSON, serializar e deserializar
 */
/**
 *
 * @author carlos
 */
public class JsonUtils {

    /**
     *
     * @param f Franquicia a serializar
     * @throws IOException
     */
    public static void xerarFicheiroJson(Franquicia f) throws IOException {
        gardarFicheiro(new Gson().toJson(f));
    }

    /**
     * Recupera os datos gardados nun JSON e convérteos a un obxecto Franquicia
     *
     * @return
     * @throws FileNotFoundException
     */
    public static Franquicia xerarFranquicia() throws FileNotFoundException {
        Franquicia f = new Gson().fromJson(new FileReader("backup.json"), Franquicia.class);
        return f == null ? new Franquicia() : f;
    }

     /**
     * Recupera os datos gardados nun JSON e convérteos a un obxecto Franquicia
     *
     * @return
     * @throws FileNotFoundException
     */
    public static Provincia[] recuperarProvincias() throws FileNotFoundException {
        ProvinciasJSON p = new Gson().fromJson(new FileReader("provincias.json"), ProvinciasJSON.class);
        return xerarProvincias(p);
    }
    
    
    /**
     * Garda un json en un ficheiro
     *
     * @param json
     * @throws IOException
     */
    public static void gardarFicheiro(String json) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("backup.json"));
        writer.write(json);
        writer.close();
    }

    private static Provincia[] xerarProvincias(ProvinciasJSON p) {
        List<Provincia> lp = null;
        if(p != null && p.getProvincias()!=null){
            lp = new ArrayList<>();
            for(ProvinciaJSON provincia: p.getProvincias()){
                lp.add(new Provincia(provincia.getId(),provincia.getNome()));
            }
        }
        return lp!=null ? lp.toArray(new Provincia[lp.size()]) : null;
    }
}

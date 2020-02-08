package me.carlosjai.ad04.xestores;

import me.carlosjai.ad04.util.SAXUtils;

/**
 *
 * @author carlos
 */
public class XestorRSS {
    private static final String URL_TITULARES = "http://ep00.epimg.net/rss/elpais/portada.xml";
    public static void amosarTitulares() {
        SAXUtils.listarTitulosRss(URL_TITULARES);
        System.out.println("[FIN]5"
                + "");
    }
}

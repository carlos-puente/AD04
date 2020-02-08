package me.carlosjai.ad04.util;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author carlos
 */
public class SAXUtils {
/**
 * Mediante o ManexadorRSS lista os títulos de cada item dun RSS dunha URL dada
 * @param url 
 */
public static void listarTitulosRss(String url){
    try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            DefaultHandler manexador = new ManexadorRSS();
            parser.parse(url, manexador);
        } catch (Exception e) {
            e.printStackTrace();
        }
}
/**
 * Clase estática para ler o RSS mediante SAX
 */
  static class ManexadorRSS extends DefaultHandler {

        private boolean newItem = false;
        private String title = null;

        @Override
        public void startElement(String uri, String localName,
                String qName, Attributes attributes) throws SAXException {
            if (qName.equals("item")) {
                newItem = true;
            } else if (qName.equals("title") && newItem) {
                title = "";
            }
        }

        @Override
        public void endElement(String uri, String localName,
                String qName) throws SAXException {
            if (qName.equals("title") && newItem) {
                System.out.println(title);
                title = null;
            }
        }

        @Override
        public void characters(char ch[], int start, int length)
                throws SAXException {
            if (title != null) {
                title += new String(ch, start, length);
            }
        }
    }
}
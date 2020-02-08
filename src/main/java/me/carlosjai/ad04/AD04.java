package me.carlosjai.ad04;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.carlosjai.ad04.db.FranquiciaDB;
import me.carlosjai.ad04.obxectos.Franquicia;
import org.hibernate.HibernateException;

/**
 *
 * @author carlos
 */
public class AD04 {

    public static void main(String[] args) {
        Franquicia f =null;
        try {
            //Conector.crearTablas();
            //ProvinciasAccessor.cargarProvinciasDeJson();
            f = FranquiciaDB.cargarDatosFranquicia();    
            
        } catch (HibernateException ex) {
            Logger.getLogger(AD04.class.getName()).log(Level.SEVERE, null, ex);       
        }
        
        Menu.menuPrincipal(f);
    }
    
}

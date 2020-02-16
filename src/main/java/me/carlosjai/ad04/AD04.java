package me.carlosjai.ad04;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.carlosjai.ad04.db.FranquiciaDB;
import me.carlosjai.ad04.db.provincias.ProvinciasAccessor;
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
           ProvinciasAccessor.crearProvincias();
            f = FranquiciaDB.cargarDatosFranquicia();    
            
        } catch (HibernateException ex) {
            Logger.getLogger(AD04.class.getName()).log(Level.SEVERE, null, ex);       
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AD04.class.getName()).log(Level.SEVERE, "Erro obtendo as provincias.", ex);
        } catch (Exception ex) {
            Logger.getLogger(AD04.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Menu.menuPrincipal(f);
    }
    
}

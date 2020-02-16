/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.provincias;

import me.carlosjai.ad04.obxectos.Provincia;
import java.util.List;
import javax.transaction.Transactional;
import me.carlosjai.ad04.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlos
 */
@Transactional
public class ProvinciasDAO {

    protected static Provincia getProvincia(int id) throws HibernateException {

        Provincia p = null;
        Query query = HibernateUtil.getSession().createQuery("SELECT p FROM Provincia p WHERE p.id=:id");
        query.setParameter("id", id);
        List<Provincia> l = query.list();
        if (l != null && !l.isEmpty()) {
            p = l.get(0);
        }
        return p;
    }

    protected static List<Provincia> getProvincias() throws HibernateException {
        List<Provincia> l = null;

        l = HibernateUtil.createQuery("SELECT p FROM Provincia p").list();

        return l;
    }

    protected static void insertarProvincia(Provincia p) throws HibernateException {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.save(p);
        tran.commit();
    }

    protected static void eliminarTodas() {
      
        for(Provincia p: getProvincias()){
            eliminar(p);
        }
        
    }
    protected static void eliminar(Provincia p) {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.delete(p);
        tran.commit();

    }
}

package me.carlosjai.ad04.db.tendas;

import java.util.List;
import me.carlosjai.ad04.obxectos.Tenda;
import me.carlosjai.ad04.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlos
 */
public class TendasDAO {

    protected static List<Tenda> getTendas() throws HibernateException {
        
        List<Tenda> l = null;

        l = HibernateUtil.createQuery("SELECT t FROM Tenda t").list();

        return l;
    }

    protected static Tenda getTenda(String nomeTenda) throws HibernateException {
        Tenda t = null;
        Query query = HibernateUtil.getSession().createQuery("SELECT t FROM Tenda t WHERE t.nome=:nome");
        query.setParameter("nome", nomeTenda);
        List<Tenda> l = query.list();
        if (l != null && !l.isEmpty()) {
            t = l.get(0);
        }
        return t;
    }

    protected static void insertarTenda(Tenda t) throws HibernateException {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.save(t);
        tran.commit();
    }

    protected static void actualizarTenda(Tenda t) throws HibernateException {
        HibernateUtil.closeSession();
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.merge(t);
        tran.commit();
    }

    protected static void actualizarTenda(String nome, Tenda t) throws HibernateException {
        HibernateUtil.closeSession();
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        Query query = session.createQuery("UPDATE Tenda set nome=:nome, cidade=:cidade, id_provincia=:provincia WHERE nome=:velloNome");
        query.setParameter("nome", t.getNome());
        query.setParameter("cidade", t.getCidade());
        query.setParameter("provincia", t.getProvincia().getId());
        query.setParameter("velloNome", nome);
        tran.commit();
    }

    protected static void eliminarTenda(Tenda tenda) throws HibernateException {
        HibernateUtil.closeSession();
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.delete(tenda);
        tran.commit();
    }
}

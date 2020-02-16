package me.carlosjai.ad04.db.empregados;

import java.sql.SQLException;
import java.util.List;
import me.carlosjai.ad04.obxectos.Empregado;
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
public class EmpregadosDAO {

    protected static List<Empregado> getEmpregados() throws HibernateException {
        return HibernateUtil.createQuery("SELECT e FROM Empregado e").list();
    }

    protected static Empregado getEmpregado(String nif) throws HibernateException {
        Empregado empregado = null;
        Query query = HibernateUtil.getSession().createQuery("SELECT e FROM Empregado e WHERE e.nif=:nif");
        query.setParameter("nif", nif);
        List<Empregado> l = query.list();
        if (l != null && !l.isEmpty()) {
            empregado = l.get(0);
        }
        return empregado;
    }

    protected static void insertarEmpregado(Empregado e) throws HibernateException {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.save(e);
        tran.commit();
    }

    protected static void actualizarEmpregado(String nif, Empregado novaInfo) throws ClassNotFoundException, SQLException {

    }

    protected static void eliminarEmpregado(String nif) throws ClassNotFoundException, SQLException {

    }
}

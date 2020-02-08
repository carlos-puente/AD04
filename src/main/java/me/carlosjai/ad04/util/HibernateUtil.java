/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.util;

import java.util.Properties;
import me.carlosjai.ad04.obxectos.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.query.Query;

/**
 *
 * @author carlos
 */
public class HibernateUtil {
     private static SessionFactory sessionFactory;
    
    //Este método devolve a sesión para poder facer operacións coa base de datos
    public static SessionFactory getSessionFactory(){
        
        //Se a sesion non se configurou, creamolo
        if(sessionFactory == null){
            try{
                Configuration conf = new Configuration();
                
                //Engadimos as propiedades
                Properties settings = new Properties();
                
                //Indicamos o conector da base de datos que vamos a usar
                settings.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
                
                //Indicamos a localización da base de datos que vamos a utilizar
                settings.put(Environment.URL,"jdbc:mysql://192.168.56.101:3306/hibernate");
                
                //Indicamos o usuario da base de datos con cal nos vamos conectar e o seu contrasinal
                settings.put(Environment.USER,"userhibernate");
                settings.put(Environment.PASS,"abc123.");
                
                //Indicamos o dialecto que ten que usar Hibernate 
                settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
                
                //Indicamos que se as táboas todas se borren e se volvan crear
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                
                //Indicamos que se mostre as operacións SQL que Hibernate leva a cabo
                settings.put(Environment.SHOW_SQL, "true");
                conf.setProperties(settings);
                
                //Engaidmos aquelas clases nas que queremos facer persistencia
                conf.addAnnotatedClass(Cliente.class);
                conf.addAnnotatedClass(Empregado.class);
                conf.addAnnotatedClass(Produto.class);
                conf.addAnnotatedClass(Provincia.class);
                conf.addAnnotatedClass(Tenda.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(serviceRegistry);
            }catch(HibernateException e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    
    public static Query createQuery(String query){
            return getSessionFactory().openSession().createQuery(query);
           
    }
    public static Session getSession(){
        return getSessionFactory().openSession();           
    }
}

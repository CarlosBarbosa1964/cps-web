package br.com.ibtechnology.cpsweb.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtil {

    private static SessionFactory factory;  
    static {  
        try {  
            factory = new Configuration().configure().buildSessionFactory();  
        } catch (Exception e) {  
            e.printStackTrace();  
            factory = null;  
        }  
    }  
      
    public static Session getSession() {  
        return factory.openSession();  
    }  
}

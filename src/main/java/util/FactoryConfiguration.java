package util;

import entity.License;
import entity.NationalIdentityCard;
import entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//constructor private
public class FactoryConfiguration {
    private static FactoryConfiguration instance;
    private SessionFactory factory;

    private FactoryConfiguration() {
        Configuration configure = new Configuration().configure()
                .addAnnotatedClass(StudentEntity.class)
                .addAnnotatedClass(License.class)
                .addAnnotatedClass(NationalIdentityCard.class);
        factory = configure.buildSessionFactory();
    }

    //this one in the method that we can access from anywhere
    public static FactoryConfiguration getInstance() {
        return instance == null ? instance = new FactoryConfiguration() : instance;
    }

    //this one cannot access without creating an object
    public Session getSession() {
        return factory.openSession();
    }
}
package io.github.chermehdi.orm;

import io.github.chermehdi.util.ClassPathProvider;
import javax.persistence.Entity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Provides The session factory object, based on hibernate.cfg.xml file, it supposes that the file
 * is on the classpath
 *
 * @author chermehdi
 */
public class HibernateSessionFactoryProvider {

  private static SessionFactory factory = null;

  public synchronized SessionFactory buildSessionFactory() {
    if (factory == null) {
      factory = createSessionFactory();
    }
    return factory;
  }

  private SessionFactory createSessionFactory() {
    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        .configure()
        .build();
    // while creating the metadata object, we could've added our mapped classes, or cash config
    // but since all is configured using xml, there is not so much to do here !
    String entityClass = Entity.class.getCanonicalName();

    MetadataSources metadataSources = new MetadataSources(serviceRegistry);
    try {
      new ClassPathProvider("")
          .loadClasses()
          .forEach(metadataSources::addAnnotatedClass);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return
        metadataSources.buildMetadata()
            .buildSessionFactory();
  }

}

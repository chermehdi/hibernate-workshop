package io.github.mehdithe.orm;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Provides The session factory object, based on hibernate.cfg.xml file, it supposes that the file
 * is on the classpath
 *
 * @author mehdithe
 */
public class HibernateSessionFactoryProvider {

  public SessionFactory buildSessionFactory() {
    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        .configure()
        .build();
    // while creating the metadata object, we could've added our mapped classes, or cash config
    // but since all is configured using xml, there is not so much to do here !
    return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
  }
}

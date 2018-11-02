package io.github.chermehdi.orm;

import io.github.chermehdi.Example;
import io.github.chermehdi.domain.EmbeddableObject;
import io.github.chermehdi.domain.EmbeddablePK;
import java.io.Serializable;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author chermehdi
 */
public class EmbeddablePrimaryExample extends Example {

  private Session engine;

  private SessionFactory sessionFactory;

  @Override
  public void boot() {
    sessionFactory = new HibernateSessionFactoryProvider().buildSessionFactory();
    engine = sessionFactory.openSession();
    engine.beginTransaction();
  }

  @Override
  public void run() {
    EmbeddableObject object = new EmbeddableObject();
    object.setName("hello embeddable keys");
    EmbeddablePK primaryKey = new EmbeddablePK(1, 2);
    object.setId(primaryKey);
    Logger logger = Logger.getAnonymousLogger();
    Serializable saved = engine.save(object);
    logger.info("the primary key is {}" + saved);
    logger.info("the saved object " + object);
  }

  @Override
  public void destroy() {
    engine.getTransaction().commit();
    engine.close();
    sessionFactory.close();
  }
}

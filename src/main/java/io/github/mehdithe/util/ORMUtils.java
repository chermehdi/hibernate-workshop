package io.github.mehdithe.util;

import io.github.mehdithe.orm.HibernateSessionFactoryProvider;
import java.util.function.Consumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Provides static utility methods to work with hibernate
 *
 * @author mehdithe
 */
public final class ORMUtils {

  private ORMUtils() {
    throw new RuntimeException("should not instatiate class of type " + getClass().getName());
  }

  public static void doInHibernate(Consumer<Session> sessionConsumer) {
    SessionFactory sessionFactory = new HibernateSessionFactoryProvider().buildSessionFactory();
    doInHibernate(sessionFactory, sessionConsumer);
  }

  public static void doInHibernate(SessionFactory sessionFactory,
      Consumer<Session> sessionConsumer) {
    try (Session session = sessionFactory.openSession()) {
      sessionConsumer.accept(session);
    }
  }
}

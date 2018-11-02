package io.github.chermehdi.util;

import io.github.chermehdi.orm.HibernateSessionFactoryProvider;
import java.util.function.Consumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Provides static utility methods to work with hibernate
 *
 * @author chermehdi
 */
public final class ORMUtils {

  private ORMUtils() {
    throw new UnsupportedOperationException("should not instatiate class of type " + getClass().getName());
  }

  public static void doInHibernate(Consumer<Session> sessionConsumer) {
    SessionFactory sessionFactory = new HibernateSessionFactoryProvider().buildSessionFactory();
    doInHibernate(sessionFactory, sessionConsumer);
  }

  public static void doInHibernateTransactional(Consumer<Session> sessionConsumer) {
    SessionFactory sessionFactory = new HibernateSessionFactoryProvider().buildSessionFactory();
    doInHibernateTransactional(sessionFactory, sessionConsumer);
  }

  public static void doInHibernate(SessionFactory sessionFactory,
      Consumer<Session> sessionConsumer) {
    try (Session session = sessionFactory.openSession()) {
      sessionConsumer.accept(session);
    }
  }

  public static void doInHibernateTransactional(SessionFactory sessionFactory,
      Consumer<Session> sessionConsumer) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      sessionConsumer.accept(session);
      transaction.commit();
    }
  }
}

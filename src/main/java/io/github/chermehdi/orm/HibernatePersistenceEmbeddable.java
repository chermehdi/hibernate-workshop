package io.github.chermehdi.orm;

import io.github.chermehdi.Example;
import io.github.chermehdi.domain.Address;
import io.github.chermehdi.domain.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author chermehdi
 */
public class HibernatePersistenceEmbeddable extends Example {

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
    Address address = new Address("london", "221b baker street-1");
    Address address2 = new Address("casablanca", "derb omar-1");
    Profile profile = new Profile(null, "mehdi.cheracher1@gmail.com", "mehdi cheracher 1", address);
    profile.setSecondAddress(address2);
    engine.persist(profile);
    Profile persistedProfile = engine.find(Profile.class, profile.getId());
    System.out.println("The persisted Profile " + persistedProfile);
  }

  @Override
  public void destroy() {
    engine.getTransaction().commit();
    engine.close();
    sessionFactory.close();
  }

}

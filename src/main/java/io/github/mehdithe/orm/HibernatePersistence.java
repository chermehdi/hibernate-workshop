package io.github.mehdithe.orm;

import io.github.mehdithe.Example;
import io.github.mehdithe.domain.Role;
import io.github.mehdithe.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author mehdithe
 */
public class HibernatePersistence extends Example {

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
    User user = new User();
    user.setFirstName("hello");
    Role userRole = new Role("USER_ROLE");
    Role adminRole = new Role("ADMIN_ROLE");
    engine.save(userRole);
    engine.save(adminRole);
    user.setLastName("mql");
    user.getRoles().add(userRole);
    user.getRoles().add(adminRole);
    engine.save(user);
  }

  @Override
  public void destroy() {
    engine.getTransaction().commit();
    engine.close();
    sessionFactory.close();
  }

}

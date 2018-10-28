package io.github.mehdithe;

import io.github.mehdithe.orm.HibernatePersistenceEmbeddable;

/**
 * @author mehdithe
 */
public class Main {

  public static void main(String[] args) {
//    new ProxyRunner(new JdbcPersistence()).run();
    new ProxyRunner(new HibernatePersistenceEmbeddable()).run();
  }
}

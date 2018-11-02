package io.github.chermehdi.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author chermehdi
 */
@Entity
public class EmbeddableObject {

  @EmbeddedId
  private EmbeddablePK id;

  private String name;

  public EmbeddableObject() {
  }


  public EmbeddablePK getId() {
    return id;
  }

  public void setId(EmbeddablePK id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "EmbeddableObject{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}

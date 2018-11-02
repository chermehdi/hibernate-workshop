package io.github.chermehdi.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * @author chermehdi
 */
@Embeddable
public class EmbeddablePK implements Serializable {

  private Integer first;

  private Integer second;

  public EmbeddablePK() {
  }

  public EmbeddablePK(Integer first, Integer second) {
    this.first = first;
    this.second = second;
  }

  public Integer getFirst() {
    return first;
  }

  public void setFirst(Integer first) {
    this.first = first;
  }

  public Integer getSecond() {
    return second;
  }

  public void setSecond(Integer second) {
    this.second = second;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmbeddablePK that = (EmbeddablePK) o;
    return Objects.equals(first, that.first) &&
        Objects.equals(second, that.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public String toString() {
    return "EmbeddablePK{" +
        "first=" + first +
        ", second=" + second +
        '}';
  }
}

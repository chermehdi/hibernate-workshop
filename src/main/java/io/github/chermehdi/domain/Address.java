package io.github.chermehdi.domain;

import javax.persistence.Embeddable;

/**
 * @author chermehdi
 */
@Embeddable
public class Address {

  private String city;

  private String homeAddress;

  public Address() {
  }

  public Address(String city, String homeAddress) {
    this.city = city;
    this.homeAddress = homeAddress;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getHomeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(String homeAddress) {
    this.homeAddress = homeAddress;
  }

  @Override
  public String toString() {
    return "Address{" +
        "city='" + city + '\'' +
        ", homeAddress='" + homeAddress + '\'' +
        '}';
  }
}


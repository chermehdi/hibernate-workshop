package io.github.mehdithe.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author mehdithe
 */
@Entity
@AttributeOverrides({
    @AttributeOverride(
        name = "address.city",
        column = @Column(name = "first_address_city")
    ),
    @AttributeOverride(
        name = "address.homeAddress",
        column = @Column(name = "first_address_home")
    )
})
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String email;

  private String fullName;

  private Address address;

  private Address secondAddress;


  public Profile() {
  }

  public Profile(Integer id, String email, String fullName, Address address) {
    this.id = id;
    this.email = email;
    this.fullName = fullName;
    this.address = address;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Address getSecondAddress() {
    return secondAddress;
  }

  public void setSecondAddress(Address secondAddress) {
    this.secondAddress = secondAddress;
  }

  @Override
  public String toString() {
    return "Profile{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", fullName='" + fullName + '\'' +
        ", address=" + address +
        '}';
  }
}

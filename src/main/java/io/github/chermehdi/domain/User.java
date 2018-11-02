package io.github.chermehdi.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * @author chermehdi
 */
public class User {

  private int id;

  private String firstName;

  private String lastName;

  private Date createdAt;


  @OneToMany(fetch = FetchType.LAZY)
  private Set<Profile> profiles;

  public Set<Profile> getProfiles() {
    return profiles;
  }

  public void setProfiles(Set<Profile> profiles) {
    this.profiles = profiles;
  }


  class UserProxy extends User {

    private final User user;

    UserProxy(User user) {
      this.user = user;
    }

    @Override
    public Set<Profile> getProfiles() {
      Set<Profile> profile = getProfileFromDb();
      this.setProfiles(profile);
      return super.getProfiles();
    }

    private Set<Profile> getProfileFromDb() {
      return null;
    }
  }

  private List<Role> roles = new ArrayList<>();

  public User() {
    createdAt = Date.from(Instant.now());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }
}

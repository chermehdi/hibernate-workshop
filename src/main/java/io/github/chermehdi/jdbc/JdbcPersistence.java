package io.github.chermehdi.jdbc;

import io.github.chermehdi.Example;
import io.github.chermehdi.domain.Role;
import io.github.chermehdi.domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Class to test the persistence model using raw jdbc api
 *
 * @author chermehdi
 */
public class JdbcPersistence extends Example {

  private Connection connection;

  @Override
  public void boot() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      // could be more dynamic here but it's just an example ...
      connection = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/hibernate-workshop", "root", "root");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void run() {
    try {
      User user = retrieveUser(2);
//      Role userRole = new Role("USER");
//      userRole.setId(getNextId("roles"));
//      insertRole(userRole, user);
      System.out.println(user.getRoles());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void insertRole(Role userRole, User user) throws Exception {
    PreparedStatement statement = connection
        .prepareStatement("INSERT INTO roles VALUES (?, ?, ?);");
    statement.setInt(1, userRole.getId());
    statement.setString(2, userRole.getName());
    statement.setInt(3, user.getId());
    statement.execute();
  }

  private void insertUser(User user) throws Exception {
    PreparedStatement statement = connection
        .prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?);");
    statement.setInt(1, user.getId());
    statement.setString(2, user.getFirstName());
    statement.setString(3, user.getLastName());
    statement.setDate(4, Date.valueOf(LocalDate.parse(user.getCreatedAt().toString())));
    statement.execute();
  }

  private User retrieveUser(int id) throws Exception {
    PreparedStatement statement = connection
        .prepareStatement("SELECT * FROM users where id = ?");
    statement.setInt(1, id);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      User user = new User();
      user.setId(resultSet.getInt("id"));
      user.setFirstName(resultSet.getString("first_name"));
      user.setLastName(resultSet.getString("last_name"));
      user.setCreatedAt(resultSet.getDate("created_at"));
      fillUserRoles(user);
      return user;
    }
    return null;
  }

  private void fillUserRoles(User user) throws Exception {
    PreparedStatement statement = connection
        .prepareStatement("SELECT * FROM roles where user_id = ?");
    statement.setInt(1, user.getId());
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
      Role role = new Role(resultSet.getString("name"));
      role.setId(resultSet.getInt("id"));
      user.getRoles().add(role);
    }
  }

  private int getNextId(String tableName) throws Exception {
    PreparedStatement statement = connection
        .prepareStatement("SELECT count(id) FROM " + tableName);
    ResultSet resultSet = statement.executeQuery();
    resultSet.next();
    return resultSet.getInt(1) + 1;
  }

  private User createRandomUser() throws Exception {
    User user = new User();
    user.setFirstName("student-" + (int) (Math.random() * 100));
    user.setLastName("mqliste-" + (int) (Math.random() * 100));
    user.setId(getNextId("users"));
    return user;
  }

  @Override
  public void destroy() {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}

package ro.ase.acs.controllers;

import static ro.ase.acs.constants.SQLConstants.*;
import static ro.ase.acs.constants.Colors.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import ro.ase.acs.interfaces.DbController;

public class SQLController implements DbController {

  private Connection conn;
  private PreparedStatement insertEmployee;

  @Override
  public void open() {
    try {
      Class.forName("org.sqlite.JDBC");
      this.conn = DriverManager.getConnection(CONNECTION_STRING);
      this.conn.setAutoCommit(false);
      System.out.println(
          SUCCESS_COLOR + "Connected to the database!" + RESET_COLOR);
    } catch (Exception e) {
      System.out.println(
          ERROR_COLOR + "Couldn't connect to database..." + System.lineSeparator() + e.getMessage() + RESET_COLOR);
      System.exit(-1);
    }
  }

  @Override
  public void close() {
    try {
      assert insertEmployee != null;
      insertEmployee.close();
      assert conn != null;
      conn.close();
      System.out.println(
          SUCCESS_COLOR + "Database closed!" + RESET_COLOR);
    } catch (Exception e) {
      System.out.println(
          ERROR_COLOR + "Couldn't close the database..." + System.lineSeparator() + e.getMessage() + RESET_COLOR);
      System.exit(-1);
    }
  }

  private void prepareStatements() {
    try {
      this.insertEmployee = conn.prepareStatement(INSERT_EMPLOYEE);
    } catch (Exception e) {
      System.out.println(
          ERROR_COLOR + "Couldn't create prepared statements" + System.lineSeparator() + e.getMessage() + RESET_COLOR);
      System.exit(-1);
    }
  }

  @Override
  public void createTable() {
    try {
      Statement statement = conn.createStatement();
      statement.executeUpdate(DROP_EMPLOYEES_TABLE);
      statement.executeUpdate(CREATE_TABLE_EMPLOYEES);
      prepareStatements();
      statement.close();
      System.out.println(
          SUCCESS_COLOR + "Table " + TABLE_EMPLOYEES + " created!" + RESET_COLOR);
    } catch (Exception e) {
      System.out.println(ERROR_COLOR + "Couldn't create " + TABLE_EMPLOYEES + " table"
          + System.lineSeparator() + e.getMessage() + RESET_COLOR);
    }
  }

  @Override
  public void insertData(Integer id, String name, String address, Double salary) {
    try {
      insertEmployee.setInt(INDEX_EMPLOYEE_ID, id);
      insertEmployee.setString(INDEX_EMPLOYEE_NAME, name);
      insertEmployee.setString(INDEX_EMPLOYEE_ADDRESS, address);
      insertEmployee.setDouble(INDEX_EMPLOYEE_SALARY, salary);
      insertEmployee.executeUpdate();
      conn.commit();
      System.out.println(
          SUCCESS_COLOR + "Data inserted!" + RESET_COLOR);
    } catch (Exception e) {
      System.out.println(ERROR_COLOR + "Couldn't insert into " + TABLE_EMPLOYEES
          + System.lineSeparator() + e.getMessage() + RESET_COLOR);
    }
  }

  @Override
  public void printData() {
    try {
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(QUERY_EMPLOYEES_ALL);
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString(2);
        String address = rs.getString("address");
        double salary = rs.getDouble("salary");
        System.out
            .println("ID: " + id + ", " + "NAME: " + name + ", " + "ADDRESS: " + address + ", " + "SALARY: " + salary);
      }
      rs.close();
      statement.close();
    } catch (Exception e) {
      System.out.println(
          ERROR_COLOR + "Couldn't print " + TABLE_EMPLOYEES + " table" +
              System.lineSeparator() + e.getMessage() + RESET_COLOR);
    }
  }
}
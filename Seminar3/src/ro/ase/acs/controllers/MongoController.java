package ro.ase.acs.controllers;

import static ro.ase.acs.constants.MongoConstants.*;
import static ro.ase.acs.constants.Colors.*;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import ro.ase.acs.interfaces.DbController;

public class MongoController implements DbController {
  private MongoClient client;
  private MongoDatabase db;

  @Override
  public void open() {
    try {
      client = new MongoClient(new MongoClientURI(CONNECTION_URI));
      db = client.getDatabase(DB_NAME);
      System.out.println(
          SUCCESS_COLOR + "Connected to the database!" + RESET_COLOR);
    } catch (Exception e) {
      System.out.println(
          ERROR_COLOR + "Couldn't connect to database..." + System.lineSeparator() + e.getMessage() + RESET_COLOR);
    }

  }

  @Override
  public void close() {
    try {
      client.close();
      System.out.println(
          SUCCESS_COLOR + "Database closed!" + RESET_COLOR);
    } catch (Exception e) {
      System.out.println(
          ERROR_COLOR + "Couldn't close the database..." + System.lineSeparator() + e.getMessage() + RESET_COLOR);
    }
  }

  @Override
  public void createTable() {
    try {
      if (db.getCollection(COLLECTION_EMPLOYEES) != null) {
        db.getCollection(COLLECTION_EMPLOYEES).drop();
      }
      db.createCollection(COLLECTION_EMPLOYEES);
      System.out.println(
          SUCCESS_COLOR + "Collection " + COLLECTION_EMPLOYEES + " created!" + RESET_COLOR);
    } catch (Exception e) {
      System.out.println(
          ERROR_COLOR + "Couldn't create " + COLLECTION_EMPLOYEES + "!" + System.lineSeparator() + e.getMessage()
              + RESET_COLOR);
    }
  }

  @Override
  public void insertData(Integer code, String name, String address, Double salary) {
    try {
      Document emp = new Document();
      emp.append(FIELD_EMPLOYEE_CODE, code);
      emp.append(FIELD_EMPLOYEE_NAME, name);
      emp.append(FIELD_EMPLOYEE_ADDRESS, address);
      emp.append(FIELD_EMPLOYEE_SALARY, salary);
      db.getCollection(COLLECTION_EMPLOYEES).insertOne(emp);
      System.out.println(
          SUCCESS_COLOR + "Data inserted!" + RESET_COLOR);
    } catch (Exception e) {
      System.out.println(
          ERROR_COLOR + "Couldn't insert data!" + System.lineSeparator() + e.getMessage() + RESET_COLOR);
    }
  }

  @Override
  public void printData() {
    try {
      FindIterable<Document> result = db.getCollection(COLLECTION_EMPLOYEES).find();
      for (Document doc : result) {
        System.out.println(doc);
      }
    } catch (Exception e) {
      System.out.println(
          ERROR_COLOR + "Couldn't print data!" + System.lineSeparator() + e.getMessage() + RESET_COLOR);
    }
  }
}
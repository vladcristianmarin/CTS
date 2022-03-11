package ro.ase.acs.controllers;

import static ro.ase.acs.constants.MongoConstants.*;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import ro.ase.acs.interfaces.DbController;

public class MongoController implements DbController {
  private MongoClient client;
  private MongoDatabase db;

  @Override
  public void open() {
    client = new MongoClient("localhost", 27017);
    db = client.getDatabase("test");

    if (db.getCollection(COLLECTION_EMPLOYEES) != null) {
      db.getCollection(COLLECTION_EMPLOYEES).drop();
    }
  }

  @Override
  public void close() {
    client.close();
  }

  @Override
  public void createTable() {
    db.createCollection(COLLECTION_EMPLOYEES);
  }

  @Override
  public void insertData(Integer code, String name, String address, Double salary) {
    Document emp = new Document();
    emp.append(FIELD_EMPLOYEE_CODE, code);
    emp.append(FIELD_EMPLOYEE_NAME, name);
    emp.append(FIELD_EMPLOYEE_ADDRESS, address);
    emp.append(FIELD_EMPLOYEE_SALARY, salary);
    db.getCollection(COLLECTION_EMPLOYEES).insertOne(emp);
  }

  @Override
  public void printData() {
    FindIterable<Document> result = db.getCollection(COLLECTION_EMPLOYEES).find();
    for (Document doc : result) {
      System.out.println(doc);
    }
  }
}
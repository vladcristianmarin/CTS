package ro.ase.acs.interfaces;

public interface DbController {
  public void open();

  public void close();

  public void createTable();

  public void insertData(Integer id, String name, String address, Double salary);

  public void printData();
}

package ro.ase.acs.constants;

public class SQLConstants {

  public static final String DB_NAME = "database.db";
  public static final String CONNECTION_STRING =
    "jdbc:sqlite:/../../../../../db/database.db";

  public static final String TABLE_EMPLOYEES = "employees";
  public static final String COLUMN_EMPLOYEE_ID = "id";
  public static final String COLUMN_EMPLOYEE_NAME = "name";
  public static final String COLUMN_EMPLOYEE_ADDRESS = "address";
  public static final String COLUMN_EMPLOYEE_SALARY = "salary";

  public static final int INDEX_EMPLOYEE_ID = 1;
  public static final int INDEX_EMPLOYEE_NAME = 2;
  public static final int INDEX_EMPLOYEE_ADDRESS = 3;
  public static final int INDEX_EMPLOYEE_SALARY = 4;

  public static final String DROP_EMPLOYEES_TABLE =
    "DROP TABLE IF EXISTS " + TABLE_EMPLOYEES;

  public static final String CREATE_TABLE_EMPLOYEES =
    "CREATE TABLE " +
    TABLE_EMPLOYEES +
    "(" +
    COLUMN_EMPLOYEE_ID +
    " INTEGER PRIMARY KEY, " +
    COLUMN_EMPLOYEE_NAME +
    " TEXT, " +
    COLUMN_EMPLOYEE_ADDRESS +
    " TEXT, " +
    COLUMN_EMPLOYEE_SALARY +
    " REAL)";

  public static final String INSERT_EMPLOYEE =
    "INSERT INTO " +
    TABLE_EMPLOYEES +
    "(" +
    COLUMN_EMPLOYEE_ID +
    "," +
    COLUMN_EMPLOYEE_NAME +
    "," +
    COLUMN_EMPLOYEE_ADDRESS +
    "," +
    COLUMN_EMPLOYEE_SALARY +
    ") VALUES (?, ?, ?, ?)";

  public static final String QUERY_EMPLOYEES_ALL =
    "SELECT * FROM " + TABLE_EMPLOYEES;
}

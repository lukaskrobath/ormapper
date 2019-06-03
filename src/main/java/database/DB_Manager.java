package database;

import annotations.PrimaryKey;


import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DB_Manager {
    //    CREATE TABLE car (
//      licenseplate  VARCHAR(255) PRIMARY KEY,
//      brand VARCHAR(255)
//      horsepower INTEGER
//      price DOUBLE
//);
    private final Map<String, String> mapping = new TreeMap<String, String>();
    private List<String> allEntities = new ArrayList<>();
    private DB_Database database = null;

    public DB_Manager(List<String> allEntities) throws SQLException {
        this.allEntities = allEntities;
        String[] helpK = {"int", "Integer", "double", "Double", "float", "Float", "long", "Long", "String", "char", "Char", "byte", "Byte", "short", "Short", "LocalDate", "LocalTime", "LocalDateTime"};
        String[] helpV = {"INTEGER", "INTEGER", "FLOAT(53)", "FLOAT(53)", "FLOAT", "FLOAT", "BIGINT", "BIGINT", "VARCHAR(255)", "CHAR", "CHAR", "SMALLINT", "SMALLINT", "INT", "INT", "DATE", "TIME", "TIMESTAMP"};
        for (int i = 0; i < helpK.length; i++) {
            mapping.put(helpK[i], helpV[i]);
        }
        try {
            database = DB_Database.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
        if (DB_Properties.getProperty("mode").equals("none")) {
            System.out.println("Mapping finished with 0 changes");
        } else if (DB_Properties.getProperty("mode").equals("create")) {
            for (String str : allEntities) {
                try {
                    Statement statement = database.getConnection().createStatement();
                    statement.executeUpdate(createTable(Class.forName(str),false));
                } catch (ClassNotFoundException e) {
                    System.out.println(e.toString());
                }
            }
            System.out.println("Mapping finished");
        } else {
            for (String str : allEntities) {
                try {
                    Statement statement = database.getConnection().createStatement();
                    statement.executeUpdate(createTable(Class.forName(str),true));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                }
            }
            System.out.println("Table dropped and mapping finished");

        }
    }

    public String createTable(Class entityClass, boolean drop) {
        String tablename = entityClass.getName().toLowerCase().substring(entityClass.getName().lastIndexOf(".") + 1);
        String res = "";
        if (drop) {
            res = "DROP TABLE " + tablename + "; ";
        }
        res += "CREATE TABLE " + tablename + " (";
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length - 1; i++) {
            res += fields[i].getName().substring(fields[i].getName().lastIndexOf(".") + 1) + " " + mapping.get(fields[i].getGenericType().toString().substring(fields[i].getGenericType().toString().lastIndexOf(".") + 1));
            if (fields[i].isAnnotationPresent(PrimaryKey.class)) {
                res += " PRIMARY KEY";
            }
            res += ", ";
        }
        res += fields[fields.length - 1].getName().substring(fields[fields.length - 1].getName().lastIndexOf(".") + 1) + " " + mapping.get(fields[fields.length - 1].getGenericType().toString().substring(fields[fields.length - 1].getGenericType().toString().lastIndexOf(".") + 1));
        if (fields[fields.length - 1].isAnnotationPresent(PrimaryKey.class)) {
            res += " PRIMARY KEY";
        }
        res += ");";
        return res;
    }

    public List<String> getAllEntities() {
        return allEntities;
    }

}

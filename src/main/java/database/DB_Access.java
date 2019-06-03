package database;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DB_Access {
    //ToDo: Prepared Statements schließen
    //TODo:


    private static DB_Access theInstance = null;
    private DB_Database database = null;


    public static DB_Access getInstance() {
        if (theInstance == null) {
            try {
                theInstance = new DB_Access();
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return theInstance;
    }



    private DB_Access() {
        try {
            database = DB_Database.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }


    public void disconnect() throws SQLException {
        database.disconnect();
    }
}

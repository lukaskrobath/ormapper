package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Database {
    private Connection connection;
    private String db_url = null;
    private String db_databasename = null;
    private String db_username = null;
    private String db_password = null;
    private String db_driver = null;

    private DB_CacheConnection cc;

    private static DB_Database theInstance = null;

    public static DB_Database getInstance() throws ClassNotFoundException, SQLException {
        if(theInstance==null){
            theInstance = new DB_Database();
        }
        return theInstance;
    }

    private DB_Database() throws ClassNotFoundException, SQLException {
        loadProperties();
        Class.forName(db_driver);
        connect();
        cc = new DB_CacheConnection(connection);
    }

    public Statement getStatement() throws SQLException {
        if(cc==null){
            throw new RuntimeException("no statement available connected");
        }
        return cc.getStatement();

    }

    public void releaseStatement(Statement statement){
        if(cc==null){
            throw new RuntimeException("not connected");
        }
        cc.releaseStatement(statement);
    }

    public void connect() throws SQLException {
        if(connection!=null){
            connection.close();
        }
        connection= DriverManager.getConnection(db_url+db_databasename,db_username,db_password);
    }
    public void disconnect() throws SQLException {
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    private void loadProperties() {
        db_url = DB_Properties.getProperty("url");
        db_databasename = DB_Properties.getProperty("databasename");
        db_username = DB_Properties.getProperty("username");
        db_password = DB_Properties.getProperty("password");
        db_driver = DB_Properties.getProperty("driver");

    }

}

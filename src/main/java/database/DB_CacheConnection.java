package database;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DB_CacheConnection {
    private Connection connection = null;
    private LinkedList<Statement> stmtQueue = new LinkedList<>();

    public DB_CacheConnection(Connection connection){
        this.connection=connection;
    }

    public synchronized Statement getStatement() throws SQLException {
        if(connection==null){
            throw new RuntimeException("not connected");
        }
        if(stmtQueue.isEmpty()){
            return connection.createStatement();
        }
        return stmtQueue.poll();

    }
    public synchronized void releaseStatement(Statement statement){
        if(connection==null){
            throw new RuntimeException("not connected");
        }
        stmtQueue.offer(statement);
    }
}

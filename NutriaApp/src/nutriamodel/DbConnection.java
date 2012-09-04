package nutriamodel;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

/**
 *
 * @author Ariel
 */
public class DbConnection {
    private String connect_string = "jdbc:sqlite://D:/dev/databases/nutriadb.db3" ;
    private ConnectionSource conn;
    public DbConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = new JdbcConnectionSource(connect_string);
        }
        catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            conn.close();
        }
        catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    public ConnectionSource getConnection() {
        return this.conn;
    }
}

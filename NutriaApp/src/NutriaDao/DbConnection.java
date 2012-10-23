package NutriaDao;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Ariel
 */
public class DbConnection {
    
    private ConnectionSource conn;
    
    public DbConnection() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.properties"));
            Class.forName("org.sqlite.JDBC");
            conn = new JdbcConnectionSource(prop.getProperty("db.string"));
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

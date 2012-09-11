package nutriamodel;

import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ariel
 */
public abstract class ModelHelper  {
           
    private static List<Object> getClassList() {
        //add all pojos objects to initialize the database
        List<Object> classList = new ArrayList();
        classList.add(new Nutrient());
        classList.add(new Ingredient());
        classList.add(new NutrientIngredient());
        return classList;
    }
    
    public static void createDataBase() throws SQLException {
        DbConnection dbConnection = new DbConnection();
        List<Object> classList = getClassList();
        for(Object pojo : classList) {
            TableUtils.dropTable(dbConnection.getConnection(), pojo.getClass(), true);
            TableUtils.createTableIfNotExists(dbConnection.getConnection(), pojo.getClass());
        }
    }
    
    public static void clearDatabase() throws SQLException {
        DbConnection dbConnection = new DbConnection();
        List<Object> classList = getClassList();
        for(Object pojo : classList) {
            TableUtils.clearTable(dbConnection.getConnection(), pojo.getClass());
        }
        
    }
}

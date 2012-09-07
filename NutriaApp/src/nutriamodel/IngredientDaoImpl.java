package nutriamodel;

import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;

public class IngredientDaoImpl extends BaseDaoImpl<Ingredient, Long> 
implements IngredientDao {
    
    public IngredientDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), Ingredient.class);
    }
}

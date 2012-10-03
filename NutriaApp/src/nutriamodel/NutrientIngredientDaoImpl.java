package NutriaModel;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class NutrientIngredientDaoImpl extends BaseDaoImpl<NutrientIngredient, Long> 
implements NutrientIngredientDao{
    
    public NutrientIngredientDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), NutrientIngredient.class);
    }
    
    public NutrientIngredientDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, NutrientIngredient.class);
    }
    
    public List<NutrientIngredient> getByIngredientId(Long ingredientId) throws SQLException {
        QueryBuilder qb = this.queryBuilder();
        PreparedQuery pq = qb.where().eq("ingredient_id", ingredientId).prepare();
        return this.query(pq);
    }
}

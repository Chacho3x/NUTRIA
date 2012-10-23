package NutriaDao;

import NutriaModel.Nutrient;
import NutriaModel.NutrientIngredient;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class NutrientDaoImpl extends BaseDaoImpl<Nutrient, Long> 
implements NutrientDao {
    
    private Dao<NutrientIngredient, Long> nutrientIngredientDao;
    
    public NutrientDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), Nutrient.class);
        nutrientIngredientDao = DaoManager.createDao(this.connectionSource, NutrientIngredient.class);
    }
    
    public NutrientDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Nutrient.class);
        nutrientIngredientDao = DaoManager.createDao(this.connectionSource, NutrientIngredient.class);
    }
    
    public List<Nutrient> getByIngredientId(Long ingredientId) throws SQLException {
        QueryBuilder qb = nutrientIngredientDao.queryBuilder();
        qb.selectColumns("nutrient_id");
        qb.where().eq("ingredient_id", ingredientId);
        
        QueryBuilder qb2 = this.queryBuilder();
        PreparedQuery pq2 = qb2.where().in("id", qb).prepare();
        return this.query(pq2);
    }
}

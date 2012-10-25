package NutriaDao;

import NutriaModel.NutrientConstraint;
import NutriaModel.QuantifiedIngredient;
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
public class QuantifiedIngredientDaoImpl extends BaseDaoImpl<QuantifiedIngredient, Long> 
implements QuantifiedIngredientDao {
    
    private IngredientDaoImpl ingredientDao;
    
    public QuantifiedIngredientDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), QuantifiedIngredient.class);
        ingredientDao = new IngredientDaoImpl(this.connectionSource);
    }
    
    public QuantifiedIngredientDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, QuantifiedIngredient.class);
        ingredientDao = new IngredientDaoImpl(this.connectionSource);
    }
    
    public List<QuantifiedIngredient> getByMixture(Long mixtureId) throws SQLException {
        QueryBuilder qb = this.queryBuilder();
        PreparedQuery pq = qb.where().eq("mixture_id", mixtureId).prepare();
        List<QuantifiedIngredient> quantifiedIngredientList = this.query(pq);
        for(QuantifiedIngredient quantifiedIngredient : quantifiedIngredientList) {
            quantifiedIngredient.setIngredient(ingredientDao.queryForId(quantifiedIngredient.getIngredient().getId()));
        }
        return quantifiedIngredientList;
    }
}

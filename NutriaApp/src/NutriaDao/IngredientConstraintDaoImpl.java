package NutriaDao;

import NutriaModel.IngredientConstraint;
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
public class IngredientConstraintDaoImpl extends BaseDaoImpl<IngredientConstraint, Long>
implements IngredientConstraintDao {
    
    private IngredientDaoImpl ingredientDao;
    
    public IngredientConstraintDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), IngredientConstraint.class);
        ingredientDao = new IngredientDaoImpl(this.connectionSource);
    }
    
    public IngredientConstraintDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, IngredientConstraint.class);
        ingredientDao = new IngredientDaoImpl(this.connectionSource);
    }
    
    public List<IngredientConstraint> getByIngredientSheet(Long ingredientSheetId) throws SQLException {
        QueryBuilder qb = this.queryBuilder();
        PreparedQuery pq = qb.where().eq("ingredient_sheet_id", ingredientSheetId).prepare();
        List<IngredientConstraint> ingredientConstraintList = this.query(pq);
        for(IngredientConstraint ingredientConstraint : ingredientConstraintList) {
            ingredientConstraint.setIngredient(ingredientDao.queryForId(ingredientConstraint.getIngredient().getId()));
        }
        return ingredientConstraintList;
    }
}

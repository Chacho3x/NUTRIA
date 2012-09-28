package NutriaModel;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.List;

public class IngredientDaoImpl extends BaseDaoImpl<Ingredient, Long> 
implements IngredientDao {
    
    private Dao<NutrientIngredient, Long> nutrientIngredientDao;
    private NutrientDaoImpl nutrientDaoImpl;
    
    public IngredientDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), Ingredient.class);
        nutrientIngredientDao = DaoManager.createDao(this.connectionSource, NutrientIngredient.class);
        nutrientDaoImpl = new NutrientDaoImpl(this.connectionSource);
    }
    
    public Ingredient populateNutrients(Ingredient ingredient) throws SQLException {
        ingredient.setNutrients(nutrientDaoImpl.getByIngredientId(ingredient.getId()));
        return ingredient;
    }
    
    @Override
    public CreateOrUpdateStatus createOrUpdate(Ingredient ingredient) throws SQLException{
        int result;
        CreateOrUpdateStatus resultStatus = super.createOrUpdate(ingredient);
        if(ingredient.getNutrients() != null && ingredient.getNutrients().size() > 0) {
            
            List<Nutrient> actualNutrientList = ingredient.getNutrients();
            
            //delete nutrients removed
            QueryBuilder qb = nutrientIngredientDao.queryBuilder();
            qb.selectColumns("nutrient_id");
            qb.where().eq("ingredient_id", ingredient.getId()).and().notIn("nutrient_id", actualNutrientList);
            
            List<NutrientIngredient> nutrientsToDelete = nutrientIngredientDao.query(qb.prepare());
            if (!nutrientsToDelete.isEmpty()) {
                result = nutrientIngredientDao.delete(nutrientsToDelete);
                if (result == 0) {
                    throw new SQLException("A problem ocoured trying to remove nutrients");
                }
            }
            
            //add new nutrients
            List<Nutrient> lastNutrientList = nutrientDaoImpl.getByIngredientId(ingredient.getId());
            actualNutrientList.removeAll(lastNutrientList);
            for(Nutrient nutrient : actualNutrientList) {
                result = nutrientIngredientDao.create(new NutrientIngredient(nutrient, ingredient));
                if (result == 0) {
                    throw new SQLException("A problem ocoured trying to add nutrient " + nutrient.getId());
                }
            }
        }
        return resultStatus;
    }
}

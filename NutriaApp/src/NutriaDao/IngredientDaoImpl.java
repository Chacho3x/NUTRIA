package NutriaDao;

import NutriaModel.Ingredient;
import NutriaModel.Nutrient;
import NutriaModel.NutrientIngredient;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

public class IngredientDaoImpl extends BaseDaoImpl<Ingredient, Long> 
implements IngredientDao {
    
    private NutrientIngredientDaoImpl nutrientIngredientDao;
    private NutrientDaoImpl nutrientDao;
    
    public IngredientDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), Ingredient.class);
        nutrientIngredientDao = new NutrientIngredientDaoImpl(this.connectionSource);
        nutrientDao = new NutrientDaoImpl(this.connectionSource);
    }

    public IngredientDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Ingredient.class);
        nutrientIngredientDao = new NutrientIngredientDaoImpl(this.connectionSource);
        nutrientDao = new NutrientDaoImpl(this.connectionSource);
    }
    
    public Ingredient populateNutrients(Ingredient ingredient) throws SQLException {
        List<NutrientIngredient> nutrientIngredientList = nutrientIngredientDao.getByIngredientId(ingredient.getId());
        
        for(NutrientIngredient aux : nutrientIngredientList) {
            Nutrient n = nutrientDao.queryForId(aux.getNutrient().getId());
            aux.setNutrient(n);
        }
        ingredient.setNutrients(nutrientIngredientList);
        return ingredient;
    }
    
    @Override
    public CreateOrUpdateStatus createOrUpdate(Ingredient ingredient) throws SQLException{
        int result;
        CreateOrUpdateStatus resultStatus = super.createOrUpdate(ingredient);
        if(ingredient.getNutrients() != null) {
            
            List<NutrientIngredient> modifiedNutrientList = ingredient.getNutrients();
            List<NutrientIngredient> lastNutrientList = nutrientIngredientDao.getByIngredientId(ingredient.getId());
            
            //delete nutrients removed
            for(NutrientIngredient lastNutrient : lastNutrientList) {
                Boolean delete = true;
                for(NutrientIngredient newNutrient : modifiedNutrientList) {
                    if(lastNutrient.getNutrient().getId().equals(newNutrient.getNutrient().getId())) {
                        delete = false;
                        break;
                    }
                }
                if(delete) {
                    result = nutrientIngredientDao.deleteById(lastNutrient.getId());
                    if (result == 0) {
                        throw new SQLException("A problem ocoured trying to remove nutrients");
                    }
                }
            }
            
            //add or update nutrients
            for(NutrientIngredient nutrientIngredient : modifiedNutrientList) {
                nutrientIngredient.setIngredient(ingredient);
                nutrientIngredientDao.createOrUpdate(nutrientIngredient);
            }
            
            ingredient = populateNutrients(ingredient);
        }
        return resultStatus;
    }
    
    @Override
    public int deleteById(Long ingredientId) throws SQLException {
        List<NutrientIngredient> nutrientsToDelete = nutrientIngredientDao.getByIngredientId(ingredientId);
        
        int result = 0;
        
        for(NutrientIngredient nutrient : nutrientsToDelete) {
            result += nutrientIngredientDao.deleteById(nutrient.getId());
        }
        if (result == nutrientsToDelete.size()) {
            result = super.deleteById(ingredientId);
        } else {
            result = 0;
        }
        return result;
    }
}

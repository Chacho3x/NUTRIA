package ModelTests;

import NutriaModel.Ingredient;
import NutriaModel.IngredientDaoImpl;
import NutriaModel.Nutrient;
import NutriaModel.NutrientDaoImpl;
import NutriaModel.NutrientIngredient;
import NutriaModel.NutrientIngredientDaoImpl;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ariel
 */
public class NutrientIngredientDaoTest extends BaseDaoTest {
    
    private NutrientIngredientDaoImpl nutrientIngredientDao;
    private NutrientDaoImpl nutrientDao;
    private IngredientDaoImpl ingredientDao;
    
    public NutrientIngredientDaoTest () throws SQLException {
        nutrientIngredientDao = new NutrientIngredientDaoImpl();
        nutrientDao = new NutrientDaoImpl();
        ingredientDao = new IngredientDaoImpl();
        
        nutrientIngredientDao.initialize();
        nutrientDao.initialize();
        ingredientDao.initialize();       
    }
    
    @Test
    public void getNutrientIngrediensByIngredientIdTest() throws SQLException {
        Nutrient nutrient1 = new Nutrient("1 nutrient", "%");
        Ingredient ingredient1 = new Ingredient("1 ingredient", 10D, 1D);
        Nutrient nutrient2 = new Nutrient("2 nutrient", "%");
        Ingredient ingredient2 = new Ingredient("2 ingredient", 5D, 1D);
        
        nutrientDao.createOrUpdate(nutrient1);
        nutrientDao.createOrUpdate(nutrient2);
        ingredientDao.createOrUpdate(ingredient1);
        ingredientDao.createOrUpdate(ingredient2);
        nutrientIngredientDao.createOrUpdate(new NutrientIngredient(nutrient1, ingredient1, 20D));
        nutrientIngredientDao.createOrUpdate(new NutrientIngredient(nutrient2, ingredient1, 30D));
        nutrientIngredientDao.createOrUpdate(new NutrientIngredient(nutrient2, ingredient2, 10D));
        
        List<NutrientIngredient> resultList = nutrientIngredientDao.getByIngredientId(ingredient1.getId());
        assertEquals(2, resultList.size());
        
        assertEquals(nutrient1.getId(), resultList.get(0).getNutrient().getId());
        assertEquals(nutrient2.getId(), resultList.get(1).getNutrient().getId());
        
        resultList = nutrientIngredientDao.getByIngredientId(ingredient2.getId());
        assertEquals(nutrient2.getId(), resultList.get(0).getNutrient().getId());
    }
}
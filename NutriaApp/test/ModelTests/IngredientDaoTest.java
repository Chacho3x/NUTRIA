package ModelTests;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import NutriaModel.Ingredient;
import NutriaModel.IngredientDaoImpl;
import NutriaModel.Nutrient;
import NutriaModel.NutrientDaoImpl;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ariel
 */
public class IngredientDaoTest extends BaseDaoTest {
    
    private IngredientDaoImpl ingredientDao;
    private NutrientDaoImpl nutrientDao;
    
    public IngredientDaoTest() throws SQLException {
        ingredientDao = new IngredientDaoImpl();
        ingredientDao.initialize();
        nutrientDao = new NutrientDaoImpl();
        nutrientDao.initialize();
    }
    
    @Test
    public void crudIngredientTest() throws SQLException {
        List<Ingredient> ingredientList = ingredientDao.queryForAll();
        int totalIngredients = ingredientList.size();
        int result;
        
        //create
        Ingredient firstIngredient = new Ingredient();
        firstIngredient.setName("Harina de hueso");
        firstIngredient.setPrice(5D);
        firstIngredient.setRoundingFactor(0.1D);
        ingredientDao.createOrUpdate(firstIngredient);
        ingredientList = ingredientDao.queryForAll();
        assertEquals(totalIngredients + 1, ingredientList.size());
        
        //Update
        firstIngredient = ingredientList.get(ingredientList.size() - 1);
        firstIngredient.setName("Sorgo");
        firstIngredient.setPrice(4.99D);
        ingredientDao.createOrUpdate(firstIngredient);
        Ingredient resultIngredient = ingredientDao.queryForId(firstIngredient.getId());
        assertEquals(firstIngredient.getId(), resultIngredient.getId());
        assertEquals(firstIngredient.getName(), resultIngredient.getName());
        assertEquals(firstIngredient.getPrice(), resultIngredient.getPrice());
        assertEquals(firstIngredient.getRoundingFactor(), resultIngredient.getRoundingFactor());
        
        //Delete
        result = ingredientDao.deleteById(firstIngredient.getId());
        assertEquals(1, result);
        resultIngredient = ingredientDao.queryForId(firstIngredient.getId());
        assertNull(resultIngredient);
    }
    
    @Test
    public void getAddNutrientsTest() throws Exception {
        List<Nutrient> nutrientList = new ArrayList();
        for(int i = 0; i < 5; i++) {
            nutrientList.add(new Nutrient("Nutrient " + i, "%"));
            nutrientDao.create(nutrientList.get(i));
        }
        Ingredient ingredient1 = new Ingredient("Sorgo", 10D, 0.1D);
        ingredient1.setNutrients(nutrientList);
        ingredientDao.createOrUpdate(ingredient1);
        
        Ingredient resultIngredient = ingredientDao.queryForId(ingredient1.getId());
        assertNotNull(resultIngredient);
        
        //get saved nutrient list
        ingredientDao.populateNutrients(resultIngredient);
        List<Nutrient> resultNutrientList = resultIngredient.getNutrients();
        assertNotNull(resultNutrientList);
        assertEquals(5, resultNutrientList.size());
        
        resultNutrientList.remove(nutrientList.get(2));
        resultNutrientList.remove(nutrientList.get(4));
        
        Nutrient lastNutrient = new Nutrient("ultimo nutriente", "%");
        nutrientDao.create(lastNutrient);
        resultNutrientList.add(lastNutrient);
                
        resultIngredient.setNutrients(resultNutrientList);
        ingredientDao.createOrUpdate(resultIngredient);
        
        Ingredient resultIngredient2 = ingredientDao.queryForId(resultIngredient.getId());
        ingredientDao.populateNutrients(resultIngredient2);
        
        List<Nutrient> resultNutrientList2 = resultIngredient2.getNutrients();
        assertNotNull(resultNutrientList2);
        assertEquals(4, resultNutrientList2.size());
        assertEquals("ultimo nutriente", resultNutrientList2.get(3).getName());
        assertFalse(resultNutrientList2.contains(nutrientList.get(2)));
        assertFalse(resultNutrientList2.contains(nutrientList.get(4)));
    }
}

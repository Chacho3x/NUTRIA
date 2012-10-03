package ModelTests;

import NutriaModel.Ingredient;
import NutriaModel.IngredientDaoImpl;
import NutriaModel.Nutrient;
import NutriaModel.NutrientDaoImpl;
import NutriaModel.NutrientIngredient;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        //first create list of nutrients
        for(int i = 0; i < 5; i++) {
            nutrientList.add(new Nutrient("Nutrient " + i, "%"));
            nutrientDao.create(nutrientList.get(i));
        }
        
        //add nutrients to ingredient
        Ingredient ingredient1 = new Ingredient("Sorgo", 10D, 0.1D);
        List<NutrientIngredient> nutrientIngredientList;
        if(ingredient1.getNutrients() == null)
            nutrientIngredientList = new ArrayList();
        else
            nutrientIngredientList = ingredient1.getNutrients();
        
        for(Nutrient n : nutrientList) {
            nutrientIngredientList.add(new NutrientIngredient(n, 10D));
        }
        
        ingredient1.setNutrients(nutrientIngredientList);
        ingredientDao.createOrUpdate(ingredient1);
        
        //test if saved
        assertNotNull(ingredient1.getId());
        
        Ingredient resultIngredient = ingredientDao.queryForId(ingredient1.getId());
        assertNotNull(resultIngredient);
        
        //get saved nutrient list
        ingredientDao.populateNutrients(resultIngredient);
        List<NutrientIngredient> resultNutrientList = resultIngredient.getNutrients();
        assertNotNull(resultNutrientList);
        assertEquals(5, resultNutrientList.size());
        
        resultNutrientList.remove(2);
        resultNutrientList.remove(2);
        
        Nutrient lastNutrient = new Nutrient("ultimo nutriente", "%");
        nutrientDao.create(lastNutrient);
        resultNutrientList.add(new NutrientIngredient(lastNutrient, 20D));
                
        resultIngredient.setNutrients(resultNutrientList);
        ingredientDao.createOrUpdate(resultIngredient);
        
        Ingredient resultIngredient2 = ingredientDao.queryForId(resultIngredient.getId());
        ingredientDao.populateNutrients(resultIngredient2);
        
        List<NutrientIngredient> resultNutrientList2 = resultIngredient2.getNutrients();
        assertNotNull(resultNutrientList2);
        assertEquals(4, resultNutrientList2.size());
        assertEquals("ultimo nutriente", resultNutrientList2.get(3).getNutrient().getName());
        assertFalse(resultNutrientList2.contains(nutrientIngredientList.get(2)));
        assertFalse(resultNutrientList2.contains(nutrientIngredientList.get(4)));
    }
}

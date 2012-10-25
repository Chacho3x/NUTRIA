package ModelTests;

import NutriaDao.IngredientDaoImpl;
import NutriaDao.MixtureDaoImpl;
import NutriaModel.Ingredient;
import NutriaModel.Mixture;
import NutriaModel.QuantifiedIngredient;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ariel
 */
public class MixtureDaoTest {
    
    private MixtureDaoImpl mixtureDao;
    private IngredientDaoImpl ingredientDao;
    
    public MixtureDaoTest() throws SQLException {
        mixtureDao = new MixtureDaoImpl();
        mixtureDao.initialize();
        
        ingredientDao = new IngredientDaoImpl();
        ingredientDao.initialize();
    }
    
    @Test
    public void crudMixtureTest() throws SQLException {
        Ingredient i1 = new Ingredient("Ingredient1", 10D, 0.01D);
        Ingredient i2 = new Ingredient("Ingredient2", 20D, 0.01D);
        Ingredient i3 = new Ingredient("Ingredient3", 30D, 0.01D);
        
        ingredientDao.createOrUpdate(i1);
        ingredientDao.createOrUpdate(i2);
        ingredientDao.createOrUpdate(i3);
        
        List<QuantifiedIngredient> qIngredientList = new ArrayList<>();
        qIngredientList.add(new QuantifiedIngredient(i1, 25D, null));
        qIngredientList.add(new QuantifiedIngredient(i2, 25D, null));
        qIngredientList.add(new QuantifiedIngredient(i3, 50D, null));
        
        Mixture mixture = new Mixture("mixture1", Boolean.TRUE);
        mixture.setIngredientList(qIngredientList);
        mixtureDao.createOrUpdate(mixture);
        
        Mixture mixtureRes = mixtureDao.queryForId(mixture.getId());
        mixtureDao.populateMixture(mixtureRes);
        assertEquals("mixture1", mixtureRes.getName());
        assertEquals(Boolean.TRUE, mixtureRes.getWetIngredients());
        
        List<QuantifiedIngredient> resultList = mixtureRes.getIngredientList();
        assertEquals(3, resultList.size());
        assertEquals(i1, resultList.get(0).getIngredient());
        assertEquals(i2, resultList.get(1).getIngredient());
        assertEquals(i3, resultList.get(2).getIngredient());
        
        mixture.setWetIngredients(Boolean.FALSE);
        resultList.remove(2);
        Ingredient i4 = new Ingredient("Ingredient4", 5D, 0.01D);
        Ingredient i5 = new Ingredient("Ingredient5", 6D, 0.01D);
        ingredientDao.createOrUpdate(i4);
        ingredientDao.createOrUpdate(i5);
        
        resultList.add(new QuantifiedIngredient(i4, 20D, null));
        resultList.add(new QuantifiedIngredient(i5, 30D, null));
        mixture.setIngredientList(resultList);
        mixtureDao.createOrUpdate(mixture);
        
        mixtureRes = mixtureDao.queryForId(mixture.getId());
        assertEquals(Boolean.FALSE, mixtureRes.getWetIngredients());
        mixtureDao.populateMixture(mixtureRes);
        resultList = mixtureRes.getIngredientList();
        assertEquals(4, resultList.size());
        
        assertEquals(i4, resultList.get(2).getIngredient());
        assertEquals(i5, resultList.get(3).getIngredient());
        
        mixtureDao.deleteById(mixture.getId());
        assertNull(mixtureDao.queryForId(mixture.getId()));
    }
}

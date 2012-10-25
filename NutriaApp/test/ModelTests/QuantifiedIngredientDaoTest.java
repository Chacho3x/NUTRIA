package ModelTests;

import NutriaDao.IngredientDaoImpl;
import NutriaDao.MixtureDaoImpl;
import NutriaDao.QuantifiedIngredientDaoImpl;
import NutriaModel.Ingredient;
import NutriaModel.Mixture;
import NutriaModel.QuantifiedIngredient;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author Ariel
 */
public class QuantifiedIngredientDaoTest extends BaseDaoTest {

    private QuantifiedIngredientDaoImpl quantifiedIngredientDao;
    private MixtureDaoImpl mixtureDao;
    private IngredientDaoImpl ingredientDao;

    public QuantifiedIngredientDaoTest() throws SQLException {
        quantifiedIngredientDao = new QuantifiedIngredientDaoImpl();
        quantifiedIngredientDao.initialize();

        mixtureDao = new MixtureDaoImpl();
        mixtureDao.initialize();

        ingredientDao = new IngredientDaoImpl();
        ingredientDao.initialize();
    }

    @Test
    public void crudQuantifiedIngredientTest() throws SQLException {
        Mixture m1 = new Mixture("Mixture 1", Boolean.TRUE);
        mixtureDao.createOrUpdate(m1);

        Ingredient ingredient1 = new Ingredient("harina de sorgo", 10D, 0.01D);
        ingredientDao.createOrUpdate(ingredient1);
        QuantifiedIngredient qi1 = new QuantifiedIngredient(m1, ingredient1, 50D, null);
        quantifiedIngredientDao.createOrUpdate(qi1);
        QuantifiedIngredient qResult = quantifiedIngredientDao.queryForId(qi1.getId());
        assertEquals(new Double(50D), qResult.getWetQuantity());
        
        qResult.setWetQuantity(60D);
        quantifiedIngredientDao.createOrUpdate(qResult);
        QuantifiedIngredient qResult2 = quantifiedIngredientDao.queryForId(qResult.getId());
        assertEquals(new Double(60D), qResult2.getWetQuantity());
        
        quantifiedIngredientDao.deleteById(qResult2.getId());
        assertNull(quantifiedIngredientDao.queryForId(qResult2.getId()));
    }
    
    @Test
    public void getQuantifiedIngredientsByMixture() throws SQLException {
        Mixture m1 = new Mixture("Mixture 1", Boolean.TRUE);
        mixtureDao.createOrUpdate(m1);
        
        Ingredient i1 = new Ingredient("Harina1", 10D, 0.01D);
        Ingredient i2 = new Ingredient("Harina2", 20D, 0.01D);
        Ingredient i3 = new Ingredient("Harina3", 30D, 0.01D);
        
        ingredientDao.createOrUpdate(i1);
        ingredientDao.createOrUpdate(i2);
        ingredientDao.createOrUpdate(i3);
        
        QuantifiedIngredient q1 = new QuantifiedIngredient(m1, i1, 25D, null);
        QuantifiedIngredient q2 = new QuantifiedIngredient(m1, i2, 25D, null);
        QuantifiedIngredient q3 = new QuantifiedIngredient(m1, i3, 50D, null);
        
        quantifiedIngredientDao.createOrUpdate(q1);
        quantifiedIngredientDao.createOrUpdate(q2);
        quantifiedIngredientDao.createOrUpdate(q3);
        
        List<QuantifiedIngredient> qList = quantifiedIngredientDao.getByMixture(m1.getId());
        assertEquals(new Double(25D), qList.get(0).getWetQuantity());
        assertEquals(new Double(25D), qList.get(1).getWetQuantity());
        assertEquals(new Double(50D), qList.get(2).getWetQuantity());
    }
}

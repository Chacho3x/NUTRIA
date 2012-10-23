package ModelTests;

import NutriaModel.Nutrient;
import NutriaDao.NutrientDaoImpl;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ariel
 */
public class NutrientDaoTest extends BaseDaoTest {
    
    private NutrientDaoImpl nutrientDao;
    
    public NutrientDaoTest() throws SQLException {
        nutrientDao = new NutrientDaoImpl();
        nutrientDao.initialize();
    }
    
    @Test
    public void crudNutrientTest() throws Exception {
        List<Nutrient> nutrientList = nutrientDao.queryForAll();
        int initialSize = nutrientList.size();
        int response;
        
        //create
        Nutrient nutrient1 = new Nutrient("fenofuxina", "%");
        response = nutrientDao.create(nutrient1);
        assertEquals(1, response);
        
        //read
        nutrientList = nutrientDao.queryForAll();
        assertEquals(initialSize + 1, nutrientList.size());
        
        Nutrient resultNutrient = nutrientList.get(nutrientList.size()-1);
        assertEquals(nutrient1.getName(), resultNutrient.getName());
        assertEquals(nutrient1.getUnit(), resultNutrient.getUnit());
        
        //update
        nutrient1.setId(resultNutrient.getId());
        nutrient1.setName("Calcio");
        nutrient1.setUnit("kcal");
        response = nutrientDao.update(nutrient1);
        assertEquals(1, response);
        
        resultNutrient = nutrientDao.queryForId(nutrient1.getId());
        assertEquals(nutrient1.getName(), resultNutrient.getName());
        
        //delete
        response = nutrientDao.deleteById(resultNutrient.getId());
        assertEquals(1, response);
        resultNutrient = nutrientDao.queryForId(resultNutrient.getId());
        assertNull(resultNutrient);
    }
}

package ModelTests;

import NutriaModel.ModelHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ariel
 */
public class BaseDaoTest {
    
    public BaseDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        ModelHelper.createDataBase();
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws Exception {
        ModelHelper.clearDatabase();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void mainTest() {
    }
}

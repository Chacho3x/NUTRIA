package ModelTests;

import NutriaDao.DaoHelper;
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
        DaoHelper.createDataBase();
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws Exception {
        DaoHelper.clearDatabase();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void mainTest() {
    }
}

package ModelTests;

import java.io.FileInputStream;
import java.util.Properties;
import NutriaModel.ModelHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
}

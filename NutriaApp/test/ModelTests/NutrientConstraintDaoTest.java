package ModelTests;

import NutriaModel.Nutrient;
import NutriaModel.NutrientConstraint;
import NutriaModel.NutrientConstraintDaoImpl;
import NutriaModel.NutrientDaoImpl;
import NutriaModel.NutritionalSheet;
import NutriaModel.NutritionalSheetDaoImpl;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.math3.optimization.linear.Relationship;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ariel
 */
public class NutrientConstraintDaoTest extends BaseDaoTest {
    
    private NutrientConstraintDaoImpl nutrientConstraintDao;
    private NutritionalSheetDaoImpl nutritionalSheetDao;
    private NutrientDaoImpl nutrientDao;
    
    public NutrientConstraintDaoTest() throws SQLException {
        nutrientConstraintDao = new NutrientConstraintDaoImpl();
        nutrientConstraintDao.initialize();
        nutritionalSheetDao = new NutritionalSheetDaoImpl();
        nutritionalSheetDao.initialize();
        nutrientDao = new NutrientDaoImpl();
        nutrientDao.initialize();
    }
    
    @Test
    public void crudNutrientConstraintTest() throws SQLException {
        Nutrient nutrient1 = new Nutrient("calcio", "%");
        nutrientDao.createOrUpdate(nutrient1);
        
        NutrientConstraint n1 = new NutrientConstraint();
        n1.setNutrient(nutrient1);
        n1.setMinBound(0.2D);
        n1.setMinRelationship(Relationship.GEQ.toString());
        n1.setMaxBound(5D);
        n1.setMaxRelationship(Relationship.LEQ.toString());
        nutrientConstraintDao.createOrUpdate(n1);
        
        NutrientConstraint nResult = nutrientConstraintDao.queryForId(n1.getId());
        assertNotNull(nResult);
        
        nResult.setMaxBound(6D);
        nutrientConstraintDao.createOrUpdate(nResult);
        
        nResult = nutrientConstraintDao.queryForId(n1.getId());
        assertEquals(new Double(6), nResult.getMaxBound());
        
        nutrientConstraintDao.deleteById(nResult.getId());
        assertNull(nutrientConstraintDao.queryForId(n1.getId()));
    }
    
    @Test
    public void getNutrientConstraintsByNutritionalSheet() throws SQLException {
        Nutrient n1 = new Nutrient("Calcio", "%");
        Nutrient n2 = new Nutrient("Fosforo", "%");
        Nutrient n3 = new Nutrient("Vitamina A", "%");
        
        nutrientDao.createOrUpdate(n1);
        nutrientDao.createOrUpdate(n2);
        nutrientDao.createOrUpdate(n3);
        
        NutritionalSheet ns = new NutritionalSheet();
        ns.setName("Alimento para aves");
        nutritionalSheetDao.createOrUpdate(ns);
        
        NutrientConstraint nc1 = new NutrientConstraint(n1, ns, 4D, Relationship.GEQ.toString());
        NutrientConstraint nc2 = new NutrientConstraint(n2, ns, 3.5D, Relationship.GEQ.toString());
        NutrientConstraint nc3 = new NutrientConstraint(n3, ns, 2.5D, Relationship.GEQ.toString());
        
        nutrientConstraintDao.createOrUpdate(nc1);
        nutrientConstraintDao.createOrUpdate(nc2);
        nutrientConstraintDao.createOrUpdate(nc3);
        
        List<NutrientConstraint> ncList = nutrientConstraintDao.getByNutritionalSheet(ns.getId());
        assertEquals(3, ncList.size());
    }
}

package ModelTests;

import NutriaModel.Nutrient;
import NutriaModel.NutrientConstraint;
import NutriaModel.NutrientDaoImpl;
import NutriaModel.NutritionalSheet;
import NutriaModel.NutritionalSheetDaoImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.optimization.linear.Relationship;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ariel
 */
public class NutritionalSheetDaoTest extends BaseDaoTest {
    
    private NutritionalSheetDaoImpl nutritionalSheetDao;
    private NutrientDaoImpl nutrientDao;
    
    public NutritionalSheetDaoTest() throws SQLException {
        nutritionalSheetDao = new NutritionalSheetDaoImpl();
        nutrientDao = new NutrientDaoImpl();
        
        nutritionalSheetDao.initialize();
        nutrientDao.initialize();
    }
    
    @Test
    public void crudNutritionalSheetTest() throws SQLException {
        Nutrient n1 = new Nutrient("Fosforo", "%");
        Nutrient n2 = new Nutrient("Calcio", "%");
        Nutrient n3 = new Nutrient("Vitamina A", "%");
        
        nutrientDao.createOrUpdate(n1);
        nutrientDao.createOrUpdate(n2);
        nutrientDao.createOrUpdate(n3);
        
        List<NutrientConstraint> constraintList = new ArrayList<>();
        constraintList.add(new NutrientConstraint(n1, 20D, Relationship.GEQ.toString(), 50D, Relationship.LEQ.toString()));
        constraintList.add(new NutrientConstraint(n2, 50D, Relationship.GEQ.toString(), 70D, Relationship.LEQ.toString()));
        constraintList.add(new NutrientConstraint(n3, 70D, Relationship.GEQ.toString(), 90D, Relationship.LEQ.toString()));
        
        NutritionalSheet ns = new NutritionalSheet("Cerdos 5 meses");
        ns.setNutrientConstraintList(constraintList);
        //save nutritionalSheet with 3 constraints
        nutritionalSheetDao.createOrUpdate(ns);
        
        NutritionalSheet resultNS = nutritionalSheetDao.queryForId(ns.getId());
        assertNotNull(resultNS);
        assertNull(resultNS.getNutrientConstraintList());
        
        //check constraints saved.
        nutritionalSheetDao.populateNutritionalSheet(resultNS);
        List<NutrientConstraint> resultConstraintList = resultNS.getNutrientConstraintList();
        assertNotNull(resultConstraintList);
        assertEquals(3, resultConstraintList.size());
        
        NutrientConstraint editConstraint = resultConstraintList.get(2);
        editConstraint.setMinBound(0D);
        nutritionalSheetDao.createOrUpdate(resultNS);
        
        resultConstraintList = resultNS.getNutrientConstraintList();
        assertNotNull(resultConstraintList);
        assertEquals(new Double(0), resultConstraintList.get(2).getMinBound());
    }
}

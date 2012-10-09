package NutriaModel;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class NutritionalSheetDaoImpl extends BaseDaoImpl<NutritionalSheet, Long> 
implements NutritionalSheetDao {
    
    private NutrientConstraintDaoImpl nutrientConstraintDao;
    
    public NutritionalSheetDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), NutritionalSheet.class);
        nutrientConstraintDao = new NutrientConstraintDaoImpl(this.connectionSource);
    }
    
    public NutritionalSheetDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, NutritionalSheet.class);
    }
    
    public void populateNutritionalSheet(NutritionalSheet nutritionalSheet) throws SQLException {
        List<NutrientConstraint> nutrientConstraintList = nutrientConstraintDao.getByNutritionalSheet(nutritionalSheet.getId());
        nutritionalSheet.setNutrientConstraintList(nutrientConstraintList);
    }
    
    @Override
    public CreateOrUpdateStatus createOrUpdate(NutritionalSheet nutritionalSheet) throws SQLException {
        int result;
        CreateOrUpdateStatus resultStatus = super.createOrUpdate(nutritionalSheet);
        if(nutritionalSheet.getNutrientConstraintList() != null) {
            List<NutrientConstraint> modifiedNutrientConstraints = nutritionalSheet.getNutrientConstraintList();
            List<NutrientConstraint> lastNutrientConstraints = nutrientConstraintDao.getByNutritionalSheet(nutritionalSheet.getId());
            
            //delete nutrientConstraints removed
            for(NutrientConstraint lastNutrientConstraint : lastNutrientConstraints) {
                Boolean delete = true;
                for(NutrientConstraint newNutrientConstraint : modifiedNutrientConstraints) {
                    if(lastNutrientConstraint.getId().equals(newNutrientConstraint.getId())) {
                        delete = false;
                        break;
                    }
                }
                if(delete) {
                    result = nutrientConstraintDao.deleteById(lastNutrientConstraint.getId());
                    if (result == 0) {
                        throw new SQLException("A problem ocoured trying to remove nutrients");
                    }
                }
            }
            
            //add or update nutrient constraints
            for(NutrientConstraint nutrientConstraint : modifiedNutrientConstraints) {
                nutrientConstraint.setNutritionalSheet(nutritionalSheet);
                nutrientConstraintDao.createOrUpdate(nutrientConstraint);
            }
            populateNutritionalSheet(nutritionalSheet);
        }
        return resultStatus;
    }
    
    @Override
    public int deleteById(Long nutritionalSheetId) throws SQLException {
        int result = 0;
        List<NutrientConstraint> nutrientConstraintList = nutrientConstraintDao.getByNutritionalSheet(nutritionalSheetId);
        for (NutrientConstraint nc : nutrientConstraintList) {
            result += nutrientConstraintDao.deleteById(nc.getId());
        }
        if(result == nutrientConstraintList.size()) {
            result = super.deleteById(nutritionalSheetId);
        } else {
            result = 0;
        }
        return result;
    }
}

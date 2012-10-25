package NutriaDao;

import NutriaModel.NutrientConstraint;
import NutriaModel.NutrientConstraintSheet;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class NutrientConstraintSheetDaoImpl extends BaseDaoImpl<NutrientConstraintSheet, Long> 
implements NutrientConstraintSheetDao {
    
    private NutrientConstraintDaoImpl nutrientConstraintDao;
    
    public NutrientConstraintSheetDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), NutrientConstraintSheet.class);
        nutrientConstraintDao = new NutrientConstraintDaoImpl(this.connectionSource);
    }
    
    public NutrientConstraintSheetDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, NutrientConstraintSheet.class);
    }
    
    public void populateNutrientConstraintSheet(NutrientConstraintSheet nutrientConstraintSheet) throws SQLException {
        List<NutrientConstraint> nutrientConstraintList = nutrientConstraintDao.getByNutrientConstraintSheet(nutrientConstraintSheet.getId());
        nutrientConstraintSheet.setNutrientConstraintList(nutrientConstraintList);
    }
    
    @Override
    public CreateOrUpdateStatus createOrUpdate(NutrientConstraintSheet nutrientConstraintSheet) throws SQLException {
        int result;
        CreateOrUpdateStatus resultStatus = super.createOrUpdate(nutrientConstraintSheet);
        if(nutrientConstraintSheet.getNutrientConstraintList() != null) {
            List<NutrientConstraint> modifiedNutrientConstraints = nutrientConstraintSheet.getNutrientConstraintList();
            List<NutrientConstraint> lastNutrientConstraints = nutrientConstraintDao.getByNutrientConstraintSheet(nutrientConstraintSheet.getId());
            
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
                nutrientConstraint.setNutritionalSheet(nutrientConstraintSheet);
                nutrientConstraintDao.createOrUpdate(nutrientConstraint);
            }
            populateNutrientConstraintSheet(nutrientConstraintSheet);
        }
        return resultStatus;
    }
    
    @Override
    public int deleteById(Long nutrientConstraintSheetId) throws SQLException {
        int result = 0;
        List<NutrientConstraint> nutrientConstraintList = nutrientConstraintDao.getByNutrientConstraintSheet(nutrientConstraintSheetId);
        for (NutrientConstraint nc : nutrientConstraintList) {
            result += nutrientConstraintDao.deleteById(nc.getId());
        }
        if(result == nutrientConstraintList.size()) {
            result = super.deleteById(nutrientConstraintSheetId);
        } else {
            result = 0;
        }
        return result;
    }
}

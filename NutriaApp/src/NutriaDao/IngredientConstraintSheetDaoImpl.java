package NutriaDao;

import NutriaModel.IngredientConstraintSheet;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author Ariel
 */
public class IngredientConstraintSheetDaoImpl extends BaseDaoImpl<IngredientConstraintSheet, Long> 
implements IngredientConstraintSheetDao {
    
    public IngredientConstraintSheetDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), IngredientConstraintSheet.class);
    }
    
    public IngredientConstraintSheetDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, IngredientConstraintSheet.class);
    }
}

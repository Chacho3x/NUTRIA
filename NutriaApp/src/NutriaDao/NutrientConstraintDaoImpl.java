package NutriaDao;

import NutriaModel.NutrientConstraint;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class NutrientConstraintDaoImpl extends BaseDaoImpl<NutrientConstraint, Long>
implements NutrientConstraintDao {
    
    private NutrientDaoImpl nutrientDao;
    
    public NutrientConstraintDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), NutrientConstraint.class);
        nutrientDao = new NutrientDaoImpl(this.connectionSource);
    }
    
    public NutrientConstraintDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, NutrientConstraint.class);
        nutrientDao = new NutrientDaoImpl(this.connectionSource);
    }
    
    public List<NutrientConstraint> getByNutritionalSheet(Long nutritionalSheetId) throws SQLException {
        QueryBuilder qb = this.queryBuilder();
        PreparedQuery pq = qb.where().eq("nutritional_sheet_id", nutritionalSheetId).prepare();
        List<NutrientConstraint> nutrientConstraintList = this.query(pq);
        for(NutrientConstraint nutConstraint : nutrientConstraintList) {
            nutConstraint.setNutrient(nutrientDao.queryForId(nutConstraint.getNutrient().getId()));
        }
        return nutrientConstraintList;
    }
}

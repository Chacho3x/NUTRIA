package nutriamodel;

import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;

/**
 *
 * @author Ariel
 */
public class NutrientDaoImpl extends BaseDaoImpl<Nutrient, Long> 
implements NutrientDao{
     
    public NutrientDaoImpl() throws SQLException{
        super(new DbConnection().getConnection(), Nutrient.class);
    }
}

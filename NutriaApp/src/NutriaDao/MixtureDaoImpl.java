package NutriaDao;

import NutriaModel.Mixture;
import NutriaModel.QuantifiedIngredient;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class MixtureDaoImpl extends BaseDaoImpl<Mixture, Long>
implements MixtureDao {
    
    private QuantifiedIngredientDaoImpl quantifiedIngredientDao;
    
    public MixtureDaoImpl() throws SQLException {
        super(new DbConnection().getConnection(), Mixture.class);
        quantifiedIngredientDao = new QuantifiedIngredientDaoImpl(this.connectionSource);
    }
    
    public MixtureDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Mixture.class);
        quantifiedIngredientDao = new QuantifiedIngredientDaoImpl(this.connectionSource);
    }
    
    public void populateMixture(Mixture mixture) throws SQLException {
        List<QuantifiedIngredient> quantifiedIngredientList = quantifiedIngredientDao.getByMixture(mixture.getId());
        mixture.setIngredientList(quantifiedIngredientList);
    }
    
    @Override
    public CreateOrUpdateStatus createOrUpdate(Mixture mixture) throws SQLException {
        int result;
        CreateOrUpdateStatus resultStatus = super.createOrUpdate(mixture);
        if(mixture.getIngredientList() != null) {
            List<QuantifiedIngredient> modifiedQuantifiedIngredients = mixture.getIngredientList();
            List<QuantifiedIngredient> lastQuantifiedIngredients = quantifiedIngredientDao.getByMixture(mixture.getId());
            
            //delete quantifiedIngredients removed
            for(QuantifiedIngredient lastQuantifiedIngredient : lastQuantifiedIngredients) {
                Boolean delete = true;
                for(QuantifiedIngredient newQuantifiedIngredient : modifiedQuantifiedIngredients) {
                    if(lastQuantifiedIngredient.getId().equals(newQuantifiedIngredient.getId())) {
                        delete = false;
                        break;
                    }
                }
                if(delete) {
                    result = quantifiedIngredientDao.deleteById(lastQuantifiedIngredient.getId());
                    if (result == 0) {
                        throw new SQLException("A problem ocoured trying to remove quantifiedIngredient");
                    }
                }
            }
            
            //add or update quantified Ingredients
            for(QuantifiedIngredient quantifiedIngredient : modifiedQuantifiedIngredients) {
                quantifiedIngredient.setMixture(mixture);
                quantifiedIngredientDao.createOrUpdate(quantifiedIngredient);
            }
            populateMixture(mixture);
        }
        return resultStatus;
    }
    
    @Override
    public int deleteById(Long nutritionalSheetId) throws SQLException {
        int result = 0;
        List<QuantifiedIngredient> quantifiedIngredientList = quantifiedIngredientDao.getByMixture(nutritionalSheetId);
        for (QuantifiedIngredient qi : quantifiedIngredientList) {
            result += quantifiedIngredientDao.deleteById(qi.getId());
        }
        if(result == quantifiedIngredientList.size()) {
            result = super.deleteById(nutritionalSheetId);
        } else {
            result = 0;
        }
        return result;
    }
    
}

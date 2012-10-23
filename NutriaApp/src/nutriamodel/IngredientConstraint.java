package NutriaModel;

/**
 *
 * @author Ariel
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ingredient_constraint")
public class IngredientConstraint {
    
    @DatabaseField(generatedId = true)
    private Long id;
    
    @DatabaseField(foreign = true, columnName = "ingredient_sheet_id")
    private IngredientConstraintSheet ingredientSheet;
    
    @DatabaseField(foreign = true, columnName = "ingredient_id")
    private Ingredient ingredient;
    
    @DatabaseField(columnName = "min_relationship")
    private String minRelationship;
    
    @DatabaseField(columnName = "min_bound")
    private Double minBound;
    
    @DatabaseField(columnName = "max_reltaionship")
    private String maxRelationship;
    
    @DatabaseField(columnName = "max_bound")
    private Double maxBound;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IngredientConstraintSheet getIngredientSheet() {
        return ingredientSheet;
    }

    public void setIngredientSheet(IngredientConstraintSheet ingredientSheet) {
        this.ingredientSheet = ingredientSheet;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getMinRelationship() {
        return minRelationship;
    }

    public void setMinRelationship(String minRelationship) {
        this.minRelationship = minRelationship;
    }

    public Double getMinBound() {
        return minBound;
    }

    public void setMinBound(Double minBound) {
        this.minBound = minBound;
    }

    public String getMaxRelationship() {
        return maxRelationship;
    }

    public void setMaxRelationship(String maxRelationship) {
        this.maxRelationship = maxRelationship;
    }

    public Double getMaxBound() {
        return maxBound;
    }

    public void setMaxBound(Double maxBound) {
        this.maxBound = maxBound;
    }

    @Override
    public String toString() {
        return "IngredientConstraint{" + "id=" + id 
                + ", ingredientSheet.id =" + ingredientSheet.getId()
                + ", ingredient.id =" + ingredient.getId()
                + ", minRelationship=" + minRelationship 
                + ", minBound=" + minBound 
                + ", maxRelationship=" + maxRelationship 
                + ", maxBound=" + maxBound + '}';
    }
}

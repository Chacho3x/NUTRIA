package nutriamodel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author Ariel
 */

@DatabaseTable(tableName = "nutrient_ingredient")
public class NutrientIngredient {
    
    @DatabaseField(generatedId = true)
    private Long id;
    
    @DatabaseField(foreign = true, columnName = "nutrient_id")
    private Nutrient nutrient;
    
    @DatabaseField(foreign = true, columnName = "ingredient_id")
    private Ingredient ingredient;

    public NutrientIngredient(){}
    
    public NutrientIngredient(Nutrient nutrient, Ingredient ingredient) {
        this.nutrient = nutrient;
        this.ingredient = ingredient;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "NutrientIngredient{" + "id=" + id + '}';
    }
}

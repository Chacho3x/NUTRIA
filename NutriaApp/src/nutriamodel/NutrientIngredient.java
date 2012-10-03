package NutriaModel;

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
    
    @DatabaseField(columnName = "nutrient_quantity")
    private Double nutrientQuantity;

    public NutrientIngredient(){}
    
    public NutrientIngredient(Nutrient nutrient, Ingredient ingredient, Double nutrientQuantity) {
        this.nutrient = nutrient;
        this.ingredient = ingredient;
        this.nutrientQuantity = nutrientQuantity;
    }
    
    public NutrientIngredient(Nutrient nutrient, Double nutrientQuantity)
    {
        this.nutrient = nutrient;
        this.nutrientQuantity = nutrientQuantity;
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
    
    public Double getNutrientQuantity() {
        return nutrientQuantity;
    }
    
    public void setNutrientQuantity(Double quantity) {
        this.nutrientQuantity = quantity;
    }

    @Override
    public String toString() {
        return "NutrientIngredient{" + "id=" + id + ", nutrient=" + nutrient + ", ingredient=" + ingredient + '}';
    }
}

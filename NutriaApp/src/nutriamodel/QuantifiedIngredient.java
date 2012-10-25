package NutriaModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author Ariel
 */
@DatabaseTable(tableName = "quantified_ingredient")
public class QuantifiedIngredient {
    
    @DatabaseField(generatedId = true)
    private Long id;
    
    @DatabaseField
    private Double quantity;
    
    @DatabaseField(columnName = "wet_quantity")
    private Double wetQuantity;
    
    @DatabaseField(columnName = "wet_percentage")
    private Double wetPercentage;
    
    @DatabaseField(columnName = "dry_quantity")
    private Double dryQuantity;
    
    @DatabaseField(columnName = "wet_cost")
    private Double wetCost;
    
    @DatabaseField(foreign = true, columnName = "ingredient_id")
    private Ingredient ingredient;
    
    @DatabaseField(foreign = true, columnName = "mixture_id")
    private Mixture mixture;

    public QuantifiedIngredient() { }
    
    public QuantifiedIngredient(Ingredient ingredient, Double wetQuantity, Double dryQuantity) { 
        this.ingredient = ingredient;
        this.wetQuantity = wetQuantity;
        this.dryQuantity = dryQuantity;
    }
    
    public QuantifiedIngredient(Ingredient ingredient, Double quantity) { 
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
    
    public QuantifiedIngredient(Mixture mixture, Ingredient ingredient, Double wetQuantity, Double dryQuantity) { 
        this.mixture = mixture;
        this.ingredient = ingredient;
        this.wetQuantity = wetQuantity;
        this.dryQuantity = dryQuantity;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    } 

    public Double getWetQuantity() {
        return wetQuantity;
    }

    public void setWetQuantity(Double wetQuantity) {
        this.wetQuantity = wetQuantity;
    }

    public Double getWetPercentage() {
        return wetPercentage;
    }

    public void setWetPercentage(Double wetPercentage) {
        this.wetPercentage = wetPercentage;
    }

    public Double getDryQuantity() {
        return dryQuantity;
    }

    public void setDryQuantity(Double dryQuantity) {
        this.dryQuantity = dryQuantity;
    }

    public Double getWetCost() {
        return wetCost;
    }

    public void setWetCost(Double wetCost) {
        this.wetCost = wetCost;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    
    public Mixture getMixture() {
        return mixture;
    }
    
    public void setMixture(Mixture mixture) {
        this.mixture = mixture;
    }

    @Override
    public String toString() {
        String result = "QuantifiedIngredient{" 
                + "id=" + id 
                + ", quantity=" + quantity
                + ", wetQuantity=" + wetQuantity 
                + ", wetPercentage=" + wetPercentage 
                + ", dryQuantity=" + dryQuantity 
                + ", wetCost=" + wetCost ;
        if(ingredient != null)
            result += ", ingredientId=" + ingredient.getId() ;
        if(mixture != null)
            result += ", mixtureId=" + mixture.getId();
        result += "}";
        return result;
    }
}

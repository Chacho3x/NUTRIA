package NutriaModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.List;

/**
 *
 * @author Ariel
 */
@DatabaseTable
public class Mixture {
    
    @DatabaseField(generatedId = true)
    private Long id;
    
    @DatabaseField
    private String name;
    
    @DatabaseField
    private String description;
    
    @DatabaseField(columnName = "wet_ingredients")
    private Boolean wetIngredients;
    
    @DatabaseField(columnName = "mixture_cost")
    private Double mixtureCost;
    
    @DatabaseField(columnName = "total_wet_quantity")
    private Double totalWetQuantity;
    
    @DatabaseField(columnName = "total_dry_quantity")
    private Double totalDryQuantity;
    
    @DatabaseField(columnName = "total_wet_percentage")
    private Double totalWetPercentage;
    
    private List<QuantifiedIngredient> ingredientList;
    
    public Mixture() { }
    
    public Mixture(String name, Boolean wetIngredients) {
        this.name = name;
        this.wetIngredients = wetIngredients;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getWetIngredients() {
        return wetIngredients;
    }

    public void setWetIngredients(Boolean wetIngredients) {
        this.wetIngredients = wetIngredients;
    }

    public Double getMixtureCost() {
        return mixtureCost;
    }

    public void setMixtureCost(Double mixtureCost) {
        this.mixtureCost = mixtureCost;
    }

    public Double getTotalWetQuantity() {
        return totalWetQuantity;
    }

    public void setTotalWetQuantity(Double totalWebQuantity) {
        this.totalWetQuantity = totalWebQuantity;
    }

    public Double getTotalDryQuantity() {
        return totalDryQuantity;
    }

    public void setTotalDryQuantity(Double totalDryQuantity) {
        this.totalDryQuantity = totalDryQuantity;
    }

    public Double getTotalWetPercentage() {
        return totalWetPercentage;
    }

    public void setTotalWetPercentage(Double totalWetPercentage) {
        this.totalWetPercentage = totalWetPercentage;
    }

    public List<QuantifiedIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<QuantifiedIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public String toString() {
        return "Mixture{" 
                + "id=" + id 
                + ", name=" + name 
                + ", description=" + description 
                + ", wetIngredients=" + wetIngredients 
                + ", mixtureCost=" + mixtureCost 
                + ", totalWebQuantity=" + totalWetQuantity 
                + ", totalDryQuantity=" + totalDryQuantity 
                + ", totalWetPercentage=" + totalWetPercentage 
                + '}';
    }
}
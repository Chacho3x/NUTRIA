package NutriaModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author Ariel
 */

@DatabaseTable(tableName = "nutrient_constraint")
public class NutrientConstraint {
    
    @DatabaseField(generatedId = true)
    private Long id;
    
    @DatabaseField(foreign = true, columnName = "nutritional_sheet_id")
    private NutrientConstraintSheet nutritionalSheet;
    
    @DatabaseField(foreign = true, columnName = "nutrient_id")
    private Nutrient nutrient;
    
    @DatabaseField(columnName = "min_relationship")
    private String minRelationship;
    
    @DatabaseField(columnName = "min_bound")
    private Double minBound;
    
    @DatabaseField(columnName = "max_relationship")
    private String maxRelationship;
    
    @DatabaseField(columnName = "max_bound")
    private Double maxBound;
    
    public NutrientConstraint(){}
    
    public NutrientConstraint(Nutrient nutrient) {
        this.nutrient = nutrient;
    }
    
    public NutrientConstraint(Nutrient nutrient, NutrientConstraintSheet nutritionalSheet, Double minBound, String minRelationship, Double maxBound, String maxRelationship) {
        this.nutrient = nutrient;
        this.nutritionalSheet = nutritionalSheet;
        this.minBound = minBound;
        this.minRelationship = minRelationship;
        this.maxBound = maxBound;
        this.maxRelationship = maxRelationship;
    }
    
    public NutrientConstraint(Nutrient nutrient, NutrientConstraintSheet nutritionalSheet, Double minBound, String minRelationship) {
        this.nutrient = nutrient;
        this.nutritionalSheet = nutritionalSheet;
        this.minBound = minBound;
        this.minRelationship = minRelationship;
    }
    
    public NutrientConstraint(Nutrient nutrient, Double minBound, String minRelationship, Double maxBound, String maxRelationship) {
        this.nutrient = nutrient;
        this.minBound = minBound;
        this.minRelationship = minRelationship;
        this.maxBound = maxBound;
        this.maxRelationship = maxRelationship;
    }
    
    public NutrientConstraint(Nutrient nutrient, Double minBound, String minRelationship) {
        this.nutrient = nutrient;
        this.minBound = minBound;
        this.minRelationship = minRelationship;
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
    
    public NutrientConstraintSheet getNutritionalSheet() {
        return nutritionalSheet;
    }
    
    public void setNutritionalSheet(NutrientConstraintSheet nutritionalSheet) {
        this.nutritionalSheet = nutritionalSheet;
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
        return "NutrientConstraint{" 
                + "id=" + id 
                + ", nutrient.id=" + nutrient.getId() 
                + ", nutritionalSheet.id=" + nutritionalSheet.getId() 
                + ", minRelationship=" + minRelationship 
                + ", minBound=" + minBound 
                + ", maxRelationship=" + maxRelationship 
                + ", maxBound=" + maxBound 
                + '}';
    }
}

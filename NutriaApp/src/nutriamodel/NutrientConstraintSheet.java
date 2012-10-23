package NutriaModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.List;

/**
 *
 * @author Ariel
 */
@DatabaseTable(tableName = "nutritional_sheet")
public class NutrientConstraintSheet {
    
    @DatabaseField(generatedId = true)
    private Long id;
    
    @DatabaseField
    private String name;
    
    private List<NutrientConstraint> nutrientConstraintList;

    public NutrientConstraintSheet() {}
    
    public NutrientConstraintSheet(String name) {
        this.name = name;
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

    public List<NutrientConstraint> getNutrientConstraintList() {
        return nutrientConstraintList;
    }

    public void setNutrientConstraintList(List<NutrientConstraint> nutrientConstraintList) {
        this.nutrientConstraintList = nutrientConstraintList;
    }

    @Override
    public String toString() {
        return "NutritionalSheet{" + "id=" + id + ", name=" + name + '}';
    }
}

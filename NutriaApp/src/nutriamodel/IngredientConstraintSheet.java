package NutriaModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.List;

/**
 *
 * @author Ariel
 */

@DatabaseTable(tableName = "ingredient_constraint_sheet")
public class IngredientConstraintSheet {
    
    @DatabaseField(generatedId = true)
    private Long id;
    
    @DatabaseField
    private String name;
    
    private List<IngredientConstraint> ingredientConstraintList;

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

    public List<IngredientConstraint> getIngredientConstraintList() {
        return ingredientConstraintList;
    }

    public void setIngredientConstraintList(List<IngredientConstraint> ingredientConstraintList) {
        this.ingredientConstraintList = ingredientConstraintList;
    }

    @Override
    public String toString() {
        return "IngredientSheet{" + "id=" + id + ", name=" + name + '}';
    }
}

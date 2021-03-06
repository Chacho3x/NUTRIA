package NutriaModel;
/**
 * @author Ariel
 */
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DatabaseTable
public class Ingredient implements Serializable {
    
    @DatabaseField(generatedId = true)
    private Long id;
    
    @DatabaseField
    private String name;
    
    @DatabaseField
    private Double price;
    
    @DatabaseField(columnName = "wetness_percentage")
    private Double wetnessPercentage;
    
    private List<NutrientIngredient> nutrients = new ArrayList<>();
    
    public Ingredient() {}
    
    public Ingredient(String name, Double price, Double wetnessPercentage) {
        this.name = name;
        this.price = price;
        this.wetnessPercentage = wetnessPercentage;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWetnessPercentage() {
        return wetnessPercentage;
    }

    public void setWetnessPercentage(Double roundingFactor) {
        this.wetnessPercentage = roundingFactor;
    }

    public List<NutrientIngredient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<NutrientIngredient> nutrients) {
        this.nutrients = nutrients;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "id=" + id + ", name=" + name + ", price=" + price + ", wetnessPercentage=" + wetnessPercentage + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.price);
        hash = 37 * hash + Objects.hashCode(this.wetnessPercentage);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingredient other = (Ingredient) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.wetnessPercentage, other.wetnessPercentage)) {
            return false;
        }
        return true;
    }
}
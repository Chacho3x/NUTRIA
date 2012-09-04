package nutriamodel;
/**
 * @author Ariel
 */
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "ingredient")
public class Ingredient implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private String name;
    @Column
    private Double price;
    @Column(name = "rounding_factor")
    private Double roundingFactor;
    
    private List<Nutrient> nutrients;

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

    public Double getRoundingFactor() {
        return roundingFactor;
    }

    public void setRoundingFactor(Double roundingFactor) {
        this.roundingFactor = roundingFactor;
    }

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "id=" + id + ", name=" + name + ", price=" + price + ", roundingFactor=" + roundingFactor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.price);
        hash = 37 * hash + Objects.hashCode(this.roundingFactor);
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
        if (!Objects.equals(this.roundingFactor, other.roundingFactor)) {
            return false;
        }
        return true;
    }
}
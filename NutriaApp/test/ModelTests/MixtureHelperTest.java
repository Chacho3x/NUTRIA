package ModelTests;

import NutriaApp.MixtureHelper;
import NutriaModel.Ingredient;
import NutriaModel.Mixture;
import NutriaModel.QuantifiedIngredient;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ariel
 */
public class MixtureHelperTest {
    
    @Test
    public void processMixtureWetQuantities() {
        Ingredient i1 = new Ingredient("Ingredient1", 10D, 20D);
        Ingredient i2 = new Ingredient("Ingredient2", 8D, 50D);
        Ingredient i3 = new Ingredient("Ingredient3", 15D, 10D);
        
        Mixture mixture1 = new Mixture("Mixture1", Boolean.TRUE);
        
        List<QuantifiedIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(new QuantifiedIngredient(i1, 50D));
        ingredientList.add(new QuantifiedIngredient(i2, 25D));
        ingredientList.add(new QuantifiedIngredient(i3, 25D));
        
        mixture1.setIngredientList(ingredientList);
        MixtureHelper.processMixture(mixture1);
        
        //assert Ingredient results
        ingredientList = mixture1.getIngredientList();
        assertEquals(new Double(50D), ingredientList.get(0).getWetQuantity());
        assertEquals(new Double(40D), ingredientList.get(0).getDryQuantity());
        assertEquals(new Double(50D), ingredientList.get(0).getWetPercentage());
        assertEquals(new Double(500D), ingredientList.get(0).getWetCost());
        
        assertEquals(new Double(25D), ingredientList.get(1).getWetQuantity());
        assertEquals(new Double(12.5D), ingredientList.get(1).getDryQuantity());
        assertEquals(new Double(25D), ingredientList.get(1).getWetPercentage());
        assertEquals(new Double(200D), ingredientList.get(1).getWetCost());
        
        assertEquals(new Double(25D), ingredientList.get(2).getWetQuantity());
        assertEquals(new Double(22.5D), ingredientList.get(2).getDryQuantity());
        assertEquals(new Double(25D), ingredientList.get(2).getWetPercentage());
        assertEquals(new Double(375D), ingredientList.get(2).getWetCost());
        
        //assert totals
        assertEquals(new Double(100D), mixture1.getTotalWetQuantity());
        assertEquals(new Double(75D), mixture1.getTotalDryQuantity());
        assertEquals(new Double(100D), mixture1.getTotalWetPercentage());
        assertEquals(new Double(1075D), mixture1.getMixtureCost());
    }
    
    @Test
    public void processMixtureDryQuantities() {
        Locale.setDefault(Locale.US);
        Ingredient i1 = new Ingredient("Ingredient1", 10D, 20D);
        Ingredient i2 = new Ingredient("Ingredient2", 8D, 50D);
        Ingredient i3 = new Ingredient("Ingredient3", 15D, 10D);
        
        Mixture mixture1 = new Mixture("Mixture1", Boolean.FALSE);
        
        List<QuantifiedIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(new QuantifiedIngredient(i1, 50D));
        ingredientList.add(new QuantifiedIngredient(i2, 25D));
        ingredientList.add(new QuantifiedIngredient(i3, 25D));
        
        mixture1.setIngredientList(ingredientList);
        MixtureHelper.processMixture(mixture1);
        
        //assert ingredient results
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        ingredientList = mixture1.getIngredientList();
        
        assertEquals(new Double(62.5D), Double.valueOf(twoDForm.format(ingredientList.get(0).getWetQuantity())));
        assertEquals(new Double(50D), Double.valueOf(twoDForm.format(ingredientList.get(0).getDryQuantity())));
        assertEquals(new Double(44.55D), Double.valueOf(twoDForm.format(ingredientList.get(0).getWetPercentage())));
        assertEquals(new Double(625D), Double.valueOf(twoDForm.format(ingredientList.get(0).getWetCost())));
        
        assertEquals(new Double(50D), Double.valueOf(twoDForm.format(ingredientList.get(1).getWetQuantity())));
        assertEquals(new Double(25D), Double.valueOf(twoDForm.format(ingredientList.get(1).getDryQuantity())));
        assertEquals(new Double(35.64D), Double.valueOf(twoDForm.format(ingredientList.get(1).getWetPercentage())));
        assertEquals(new Double(400D), Double.valueOf(twoDForm.format(ingredientList.get(1).getWetCost())));
        
        assertEquals(new Double(27.78D), Double.valueOf(twoDForm.format(ingredientList.get(2).getWetQuantity())));
        assertEquals(new Double(25D), Double.valueOf(twoDForm.format(ingredientList.get(2).getDryQuantity())));
        assertEquals(new Double(19.80D), Double.valueOf(twoDForm.format(ingredientList.get(2).getWetPercentage())));
        assertEquals(new Double(416.67D), Double.valueOf(twoDForm.format(ingredientList.get(2).getWetCost())));
        
        //assert totals
        assertEquals(new Double(140.28D), Double.valueOf(twoDForm.format(mixture1.getTotalWetQuantity())));
        assertEquals(new Double(100D), Double.valueOf(twoDForm.format(mixture1.getTotalDryQuantity())));
        assertEquals(new Double(100D), Double.valueOf(twoDForm.format(mixture1.getTotalWetPercentage())));
        assertEquals(new Double(1441.67D), Double.valueOf(twoDForm.format(mixture1.getMixtureCost())));
    }
}

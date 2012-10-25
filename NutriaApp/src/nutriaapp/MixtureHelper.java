package NutriaApp;

import NutriaModel.Mixture;
import NutriaModel.QuantifiedIngredient;

/**
 *
 * @author Ariel
 */
public abstract class MixtureHelper {
    
    public static void processMixture(Mixture mixture) {
        Double mixtureCost = 0D;
        Double totalWetQuantity = 0D;
        Double totalDryQuantity = 0D;
        Double totalWetPercentage = 0D;
        
        if (mixture.getWetIngredients()) {
            //ingredients in Wet quantities
            for(QuantifiedIngredient qIngredient : mixture.getIngredientList()) {
                //set the wetQuantity equal to the actual quantity
                qIngredient.setWetQuantity(qIngredient.getQuantity());
                
                //calculate the ingredient dryQuantity
                //Qdry = Qwet * (100 - %wetness) / 100
                Double qDry = qIngredient.getWetQuantity() * (100D - qIngredient.getIngredient().getWetnessPercentage()) / 100;
                qIngredient.setDryQuantity(qDry);
                
                //Add the actual wetQuantity to the totalWetQuantity
                totalWetQuantity += qIngredient.getWetQuantity();
                
                //Add the actual dryQuantity to the totalDryQuantity
                totalDryQuantity += qIngredient.getDryQuantity();
            }
        } else {
            //ingredients in dry quantities
            for(QuantifiedIngredient qIngredient : mixture.getIngredientList()) {
                //set the dryQuantity equal to the actual quantity
                qIngredient.setDryQuantity(qIngredient.getQuantity());
                
                //Calculate the wetQuantity
                //Qwet = Qdry * 100 / (100 - %wetness)
                Double wetQuantity = qIngredient.getDryQuantity() * 100D / (100D - qIngredient.getIngredient().getWetnessPercentage());
                qIngredient.setWetQuantity(wetQuantity);
                
                //add the wetQuantity to the totalWetQuantity
                totalWetQuantity += qIngredient.getWetQuantity();
                
                //add the actual dryQuantity to the totalDryQuantity
                totalDryQuantity += qIngredient.getDryQuantity();
            }
        }
        
        for(QuantifiedIngredient qIngredient : mixture.getIngredientList()) {
            //calculate the wetCost of the ingredient
            qIngredient.setWetCost(qIngredient.getWetQuantity() * qIngredient.getIngredient().getPrice());

            //Add the qIngredient wet cost to the mixtureCost
            mixtureCost += qIngredient.getWetCost();

            //Calculate the wetPercentage
            qIngredient.setWetPercentage(qIngredient.getWetQuantity() * 100D / totalWetQuantity);

            //add the ingredient wetPercentage to the totalWetPercentage
            totalWetPercentage += qIngredient.getWetPercentage();
        }
        
        System.out.println("totalPercentage: "+totalWetPercentage);
        //Set the values to the mixture
        mixture.setTotalDryQuantity(totalDryQuantity);
        mixture.setTotalWetQuantity(totalWetQuantity);
        mixture.setMixtureCost(mixtureCost);
        mixture.setTotalWetPercentage(totalWetPercentage);
    }
}

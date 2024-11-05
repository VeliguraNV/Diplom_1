package praktikum;

import org.junit.Assert;
import org.junit.Test;


public class IngredientTest {
    IngredientType type = IngredientType.SAUCE;
    private static final String nameIngredient = "Соус";
    private static final float priceIngredient = 100;

    @Test
    public void getPrice() {
        Ingredient ingredient = new Ingredient(type, nameIngredient, priceIngredient);
        Assert.assertEquals(priceIngredient, ingredient.getPrice(), 0);
    }

    @Test
    public void getName() {
        Ingredient ingredient = new Ingredient(type, nameIngredient, priceIngredient);
        Assert.assertEquals(nameIngredient, ingredient.getName());
    }

    @Test
    public void getType() {
        Ingredient ingredient = new Ingredient(type, nameIngredient, priceIngredient);
        Assert.assertEquals(type,ingredient.getType());
    }
}
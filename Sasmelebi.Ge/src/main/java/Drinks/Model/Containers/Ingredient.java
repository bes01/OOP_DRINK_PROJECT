package Drinks.Model.Containers;

public class Ingredient implements IngredientDao {
    private int ingredientId;
    private String ingredientName;

    public Ingredient(int ingredientId, String ingredientName) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
    }

    @Override
    public int getIngredientId() {
        return 0;
    }

    @Override
    public String getIngredientName() {
        return null;
    }
}

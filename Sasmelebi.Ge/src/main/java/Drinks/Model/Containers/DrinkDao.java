package Drinks.Model.Containers;

import java.util.ArrayList;

public interface DrinkDao {
    public int getDrinkId();

    public String getDrinkName();

    public String getImagePath();

    public String getInstruction();

    public int getParentId();

    public ArrayList<Ingredient> getMyIngredients();
}

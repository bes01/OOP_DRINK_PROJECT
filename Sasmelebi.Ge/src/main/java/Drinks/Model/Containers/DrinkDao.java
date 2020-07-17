package Drinks.Model.Containers;

import java.util.ArrayList;
import java.util.Date;

public interface DrinkDao {
    public int getDrinkId();

    public String getDrinkName();

    public String getImagePath();

    public String getInstruction();

    public int getParentId();

    public int getAuthorId();

    public Date getAdditionTime();

    public ArrayList<Ingredient> getMyIngredients();
}

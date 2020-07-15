package Drinks.Model.DataBase.DrinkDao;

import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.Ingredient;

import java.util.ArrayList;

public class TheDrinkData {

    public Drink getDrink(int drinkId) {
        return null;
    }

    public ArrayList<Ingredient> getIngredients(int drinkId) throws SQLException {
        Connector connector = Connector.getInstance();
        PreparedStatement st = connector.getStatement(FROM_INGREDIENTS);
        st.setString(1, Constants.schema + ".drinks_ingredients");
        st.setString(2, Constants.schema + ".ingredients");
        st.setString(3, Constants.schema + ".drinks_ingredients.ingredient_id");
        st.setString(4, Constants.schema + ".ingredients.ingredient_id");
        st.setInt(5, drinkId);
        ResultSet res = connector.executeQuery(st);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        while (res.next())
            ingredients.add(new Ingredient(res.getInt("ingredient_id"),
                    res.getString("ingredient_name")));
        return ingredients;
    }

    public boolean isRated(int drinkId){
        return false;
    }
}

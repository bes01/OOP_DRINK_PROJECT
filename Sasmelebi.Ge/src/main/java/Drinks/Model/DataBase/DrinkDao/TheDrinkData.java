package Drinks.Model.DataBase.DrinkDao;

import Drinks.Constants.Constants;
import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TheDrinkData {

    Connector connector;

    public TheDrinkData(){
        connector = Connector.getInstance();
    }

    public Drink getDrink(int drinkId) throws SQLException {
        Connector connector = Connector.getInstance();
        PreparedStatement st = connector.getStatement(FROM_DRINKS);
        st.setString(1, Constants.schema + ".drinks");
        st.setInt(2, drinkId);
        ResultSet res = connector.executeQuery(st);
        ArrayList<Ingredient> ingredients = getIngredients(drinkId);
        Drink drink = new Drink(res.getInt(1), res.getString(2), res.getString(3),
                res.getString(4), res.getInt(5), res.getInt(6), ingredients);
        return drink;
    }

    public ArrayList<Ingredient> getIngredients(int drinkId) throws SQLException {
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

    public boolean isRated(int drinkId) throws SQLException {
        Drink drink = getDrink(drinkId);
        PreparedStatement st = connector.getStatement(FROM_RANKING);
        st.setString(1, Constants.schema + ".ranking");
        st.setInt(2, drink.getDrinkId());
        st.setInt(3, drink.getAuthorId());
        ResultSet res = connector.executeQuery(st);
        return res.next();
    }
    public static final String FROM_DRINKS = "SELECT * FROM ? WHERE drink_id = ?";
    public static final String FROM_INGREDIENTS = "SELECT * FROM ? " +
            " JOIN ? ON ? = ? " + " WHERE drink_id = ?";
    public static final String FROM_RANKING = "SELECT * FROM ? WHERE drink_id = ? AND  user_id = ?";
}

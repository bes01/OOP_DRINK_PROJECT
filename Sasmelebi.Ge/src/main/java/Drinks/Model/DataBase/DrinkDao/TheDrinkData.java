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

    public TheDrinkData() throws SQLException {
        connector = Connector.getInstance();
        PreparedStatement st = connector.getStatement("USE " + Constants.schema);
        connector.execute(st);
    }

    public Drink getDrink(int drinkId) throws SQLException {
        PreparedStatement st = connector.getStatement(FROM_DRINKS);
        st.setInt(1, drinkId);
        ResultSet res = connector.executeQuery(st);
        ArrayList<Ingredient> ingredients = getIngredients(drinkId);
        res.next();
        Drink drink = new Drink(res.getInt("drink_id"), res.getString("drink_name"),
                res.getString("image"), res.getString("instruction"), res.getInt("parent_id"),
                res.getInt("author"), ingredients);
        return drink;
    }

    public ArrayList<Ingredient> getIngredients(int drink_id) throws SQLException {
        PreparedStatement st = connector.getStatement(FROM_INGREDIENTS);
        st.setInt(1, drink_id);
        ResultSet res = connector.executeQuery(st);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        while (res.next())
            ingredients.add(new Ingredient(res.getInt("ingredient_id"), res.getString("ingredient_name")));
        return ingredients;
    }

    public boolean isRated(int drinkId) throws SQLException {
        Drink drink = getDrink(drinkId);
        PreparedStatement st = connector.getStatement(FROM_RANKING);
        st.setString(1, "ranking");
        st.setInt(2, drink.getDrinkId());
        st.setInt(3, drink.getAuthorId());
        ResultSet res = connector.executeQuery(st);
        return res.next();
    }
    public static final String FROM_DRINKS = "SELECT * FROM drinks WHERE drink_id = ?";
    public static final String FROM_INGREDIENTS = "SELECT * FROM drinks_ingredients " +
            " JOIN ingredients ON drinks_ingredients.ingredient_id = ingredients.ingredient_id " +
            " WHERE drink_id = ?";
    public static final String FROM_RANKING = "SELECT * FROM ? WHERE drink_id = ? AND  user_id = ?";
}

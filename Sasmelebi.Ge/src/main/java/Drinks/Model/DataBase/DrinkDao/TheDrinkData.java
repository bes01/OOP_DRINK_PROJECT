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

    public ArrayList<Ingredient> getIngredients(int drinkId){
        return null;
    }

    public boolean isRated(int drinkId){
        return false;
    }
    public static final String FROM_DRINKS = "SELECT * FROM ? WHERE drink_id = ?";
    public static final String FROM_INGREDIENTS = "SELECT * FROM ? " +
            " JOIN ? ON ? = ? " + " WHERE drink_id = ?";
}

package Drinks.Model.DataBase.DrinkDao;

import Drinks.Constants.Constants;
import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.DrinkDao;
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

    public Drink getDrink(int drinkId) {
        try {
            PreparedStatement st = connector.getStatement(FROM_DRINKS);
            st.setInt(1, drinkId);
            ResultSet res = connector.executeQuery(st);
            ArrayList<Ingredient> ingredients = getIngredients(drinkId);
            res.next();
            Drink drink = new Drink(res.getInt("drink_id"), res.getString("drink_name"),
                    res.getString("image"), res.getString("instruction"), res.getInt("parent_id"),
                    res.getInt("author"), res.getDate("addition_time"), ingredients);
            return drink;
        } catch (SQLException throwables) {
            return null;
        }
    }

    public ArrayList<Ingredient> getIngredients(int drink_id) {
        try {
            PreparedStatement st = connector.getStatement(FROM_INGREDIENTS);
            st.setInt(1, drink_id);
            ResultSet res = connector.executeQuery(st);
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            while (res.next())
                ingredients.add(new Ingredient(res.getInt("ingredient_id"), res.getString("ingredient_name")));
            return ingredients;
        } catch (SQLException throwables) {
            return new ArrayList<Ingredient>();
        }
    }

    public double sumRated(int drink_id) throws SQLException {
        PreparedStatement st = connector.getStatement(FROM_RANKING);
        st.setInt(1, drink_id);
        ResultSet res = connector.executeQuery(st);
        res.next();
        System.out.println(res.getDouble("AVG"));
        return res.getDouble("AVG");
    }

    public int userRated(int drink_id, int user_id) throws SQLException {
        PreparedStatement st = connector.getStatement(FROM_RANKING_USER);
        st.setInt(1, drink_id);
        st.setInt(2, user_id);
        ResultSet res = connector.executeQuery(st);
        res.next();
        return res.getInt("SUM");
    }

    public void justRated(int drink_id, int user_id, int score) throws SQLException {
        if(userRated(drink_id, user_id) == 0) insertRanking(drink_id, user_id, score);
        else updateRanking(drink_id, user_id, score);
    }

    private void insertRanking(int drink_id, int user_id, int score) throws SQLException {
        PreparedStatement st = connector.getStatement(INSERT_RANKING);
        st.setInt(1, user_id);
        st.setInt(2, drink_id);
        st.setInt(3, score);
        connector.execute(st);
    }

    private void updateRanking(int drink_id, int user_id, int score) throws SQLException {
        PreparedStatement st = connector.getStatement(UPDATE_RANKING);
        st.setInt(1, score);
        st.setInt(2, user_id);
        st.setInt(3, drink_id);
        connector.execute(st);
    }

    public Drink getParentDrink(int drink_id) throws SQLException {
        Drink curr = getDrink(drink_id);
        if(curr.getParentId() == 0) return null;
        return getDrink(curr.getParentId());
    }

    public boolean isInFavourites(int drink_id, int user_id) throws SQLException {
        PreparedStatement st = connector.getStatement(FROM_FAVOURITES);
        st.setInt(1, user_id);
        st.setInt(2, drink_id);
        ResultSet res = connector.executeQuery(st);
        return res.next();
    }

    public boolean isInUserDrinks(int drink_id, int user_id) throws SQLException {
        Drink drink = getDrink(drink_id);
        return user_id == drink.getAuthorId();
    }

    public boolean addInFavourites(int drink_id, int user_id) throws SQLException {
        if(isInFavourites(drink_id, user_id) || isInUserDrinks(drink_id, user_id)) return false;
        PreparedStatement st = connector.getStatement(INSERT_FAVOURITES);
        st.setInt(1, user_id);
        st.setInt(2, drink_id);
        connector.execute(st);
        return true;
    }

    private static final String FROM_DRINKS = "SELECT * FROM drinks WHERE drink_id = ?";
    private static final String FROM_INGREDIENTS = "SELECT * FROM drinks_ingredients " +
            " JOIN ingredients ON drinks_ingredients.ingredient_id = ingredients.ingredient_id " +
            " WHERE drink_id = ?";
    private static final String FROM_RANKING = "SELECT SUM(rank_score)/COUNT(rank_score) as AVG FROM ranking WHERE drink_id = ?";
    private static final String FROM_RANKING_USER = "SELECT SUM(rank_score) as SUM " +
            "FROM ranking WHERE drink_id = ? AND user_id = ?";
    private static final String INSERT_RANKING = "INSERT INTO ranking (user_id, drink_id, rank_score) VALUES(?,?,?);";
    private static final String UPDATE_RANKING = "UPDATE ranking SET " +
            " rank_score = ? WHERE user_id = ? AND drink_id = ?";
    private static final String FROM_FAVOURITES = "SELECT * FROM favourites WHERE user_id = ? AND " +
            "drink_id = ?";
    private static final String INSERT_FAVOURITES = "INSERT INTO favourites (user_id, drink_id) VALUES(?,?);";
}

package Drinks.Model.DataBase.DataRetrieve;

import Drinks.Constants.Constants;
import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.Connector;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DrinkData {
    private Connector connector;

    public DrinkData() {
        this.connector = Connector.getInstance();
    }

    public ArrayList<Drink> favourites(int user_id) throws SQLException {
        PreparedStatement statement = connector.getStatement(
                "select * from " + Constants.schema +
                        ".favourites " +
                        "join " + Constants.schema + ".drinks on " + Constants.schema +
                        ".favourites.drink_id = " + Constants.schema + ".drinks.drink_id " +
                        "where user_id = ?");
        return getDrinks(user_id, statement);
    }

    public ArrayList<Drink> userDrinks(int user_id) throws SQLException {
        PreparedStatement statement = connector.getStatement("select * from " + Constants.schema + ".drinks " +
                "where author = ?");
        return getDrinks(user_id, statement);
    }

    private ArrayList<Drink> getDrinks(int user_id, PreparedStatement statement) throws SQLException {
        statement.setInt(1, user_id);
        return getDrinksArray(statement, -1);
    }

    public ArrayList<Drink> getDrinksArray(PreparedStatement statement, int quantity) throws SQLException {
        ArrayList<Drink> drinks = new ArrayList<>();
        ResultSet set = connector.executeQuery(statement);
        int counter = 0;
        while (set.next()) {
            drinks.add(new Drink(set.getInt("drink_id"), set.getString("drink_name"),
                    set.getString("image"), set.getString("instruction"), set.getInt("parent_id"),
                    set.getInt("author"), set.getDate("addition_time"),
                    getIngredients(set.getInt("drink_id"))));
            if(quantity != -1 && ++counter == quantity)
                break;
        }
        return drinks;
    }

    public ArrayList<Drink> getDrinksByNameAndIngredients(String name, int[] ingredientIds) {
        ArrayList<Drink> drinks = new ArrayList<>();
        String query = getQuery(name, ingredientIds);
        try {
            PreparedStatement stmt = connector.getStatement(query);
            ResultSet rs = connector.executeQuery(stmt);
            while (rs.next()) {
                int drinkId = rs.getInt(1);
                String drinkName = rs.getString(2);
                String image = rs.getString(3);
                Drink drink = new Drink(drinkId, drinkName, image, "", -1, -1, null);
                if(areIngredientEnough(drink,ingredientIds))
                    drinks.add(drink);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drinks;

    }

    private boolean areIngredientEnough(Drink drink, int[] ingredientIds) {
        IngredientData ingData = new IngredientData();
        ArrayList<Ingredient> ingredients = ingData.getIngredientsByDrinkId(drink.getDrinkId());
        Set<Integer> ingredientIdSet = getIngredientIdSet(ingredientIds);
        for(int i=0;i<ingredients.size();i++){
            if(!ingredientIdSet.contains(ingredients.get(i).getIngredientId()))
                return  false;

        }
        return  true;
    }

    private Set<Integer> getIngredientIdSet(int[] ingredientIds) {
        Set<Integer> ids = new HashSet<>();
        for(int i=0;i<ingredientIds.length;i++){
            ids.add(ingredientIds[i]);

        }
        return  ids;
    }

    private String getQuery(String name, int[] ingredientIds) {
        if(ingredientIds == null){
            String query ="Select   d.drink_id , d.drink_name, d.image "+
                    " from drinks d " +
                    " where d.drink_name Like Concat(\"%\" , \"" + name + "\" , \"%\")";
            return  query;

        }
        String query = "select d.drink_id , d.drink_name, d.image  " +
                " from drinks_ingredients di " +
                " join ingredients i " +
                " on di.ingredient_id = i.ingredient_id " +
                " join drinks d " +
                " on d.drink_id = di.drink_id " +
                " where d.drink_name Like Concat(\"%\" , \"" + name + "\" , \"%\")" +
                " and i.ingredient_id in " + getConcatStringIds(ingredientIds) +
                " group by d.drink_name " ;
                //+
                //" having count(d.drink_name) >= " + ingredientIds.length + " ;";
        return query;
    }

    private String getConcatStringIds(int[] ingredientIds) {
        String str = "(";
        str += ingredientIds[0];
        for (int i = 1; i < ingredientIds.length; i++) {

            str += " , " + ingredientIds[i];

        }
        str += ")";
        return str;

    }

    public ArrayList<Ingredient> getIngredients(int drink_id) throws SQLException {
        PreparedStatement statement = connector.getStatement("select * from " + Constants.schema +
                ".drinks_ingredients join " + Constants.schema + ".ingredients on " +
                Constants.schema + ".drinks_ingredients.ingredient_id = " + Constants.schema + ".ingredients.ingredient_id " +
                "where drink_id = ?");
        statement.setInt(1, drink_id);
        ResultSet set = statement.executeQuery();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        while (set.next())
            ingredients.add(new Ingredient(set.getInt("ingredient_id"), set.getString("ingredient_name")));
        return ingredients;
    }
}

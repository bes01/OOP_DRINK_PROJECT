package Drinks.Model.DataBase.DataRetrieve;

import Drinks.Constants.Constants;
import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
            if (quantity != -1 && ++counter == quantity)
                break;
        }
        return drinks;
    }

    public HashSet<Drink> getDrinksByNameAndIngredients(String name, int[] ingredientIds) {
        if(ingredientIds == null)
            return getDrinksByName(name);

        HashSet<Drink> allDrinks = new HashSet<>();

        Set<ArrayList<Integer>> allPossibleSubsets = getAllPossibleSubsets(ingredientIds);

        for (ArrayList<Integer> currSubSet : allPossibleSubsets) {
            if(currSubSet.size() == 0)
                continue;
            String query = getQuery(name, currSubSet);
            allDrinks.addAll(getDrinksByQuery(query));

        }

        return allDrinks;

    }
    private HashSet<Drink> getDrinksByQuery(String query){
        HashSet<Drink> drinks = new HashSet<>();
        try {
            PreparedStatement stmt = connector.getStatement(query);
            ResultSet rs = connector.executeQuery(stmt);
            while (rs.next()) {
                int drinkId = rs.getInt(1);
                String drinkName = rs.getString(2);
                String image = rs.getString(3);
                Drink drink = new Drink(drinkId, drinkName, image, "", -1, -1, null);
                drinks.add(drink);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drinks;


    }

    private HashSet<Drink> getDrinksByName(String name) {
        String query = getNoIngredientQuery(name);
        return getDrinksByQuery(query);
    }

    private HashSet<ArrayList<Integer>> getAllPossibleSubsets( int[] ingredientIds) {
        HashSet<ArrayList<Integer>> subsets = new HashSet<>();
        ArrayList<Integer> emptyArrayList = new ArrayList<>();
        subsets.add(emptyArrayList);
        return getAllPossibleSubsetsHelper(subsets, ingredientIds, 0);
    }

    private HashSet<ArrayList<Integer>> getAllPossibleSubsetsHelper(HashSet<ArrayList<Integer>> subsets, int[] ingredientIds, int index) {
        if(index >= ingredientIds.length)
            return subsets;

        HashSet<ArrayList<Integer>> newSubSets = new HashSet<>(subsets);
        for (ArrayList<Integer> currElem : subsets) {
            ArrayList<Integer> newElem = new ArrayList<>(currElem);
            newElem.add(ingredientIds[index]);
            newSubSets.add(newElem);

        }
        return getAllPossibleSubsetsHelper(newSubSets, ingredientIds, index + 1);

    }

    private String getNoIngredientQuery(String name) {

        String query = "Select   d.drink_id , d.drink_name, d.image " +
                " from drinks d " +
                " where d.drink_name Like Concat(\"%\" , \"" + name + "\" , \"%\")";
        return query;


    }

    private String getQuery(String name, ArrayList<Integer> ingredientIds) {

        String query = "select d.drink_id , d.drink_name, d.image  " +
                " from drinks_ingredients di " +
                " join ingredients i " +
                " on di.ingredient_id = i.ingredient_id " +
                " join drinks d " +
                " on d.drink_id = di.drink_id " +
                " where d.drink_name Like Concat(\"%\" , \"" + name + "\" , \"%\")" +
                " and i.ingredient_id in " + getConcatStringIds(ingredientIds) +
                " group by d.drink_name " +
                " having count(d.drink_name) = " + ingredientIds.size() + " ;";
        return query;
    }

    private String getConcatStringIds(ArrayList<Integer> ingredientIds) {
        String str = "(";
        str += ingredientIds.get(0);
        for (int i = 1; i < ingredientIds.size(); i++) {

            str += " , " + ingredientIds.get(i);

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

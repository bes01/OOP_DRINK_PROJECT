package Drinks.Model.DataBase.DataRetrieve;

import Drinks.Constants.Constants;
import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientData {
    Connector connection;

    public IngredientData() {
        this.connection = Connector.getInstance();
    }

    private  ArrayList<Ingredient> getIngredientsByQuery(String query){
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        try {
            PreparedStatement firstStatement = connection.getStatement("Use " + Constants.schema);
            connection.execute(firstStatement);
            PreparedStatement stmt = connection.getStatement(query);
            ResultSet rs = connection.executeQuery(stmt);
            while (rs.next()){
                int ingredientId = rs.getInt(1);
                String ingredientName = rs.getString(2);
                Ingredient ing = new Ingredient(ingredientId,ingredientName);
                ingredients.add(ing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  ingredients;


    }

    public ArrayList<Ingredient> getAllIngredients() {

        return getIngredientsByQuery("select * from ingredients;");
    }

    public ArrayList<Ingredient> getIngredientsByDrinkId(int drinkId) {

       String query ="select i.ingredient_id , i.ingredient_name " +
               " from drinks_ingredients di " +
               " join ingredients i " +
               " on di.ingredient_id = i.ingredient_id " +
               " join drinks d " +
               " on d.drink_id = di.drink_id " +
               " where d.drink_id = " + drinkId + " ;" ;
       return getIngredientsByQuery(query);
    }




}

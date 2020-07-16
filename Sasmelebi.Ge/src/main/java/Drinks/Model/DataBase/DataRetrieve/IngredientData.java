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

    public ArrayList<Ingredient> getAllIngredients() {

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        try {
            PreparedStatement firstStatement = connection.getStatement("Use " + Constants.schema);
            connection.execute(firstStatement);
            PreparedStatement stmt = connection.getStatement("select * from ingredients;");
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


}

package Drinks.Model.DataBase.IngredientDao;

import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IngredientData {
    private Connector connector;
    public IngredientData (){
        connector=Connector.getInstance();
    }
    public Ingredient getIngredient(String s)  {

        PreparedStatement statement = null;
        try {
            statement = connector.getStatement("SELECT ingredient_id,ingredient_name FROM ingredients WHERE ingredient_name=?;");
            statement.setString(1,s);
            ResultSet resultSet=connector.executeQuery(statement);
            if(resultSet.next()) {
                int id = resultSet.getInt(1);
                String s1 = resultSet.getString(2);
                return new Ingredient(id,s1);
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addNewIngredient(String s) {
        try {
            String statementString="INSERT INTO ingredients(ingredient_name) VALUES(?);";
            PreparedStatement preparedStatement= null;
            preparedStatement = connector.getStatement(statementString);
            preparedStatement.setString(1,s);
            connector.execute(preparedStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

package Drinks.Model.DataBase.RecipeDao;

import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.Connector;
import Drinks.Model.DataBase.IngredientDao.IngredientData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import static java.sql.Types.NULL;

public class RecipeAddition {
    private Connector connector;
    private IngredientData ingredientData;
    public RecipeAddition() {
        connector= Connector.getInstance();
        ingredientData= new IngredientData();
    }
    public void addDrink(Drink user) {
        try {
            String query="INSERT INTO drinks (drink_name,image,instruction,parent_id,author) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connector.getStatement(query);
            preparedStatement.setString(1,user.getDrinkName());
            preparedStatement.setString(2,user.getImagePath());
            preparedStatement.setString(3,user.getInstruction());
            if (user.getParentId()==-1){
                preparedStatement.setNull(4, Types.INTEGER );
            }else{
                preparedStatement.setInt(4,user.getParentId());
            }
            preparedStatement.setInt(5,user.getAuthorId());
            connector.execute(preparedStatement);
            int drinkId = getDrinkID(user);
            ArrayList<Ingredient> ingredients=user.getMyIngredients();

            for (int i = 0; i <ingredients.size(); i++) {
                Ingredient ingredient =ingredientData.getIngredient(ingredients.get(i).getIngredientName());
                String subQuery="SELECT ingredient_id FROM ingredients WHERE ingredient_name=?;";
                PreparedStatement statement = connector.getStatement(subQuery);
                statement.setString(1,ingredients.get(i).getIngredientName());
                ResultSet resultSet =connector.executeQuery(statement);
                if(resultSet.next()){
                    int ingredientId = resultSet.getInt(1);
                    String insertQuery="INSERT INTO drinks_ingredients (drink_id,ingredient_id) VALUES(?,?);";
                    PreparedStatement insertStatement = connector.getStatement(insertQuery);
                    insertStatement.setInt(1,drinkId);
                    insertStatement.setInt(2,ingredientId);
                    connector.execute(insertStatement);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private int getDrinkID(Drink user) {
        int res=0;
        try {
            String queryFinal="";
            PreparedStatement preparedStatement;
            if (user.getParentId() == -1) {
                queryFinal ="SELECT drink_id FROM drinks WHERE drink_name=? AND image=? AND instruction=? AND parent_id is null AND author=? ;";
                preparedStatement=connector.getStatement(queryFinal);
                preparedStatement.setString(1,user.getDrinkName());
                preparedStatement.setString(2,user.getImagePath());
                preparedStatement.setString(3,user.getInstruction());
                preparedStatement.setInt(4,user.getAuthorId());
            }else{
                queryFinal ="SELECT drink_id FROM drinks WHERE drink_name=? AND image=? AND instruction=? AND parent_id=? AND author=? ;";
                preparedStatement=connector.getStatement(queryFinal);
                preparedStatement.setString(1,user.getDrinkName());
                preparedStatement.setString(2,user.getImagePath());
                preparedStatement.setString(3,user.getInstruction());
                preparedStatement.setInt(4,user.getParentId());
                preparedStatement.setInt(5,user.getAuthorId());
            }
            ResultSet resultSet=connector.executeQuery(preparedStatement);
            while(resultSet.next())res=resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

}

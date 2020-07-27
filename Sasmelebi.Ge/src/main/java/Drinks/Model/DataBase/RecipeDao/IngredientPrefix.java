package Drinks.Model.DataBase.RecipeDao;

import Drinks.Model.DataBase.Connector;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientPrefix {
    public Connector connector;
    public IngredientPrefix(){
        connector= Connector.getInstance();
    }
    public String[] getIngredientsByPrefix() throws SQLException {
        String query="SELECT ingredient_name FROM ingredients;";
        PreparedStatement preparedStatement= connector.getStatement(query);
        ResultSet resultSet =connector.executeQuery(preparedStatement);
        ArrayList<String> arrayList= new ArrayList<>();
        while(resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        String[] mas= new String[arrayList.size()];
        for (int i=0; i<arrayList.size(); i++){
         mas[i]=arrayList.get(i);
        }
        return mas;
    }
}

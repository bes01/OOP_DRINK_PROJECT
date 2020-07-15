package Drinks.Model.DataBase.RecipeDao;

import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.Connector;
import Drinks.Model.DataBase.DataRetrieve.DrinkData;
import Drinks.Model.DataBase.IngredientDao.IngredientData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExistenceChecker {
    private Connector connector;
    private IngredientData ingredientData;
    private DrinkData drinkData;
    public ExistenceChecker(){
        ingredientData= new IngredientData();
        drinkData= new DrinkData();
        connector=Connector.getInstance();
    }
    public boolean checkExistance(String[] enumeration, String name, String path,
                                  String instruction, int parentId, int authorId) {

        try {
            String query="SELECT drink_id FROM drinks WHERE drink_name=? AND image=? AND instruction=? AND author=? AND parent_id";
            if (parentId==-1){
                query+=" is null;";
            }else{
                query+="=?;";
            }
            PreparedStatement preparedStatement= null;
            preparedStatement = connector.getStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,path);
            preparedStatement.setString(3,instruction);
            preparedStatement.setInt(4,authorId);
            if (parentId!=-1) preparedStatement.setInt(5,authorId);
            ResultSet resultSet=connector.executeQuery(preparedStatement);

            while (resultSet.next()){
                ArrayList<Ingredient>  arrayList= new ArrayList<>();
                boolean isSame=true;
                int drink_id = resultSet.getInt(1);
                for (int i=0; i<enumeration.length; i++){
                    Ingredient ingredient=ingredientData.getIngredient(enumeration[i]);
                    if (ingredient==null) isSame=false;
                    else {
                        arrayList.add(ingredient);
                    }
                }

                ArrayList<Ingredient> drinkIngredients = drinkData.getIngredients(drink_id);
                if (drinkIngredients.size()!=arrayList.size()) isSame=false;
                else {
                    for (int i = 0; i < arrayList.size(); i++) {
                        Ingredient curIngredient = arrayList.get(i);
                        boolean found = false;
                        for (int j = 0; j < drinkIngredients.size(); j++) {
                            if (drinkIngredients.get(j).getIngredientId() == curIngredient.getIngredientId()) {
                                drinkIngredients.remove(j);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            isSame = false;
                            break;
                        }
                    }
                }
                if (isSame) return  true;
            }

            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

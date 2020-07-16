package Drinks.Model.Containers;

import Drinks.Model.DataBase.DataRetrieve.UserData;
import Drinks.Model.DataBase.DrinkDao.TheDrinkData;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrinkFull {
    
    private int user_ranking, all_ranking;
    private Drink parent;
    
    public DrinkFull(int drink_id, int user_id) throws SQLException {
        TheDrinkData drData = new TheDrinkData();
        all_ranking = drData.sumRated(drink_id);
        user_ranking = drData.userRated(drink_id, user_id);
        parent = drData.getParentDrink(drink_id);
    }
    
    public int getCurrentRanking() {
        return all_ranking;
    }
    
    public int getCurrentUserRanking() {
        return user_ranking;
    }

    public Drink getParentDrink(){ return parent; }
}

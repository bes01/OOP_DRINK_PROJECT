package Drinks.Model.Containers;

import Drinks.Model.DataBase.DrinkDao.TheDrinkData;

import java.sql.SQLException;

public class DrinkFull {
    
    int user_ranking, all_ranking;
    
    public DrinkFull(int drink_id, int user_id) throws SQLException {
        TheDrinkData drData = new TheDrinkData();
        all_ranking = drData.sumRated(drink_id);
        user_ranking = drData.userRated(drink_id, user_id);
    }
    
    public int getCurrentRanking() {
        return all_ranking;
    }
    
    public int getCurrentUserRanking() {
        return user_ranking;
    }
}

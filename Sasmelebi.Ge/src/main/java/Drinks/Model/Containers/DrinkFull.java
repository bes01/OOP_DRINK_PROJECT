package Drinks.Model.Containers;

import java.sql.SQLException;

public class DrinkFull {
    
    private int user_ranking;
    private double all_ranking;
    private Drink parent;
    private User user;

    public DrinkFull(double all_ranking, int user_ranking, User user, Drink parent) throws SQLException {
        this.all_ranking = all_ranking;
        this.user_ranking = user_ranking;
        this.parent = parent;
        this.user = user;
    }
    
    public double getCurrentRanking() {
        return all_ranking;
    }
    
    public int getCurrentUserRanking() {
        return user_ranking;
    }

    public Drink getParentDrink(){ return parent; }

    public User getDrinksAuthor(){ return user; }
}

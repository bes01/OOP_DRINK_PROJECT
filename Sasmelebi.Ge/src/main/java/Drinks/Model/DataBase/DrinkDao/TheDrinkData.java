package Drinks.Model.DataBase.DrinkDao;

import Drinks.Model.Containers.Drink;
import Drinks.Model.DataBase.Connector;

public class TheDrinkData {

    private Connector conn;

    public TheDrinkData(){
        conn = Connector.getInstance();
    }

    public Drink getDrink(String drinkId){
        return null;
    }
}

package Drinks.Model.Containers;

interface DrinkFullDao {

    double getCurrentRanking();

    int getCurrentUserRanking();

    Drink getParentDrink();
}

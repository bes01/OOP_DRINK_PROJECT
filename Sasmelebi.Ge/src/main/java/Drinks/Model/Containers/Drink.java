package Drinks.Model.Containers;

import java.util.ArrayList;

public class Drink implements DrinkDao {
    private int drinkId;
    private String drinkName;
    private String imagePath;
    private String instruction;
    private int parentId;
    private int authorId;
    private ArrayList<Ingredient> myIngredients;

    public Drink(int drinkId, String drinkName, String imagePath, String instruction,
                 int parentId, int authorId, ArrayList<Ingredient> myIngredients) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.imagePath = imagePath;
        this.instruction = instruction;
        this.parentId = parentId;
        this.authorId = authorId;
        this.myIngredients = myIngredients;
    }

    @Override
    public int getDrinkId() {
        return drinkId;
    }

    @Override
    public String getDrinkName() {
        return drinkName;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String getInstruction() {
        return instruction;
    }

    @Override
    public int getParentId() {
        return parentId;
    }

    @Override
    public int getAuthorId(){
        return authorId;
    }

    @Override
    public ArrayList<Ingredient> getMyIngredients() {
        return myIngredients;
    }


    public int getAuthorId() {
        return authorId;
    }
}

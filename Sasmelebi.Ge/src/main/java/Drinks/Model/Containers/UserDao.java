package Drinks.Model.Containers;

import java.util.ArrayList;

interface UserDao {
    public int getUserId();

    public String getFirstName();

    public String getLastName();

    public String getNickName();

    public String getSex();

    public int getAge();

    public String getMail();

    public String getPassword();

    public double getRank();

    public ArrayList<Drink> getMyDinks();

    public ArrayList<Drink> getFavourites();
}

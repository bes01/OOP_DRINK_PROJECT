package Drinks.Model.Containers;

import java.util.ArrayList;

public class User implements UserDao {
    private int userId;
    private String firstName;
    private String lastName;
    private String nickName;
    private String sex;
    private int age;
    private String mail;
    private String password;
    private int rank;
    private ArrayList<Drink> myDrinks;
    private ArrayList<Drink> favourites;

    public User(int userId, String firstName, String lastName, String nickName, String sex,
                int age, String mail, String password, int rank, ArrayList<Drink> myDrinks, ArrayList<Drink> favourites) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.sex = sex;
        this.age = age;
        this.mail = mail;
        this.password = password;
        this.rank = rank;
        this.myDrinks = myDrinks;
        this.favourites = favourites;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public ArrayList<Drink> getMyDinks() {
        return myDrinks;
    }

    public ArrayList<Drink> getFavourites(){
        return  favourites;
    }
}

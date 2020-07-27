package Drinks.Model.DataBase.DataRetrieve;

import Drinks.Constants.Constants;
import Drinks.Model.Containers.User;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserData {
    private Connector connector;
    private DrinkData drinkData;

    public UserData() {
        this.connector = Connector.getInstance();
        drinkData = new DrinkData();
    }

    public void addUser(String firstName, String lastName, String nickname, String sex, int age, String mail,
                       String password) throws SQLException {
        Connector connector = Connector.getInstance();
        String query = "insert into " + Constants.schema +
                ".users(first_name, last_name, nickname, sex, age, mail, password) values (?,?,?,?,?,?,?);";
        PreparedStatement statement = connector.getStatement(query);
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, nickname);
        statement.setString(4, sex);
        statement.setInt(5, age);
        statement.setString(6, mail);
        statement.setString(7, password);
        connector.execute(statement);
    }

    public User searchUserByNickname(String nickname) throws SQLException {
        Connector connector = Connector.getInstance();
        PreparedStatement statement = connector.getStatement("select * from " + Constants.schema +
                ".users where nickname = ?");
        statement.setString(1, nickname);
        ResultSet set = connector.executeQuery(statement);
        if (!set.next()) return null;

        int user_id = set.getInt("user_id");
        return new User(user_id, set.getString("first_name"), set.getString("last_name"),
                set.getString("nickname"), set.getString("sex"), set.getInt("age"),
                set.getString("mail"), set.getString("password"), getRank(user_id), drinkData.userDrinks(user_id), drinkData.favourites(user_id));
    }

    public User searchUserById(int user_id) throws SQLException {
        Connector connector = Connector.getInstance();
        PreparedStatement statement = connector.getStatement("select * from " + Constants.schema +
                ".users where user_id = ?");
        statement.setInt(1, user_id);
        ResultSet set = connector.executeQuery(statement);
        set.next();
        return new User(set.getInt(1), set.getString("first_name"), set.getString("last_name"),
                set.getString("nickname"), set.getString("sex"), set.getInt("age"),
                set.getString("mail"), null, getRank(user_id), drinkData.userDrinks(user_id), drinkData.favourites(user_id));
    }

    public double getRank(int user_id) throws SQLException {
        Connector connector = Connector.getInstance();
        PreparedStatement statement = connector.getStatement("select sum(" + Constants.schema + ".ranking.rank_score)/count(*) " +
                "from " + Constants.schema + ".ranking " +
                "where " + Constants.schema + ".ranking.drink_id in " +
                "(select " + Constants.schema + ".drinks.drink_id from " + Constants.schema + ".drinks " +
                "where " + Constants.schema + ".drinks.author = ?)");
        statement.setInt(1, user_id);
        ResultSet set = connector.executeQuery(statement);
        set.next();
        Double rank = set.getDouble(1);
        return (rank == null) ? 0 : rank;
    }

    public User searchUserByMail(String mail) throws SQLException {
        Connector connector = Connector.getInstance();
        PreparedStatement statement = connector.getStatement("select * from " + Constants.schema +
                ".users where mail = ?");
        statement.setString(1, mail);
        ResultSet set = connector.executeQuery(statement);
        if (!set.next()) return null;

        int user_id = set.getInt("user_id");
        return new User(user_id, set.getString("first_name"), set.getString("last_name"),
                set.getString("nickname"), set.getString("sex"), set.getInt("age"),
                set.getString("mail"), set.getString("password"), getRank(user_id), drinkData.userDrinks(user_id), drinkData.favourites(user_id));
    }
}

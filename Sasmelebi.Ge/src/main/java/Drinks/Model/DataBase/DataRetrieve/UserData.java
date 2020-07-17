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
        Double sum = (double) getRankHelper(user_id, "sum(rank_score)");
        Double quantity = (double) getRankHelper(user_id, "count(*)");
        return (quantity == 0) ? 0 : sum / quantity;
    }

    private int getRankHelper(int user_id, String select) throws SQLException {
        Connector connector = Connector.getInstance();
        PreparedStatement statement = connector.getStatement("select " + select + " from " + Constants.schema +
                ".ranking where user_id = ?");
        statement.setInt(1, user_id);
        ResultSet set = connector.executeQuery(statement);
        set.next();
        return set.getInt(1);
    }

}

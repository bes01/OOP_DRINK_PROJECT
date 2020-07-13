package Drinks.Model.DataBase.DataRetrieve;

import Drinks.Constants.Constants;
import Drinks.Model.Containers.User;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserData {
    public static User searchUserById(int user_id) throws SQLException {
        Connector connector = Connector.getInstance();
        PreparedStatement statement = connector.getStatement("select * from " + Constants.schema +
                ".users where user_id = ?");
        statement.setInt(1, user_id);
        ResultSet set = connector.executeQuery(statement);
        set.next();
        return new User(set.getInt(1), set.getString("first_name"), set.getString("last_name"),
                set.getString("nickname"), set.getString("sex"), set.getInt("age"),
                set.getString("mail"), null, getRank(user_id), DrinkData.userDrinks(user_id), DrinkData.favourites(user_id));
    }

    public static double getRank(int user_id) throws SQLException {
        Double sum = (double) selectFromRank(user_id, "sum(rank_score)");
        Double quantity = (double) selectFromRank(user_id, "count(*)");
        return (quantity == 0) ? 0 : sum / quantity;
    }

    private static int selectFromRank(int user_id, String select) throws SQLException {
        Connector connector = Connector.getInstance();
        PreparedStatement statement = connector.getStatement("select " + select + " from " + Constants.schema +
                ".ranking where user_id = ?");
        statement.setInt(1, user_id);
        ResultSet set = connector.executeQuery(statement);
        set.next();
        return set.getInt(1);
    }
}

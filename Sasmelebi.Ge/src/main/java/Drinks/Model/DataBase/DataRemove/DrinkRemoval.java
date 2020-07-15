package Drinks.Model.DataBase.DataRemove;

import Drinks.Constants.Constants;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DrinkRemoval {
    private Connector connector;

    public DrinkRemoval() {
        connector = Connector.getInstance();
    }

    public void removeFromFavourites(int drink_id, int user_id) throws SQLException {
        PreparedStatement statement = connector.getStatement("delete from " + Constants.schema + ".favourites where " +
                "drink_id = ? and user_id = ?");
        statement.setInt(1, drink_id);
        statement.setInt(2, user_id);
        connector.execute(statement);
    }
}

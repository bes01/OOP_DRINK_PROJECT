package Drinks.Model.DataBase.DrinkDao;

import Drinks.Constants.Constants;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FavouritesData {

    Connector connector;

    public FavouritesData() throws SQLException {
        connector = Connector.getInstance();
        PreparedStatement st = connector.getStatement("USE " + Constants.schema);
        connector.execute(st);
    }

    public boolean isInFavourites(int drink_id, int user_id) throws SQLException {
        PreparedStatement st = connector.getStatement(FROM_FAVOURITES);
        st.setInt(1, user_id);
        st.setInt(2, drink_id);
        ResultSet res = connector.executeQuery(st);
        return res.next();
    }

    public boolean addInFavourites(int drink_id, int user_id) throws SQLException {
        if(isInFavourites(drink_id, user_id) ||
                new TheDrinkData().isInUserDrinks(drink_id, user_id)) return false;
        PreparedStatement st = connector.getStatement(INSERT_FAVOURITES);
        st.setInt(1, user_id);
        st.setInt(2, drink_id);
        connector.execute(st);
        return true;
    }

    private static final String FROM_FAVOURITES = "SELECT * FROM favourites WHERE user_id = ? AND " +
            "drink_id = ?";
    private static final String INSERT_FAVOURITES = "INSERT INTO favourites (user_id, drink_id) VALUES(?,?);";
}

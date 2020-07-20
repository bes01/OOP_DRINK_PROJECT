package Drinks.Model.DataBase.DataRetrieve;

import Drinks.Constants.Constants;
import Drinks.Model.Containers.Drink;
import Drinks.Model.DataBase.Connector;
import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DiscoverData {
    private Connector connector;
    private DrinkData drinkData;

    public DiscoverData() {
        connector = Connector.getInstance();
        drinkData = new DrinkData();
    }

    public ArrayList<Drink> recentlyAdded() throws SQLException {
        PreparedStatement statement = connector.getStatement("select * from " + Constants.schema + ".drinks " +
                "order by addition_time desc");
        return drinkData.getDrinksArray(statement, Constants.discoverPageItemPerRow);
    }

    public ArrayList<Drink> topDrinks() throws SQLException {
        PreparedStatement statement = connector.getStatement("select *,sum(" + Constants.schema + ".ranking.rank_score)/" +
                "(select count(*) from " + Constants.schema + ".ranking where " + Constants.schema +
                ".ranking.drink_id = " + Constants.schema + ".drinks.drink_id) ranks " +
                "from " + Constants.schema + ".ranking join " + Constants.schema + ".drinks " +
                "on " + Constants.schema + ".ranking.drink_id = " + Constants.schema + ".drinks.drink_id " +
                "group by " + Constants.schema + ".ranking.drink_id order by ranks desc");
        return drinkData.getDrinksArray(statement, Constants.discoverPageItemPerRow);
    }

    public ArrayList<Drink> randomDrinks() throws SQLException {
        PreparedStatement statement = connector.getStatement("select max(" + Constants.schema + ".drinks.drink_id) " +
                "from " + Constants.schema + ".drinks");
        ResultSet res = connector.executeQuery(statement);
        res.next();
        int maxId = res.getInt(1);
        Set<Integer> randomIds = new HashSet<>();
        Random random = new Random();
        String query = "select * from " + Constants.schema + ".drinks where "
                + Constants.schema + ".drinks.drink_id in (";
        while (randomIds.size() != Constants.discoverPageItemPerRow) {
            int randomId = random.nextInt(maxId) + 1;
            if (randomIds.add(new Integer(randomId)))
                query += randomId + ",";
        }
        query = query.substring(0, query.length() - 1) + ")";
        statement = connector.getStatement(query);
        return drinkData.getDrinksArray(statement, Constants.discoverPageItemPerRow);
    }
}

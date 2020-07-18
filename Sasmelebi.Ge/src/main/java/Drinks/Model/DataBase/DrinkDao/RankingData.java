package Drinks.Model.DataBase.DrinkDao;

import Drinks.Constants.Constants;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RankingData {

    Connector connector;

    public RankingData() throws SQLException {
        connector = Connector.getInstance();
        PreparedStatement st = connector.getStatement("USE " + Constants.schema);
        connector.execute(st);
    }

    public double sumRated(int drink_id) throws SQLException {
        PreparedStatement st = connector.getStatement(FROM_RANKING);
        st.setInt(1, drink_id);
        ResultSet res = connector.executeQuery(st);
        res.next();
        return res.getDouble("AVG");
    }

    public int userRated(int drink_id, int user_id) throws SQLException {
        PreparedStatement st = connector.getStatement(FROM_RANKING_USER);
        st.setInt(1, drink_id);
        st.setInt(2, user_id);
        ResultSet res = connector.executeQuery(st);
        res.next();
        return res.getInt("SUM");
    }

    public void justRated(int drink_id, int user_id, int score) throws SQLException {
        if(userRated(drink_id, user_id) == 0) insertRanking(drink_id, user_id, score);
        else updateRanking(drink_id, user_id, score);
    }

    private void insertRanking(int drink_id, int user_id, int score) throws SQLException {
        PreparedStatement st = connector.getStatement(INSERT_RANKING);
        st.setInt(1, user_id);
        st.setInt(2, drink_id);
        st.setInt(3, score);
        connector.execute(st);
    }

    private void updateRanking(int drink_id, int user_id, int score) throws SQLException {
        PreparedStatement st = connector.getStatement(UPDATE_RANKING);
        st.setInt(1, score);
        st.setInt(2, user_id);
        st.setInt(3, drink_id);
        connector.execute(st);
    }

    private static final String FROM_RANKING = "SELECT SUM(rank_score)/COUNT(rank_score) as AVG FROM ranking WHERE drink_id = ?";
    private static final String FROM_RANKING_USER = "SELECT SUM(rank_score) as SUM " +
            "FROM ranking WHERE drink_id = ? AND user_id = ?";
    private static final String INSERT_RANKING = "INSERT INTO ranking (user_id, drink_id, rank_score) VALUES(?,?,?);";
    private static final String UPDATE_RANKING = "UPDATE ranking SET " +
            " rank_score = ? WHERE user_id = ? AND drink_id = ?";
}

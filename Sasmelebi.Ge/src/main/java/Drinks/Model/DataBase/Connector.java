package Drinks.Model.DataBase;

import Drinks.Constants.Constants;

import java.sql.*;

public class Connector {
    private Connection connection;
    private static Connector connector = null;

    public Connector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    Constants.dataUser,
                    Constants.dataPassword);
        } catch (Exception e) {
            throw new RuntimeException("Cant connect to the database.");
        }
    }

    public static Connector getInstance() {
        if (connector == null) {
            synchronized (Connector.class) {
                if (connector == null)
                    connector = new Connector();
            }
        }
        return connector;
    }

    public PreparedStatement getStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    public ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public void execute(PreparedStatement statement) throws SQLException {
        statement.execute();
    }
}

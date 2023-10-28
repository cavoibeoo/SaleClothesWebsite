package sql;

import java.sql.*;

public class index {
    public static void main(String[] args) {

        try {
            String dbURL = "jdbc:mysql://localhost:3306/itproject";
            String username = "root";
            String password = "123456789";
            Connection connection = DriverManager.getConnection(
                    dbURL, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstName"));
            }

        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

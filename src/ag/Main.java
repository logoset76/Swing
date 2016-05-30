package ag;

import java.sql.*;

public class Main {


    private static Connection connection;
    private static Statement statement;


    public static void main(String[] args) {

        try {
            Class.forName("orq.sqlite.jdbc");
            connection = DriverManager.getConnection("jdbc:sqlite:DB.db");
            statement = connection.createStatement();
            test();
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("Connection error");
        }

        MWindow mWindow = new MWindow();


    }
    public static void test(){

        String s = "SELECT * FROM Main";
        try {
            ResultSet resultSet = statement.executeQuery(s);
            while (resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

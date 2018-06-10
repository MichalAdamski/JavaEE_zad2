package michal;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library?characterEncoding=utf8&useSSL=false";
        String username = "root";
        String password = "admin";
        try {
            Connection connection = (Connection) DriverManager.getConnection(url,username,password);
            Statement statement = (Statement) connection.createStatement();
            String query = "select * from books";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String author = resultSet.getString(3);
                System.out.println(id + " " + author + " " + title + " ");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

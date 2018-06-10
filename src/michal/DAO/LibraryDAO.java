package michal.DAO;

import com.mysql.jdbc.Connection;
import michal.model.Library;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class LibraryDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/library?characterEncoding=utf8&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    Connection connection;

    public LibraryDAO() {
        try {
            connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Nie można nawiązać połączenia");
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract Library create(Library l);
    public abstract List<Library> read();
    public abstract void update(Library l);
    public abstract void delete(Library l);
}
package michal.DAO;

import com.mysql.jdbc.PreparedStatement;
import michal.model.Book;
import michal.model.Library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends LibraryDAO {

    @Override
    public Library create(Library l) {
        if (l instanceof Book) {
            final String sql = "insert into books(title,author,year,isbn) values(?,?,?,?)";
            try {
                PreparedStatement prepStmt = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prepStmt.setString(1, ((Book) l).getTitle());
                prepStmt.setString(2, ((Book) l).getAuthor());
                prepStmt.setInt(3, ((Book) l).getYear());
                prepStmt.setInt(4, ((Book) l).getIsbn());
                prepStmt.executeUpdate();


                ResultSet result = prepStmt.getGeneratedKeys();
                if (result.next()) {
                    l.setId(result.getLong(1));
                }
                return l;
            } catch (SQLException e) {
                System.out.println("Nie można utworzyć książki");
            }
        } else {
            System.out.println("Obiekt nie jest książką");
        }
        return null;
    }

    @Override
    public List<Library> read() {
        final String sql = "select * from books";
        try {
            PreparedStatement prepStmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet result = prepStmt.executeQuery();
            List<Library> books = new ArrayList<>();

            while (result.next()) {
                Book book = new Book(result.getString(2), result.getString(3), result.getInt(4), result.getInt(5));
                book.setId(result.getLong(1));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            System.out.println("Nie można wczytać bazy książek");
        }
        return null;
    }

    @Override
    public void update(Library l) {
        final String sql = "update books set title=?, author=?, year=?, isbn=? where id=?";
        if (l instanceof Book) {
            try {
                PreparedStatement prepStmt = (PreparedStatement) connection.prepareStatement(sql);
                prepStmt.setString(1, ((Book) l).getTitle());
                prepStmt.setString(2, ((Book) l).getAuthor());
                prepStmt.setInt(3, ((Book) l).getYear());
                prepStmt.setInt(4, ((Book) l).getIsbn());
                prepStmt.setLong(5, (l.getId()));
                prepStmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Nie można zaktualizować książki");
            }
        } else {
            System.out.println("Obiekt nie jest książką");
        }
    }

    @Override
    public void delete(Library l) {
        final String sql = "delete from books where id = ?";
        if (l instanceof Book) {
            try {
                PreparedStatement prepStmt = (PreparedStatement) connection.prepareStatement(sql);
                prepStmt.setLong(1, l.getId());
                prepStmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Nie można usunąć książki");
            }
        } else {
            System.out.println("Obiekt nie jest książką");
        }
    }
}

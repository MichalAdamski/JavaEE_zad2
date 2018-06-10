import michal.DAO.BookDAO;
import michal.Messages;
import michal.model.Book;
import michal.model.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainWindow {
    List<Book> books = new ArrayList<>();


    public void read() {
        BookDAO dao = new BookDAO();
        List<Library> lib = dao.read();
        for (Library l : lib) {
            if (l instanceof Book) {
                books.add((Book) l);
            }
        }
        dao.close();
    }

    public Book search(long id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public void add(Scanner input) {
        System.out.println(Messages.BOOK_TITLE);
        String title = input.nextLine();

        System.out.println(Messages.BOOK_AUTHOR);
        String author = input.nextLine();

        System.out.println(Messages.BOOK_YEAR);
        int year = input.nextInt();
        input.nextLine();
        System.out.println(Messages.BOOK_ISBN);
        int isbn = input.nextInt();
        input.nextLine();

        if (year > 1900 && year < 2018) {
            Book book = new Book(title, author, year, isbn);

            BookDAO dao = new BookDAO();
            book = (Book)dao.create(book);
            dao.close();

            books.add(book);
            System.out.println(Messages.BOOK_ADDED);
        } else {
            System.out.println(Messages.BOOK_INVALID_YEAR);
        }
    }

    public void printBooks() {
        System.out.println("\n////////////////////////////////////////////////\n");
        for (Book b : books) {
            System.out.println(b.toString());
        }
        System.out.println("\n////////////////////////////////////////////////");
    }

    public void delete(Scanner input) {
        System.out.println(Messages.BOOK_ID);
        long id = input.nextLong();
        input.nextLine();

        Book b = search(id);

        if (b != null) {
            BookDAO dao = new BookDAO();
            dao.delete(b);
            books.remove(b);
            dao.close();
            System.out.println(Messages.BOOK_DELETED);
        } else {
            System.out.println(Messages.BOOK_NOT_EXISTS);
        }
    }

    public void update(Scanner input) {
        System.out.println(Messages.BOOK_ID);
        long id = input.nextLong();
        input.nextLine();

        System.out.println(Messages.BOOK_TITLE);
        String title = input.nextLine();

        System.out.println(Messages.BOOK_AUTHOR);
        String author = input.nextLine();

        System.out.println(Messages.BOOK_YEAR);
        int year = input.nextInt();
        input.nextLine();

        System.out.println(Messages.BOOK_ISBN);
        int isbn = input.nextInt();
        input.nextLine();

        if (year > 1900 && year < 2018) {
            Book b = search(id);
            if (b != null) {
                b.setAuthor(author);
                b.setIsbn(isbn);
                b.setTitle(title);
                b.setYear(year);

                BookDAO dao = new BookDAO();
                dao.update(b);
                dao.close();
                System.out.println(Messages.BOOK_UPDATED);
            } else {
                System.out.println(Messages.BOOK_NOT_EXISTS);
            }
        } else {
            System.out.println(Messages.BOOK_INVALID_YEAR);
        }

    }

    public void start() {
        Scanner input = new Scanner(System.in);
        program:
        while (true) {
            System.out.println(Messages.MAIN_MESSAGE);
            String option = input.nextLine();

            switch (option) {
                case "1":
                    add(input);
                    break;
                case "2":
                    printBooks();
                    break;
                case "3":
                    delete(input);
                    break;
                case "4":
                    update(input);
                    break;
                case "q":
                    break program;
                    default:
                        System.out.println(Messages.INVALID_OPTION);
            }
        }
        input.close();
    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
        main.read();
        main.start();
    }
}

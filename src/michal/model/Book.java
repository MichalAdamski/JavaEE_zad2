package michal.model;

public class Book extends Library {
    private String title;
    private String author;
    private int year;
    private int isbn;

    public Book(String title, String author, int year, int isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", isbn=" + isbn +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Book) {
            if (id == ((Book) o).id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAdded() {
        return id == 0;
    }
}

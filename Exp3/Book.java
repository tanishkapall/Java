public class Book 
{
    public String title;
    public String author;
    public double price;
    public String genre;
    public String ISBN;

    public Book() {
        // Default constructor
        title = "Unknown";
        author = "Unknown";
        price = 0.0;
        genre = "Uncategorized";
        ISBN = "000-0000000000";
    }

    public Book(String t, String a, double p, String g, String isbn) throws InvalidPriceException, TitleExp {
        if (t == null || t.trim().isEmpty()) {
        throw new TitleExp("Title cannot be empty");
    }
        title = t;
        author = a;
        if (p<0)
            throw new InvalidPriceException("Price cannot be negative");
        price = p;
        genre = g;
        ISBN = isbn;
    }
    
    public Book(Book b) {
        title = b.title;
        author = b.author;
        price = b.price;
        genre = b.genre;
        ISBN = b.ISBN;
    }
}

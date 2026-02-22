import java.util.ArrayList;
public class ArrayListOfBooks 
{
    public static void main(String[] args) 
    {
        Book b1 = new Book();
        Book b4 = new Book(b1);
        b4.title = "1984";
        b4.price = 7.99;
        b4.genre = "Fiction";
        ArrayList<Book> library = new ArrayList<Book>();
        
        try {
            Book b2 = new Book("To Kill a Mockingbird", "Harper Lee", 7.99, "Classic", "978-0060935467");
            Book b3 = new Book(b2);
            b3.title = "Harry Potter";
            b3.price= 444.44;
            
            library.add(b1);
            library.add(b2);
            library.add(b3);
            library.add(b4);
            Book b6 = new Book("", "Author", 10.99, "Classic", "123");
            library.add(b6);
        } catch (TitleExp | InvalidPriceException e) {
            System.out.println(e.getMessage());
        }

        try {
            Book b5 = new Book("The Great Gatsby", "Fitzgerald", -10.99, "Fiction", "978-0743273565");
            library.add(b5);
        } catch (TitleExp | InvalidPriceException e) {
            System.out.println(e.getMessage());
        }

        double sum = 0;
        for (Book b : library) {
            sum += b.price;
        }
        double average = sum / library.size();
        System.out.println("Average price of books: " + average);

        library.forEach(b -> {
        if (b.genre.equals("Fiction")) {
            System.out.println("Fiction Book: " + b.title);
        }
    });
        for (Book b : library) {
            System.out.println("Title: " + b.title + ", Author: " + b.author + ", Price: $" + b.price + ", Genre: " + b.genre + ", ISBN: " + b.ISBN);
            System.out.println("-----------------------------");
        }
        library.forEach(b -> {
            if (b.genre.equals("Uncategorized")) {
                System.out.println(b.title + " is uncategorized.");
            }
        }
        ); 
    
} 
}

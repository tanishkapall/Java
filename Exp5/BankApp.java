// ─────────────────────────────────────────────
// Main Class: BankApp
// ─────────────────────────────────────────────
public class BankApp {
    public static void main(String[] args) {
 
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();
 
        // Create customers
        Customer c1 = new Customer(1, "Tanishka");
        Customer c2 = new Customer(2, "Anuj");
 
        // Create accounts
        Account a1 = new SavingsAccount(101, 5000, 4.5, 1000);
        Account a2 = new LoanAccount(201, 20000);
        Account a3 = new SavingsAccount(102, 8000, 5.0, 1500);
 
        // Add to accounts list
        accounts.add(a1);
        accounts.add(a2);
        accounts.add(a3);
 
        // Link accounts to customers
        c1.addAccount(a1);
        c1.addAccount(a2);
        c2.addAccount(a3);
 
        // Add customers to list
        customers.add(c1);
        customers.add(c2);
 
        // Display consolidated info
        for (Customer c : customers) {
            c.viewAccounts();
            System.out.println("---------------------");
        }
    }
}

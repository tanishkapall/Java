public class PayrollSystem {
    public static void main(String[] args) {

        // Full-time SWE
        Employee e1 = new FullTimeEmployee(
                "Tanishka", "ABCDE1234F", "01-01-2023",
                "Software Engineer", 101,
                500000, 50000, "SWE"
        );

        // Contract Employee
        Employee e2 = new ContractEmployee(
                "Rahul", "PQRSX5678Z", "15-03-2024",
                "Consultant", 102,
                120, 500
        );

        // Manager
        Employee e3 = new Manager(
                "Anita", "LMNOP9876Q", "10-07-2022",
                "Manager", 103,
                800000, 100000,
                "Manager", 50000, 30000
        );

        // Display + CTC
        Employee[] employees = {e1, e2, e3};

        for (Employee emp : employees) {
            emp.displayDetails();
            System.out.println("CTC: " + emp.calcCTC());
        }
    }
}
// Base abstract class
abstract class Employee {
    protected String name;
    protected String panNo;
    protected String joiningDate;
    protected String designation;
    protected int empId;

    public Employee(String name, String panNo, String joiningDate, String designation, int empId) {
        this.name = name;
        this.panNo = panNo;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.empId = empId;
    }

    // Abstract method
    abstract double calcCTC();

    public void displayDetails() {
        System.out.println("\nEmployee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Designation: " + designation);
        System.out.println("PAN: " + panNo);
        System.out.println("Joining Date: " + joiningDate);
    }
}

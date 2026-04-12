import java.io.File;

public class MainApp {
    public static void main(String[] args) {

        File file = new File("C:\\invalid_folder\\students.csv");

        if (!file.exists()) {
            FileHandler.writeHeader();
        }

        // Initial rows
        FileHandler.addStudent(new Student(1, "Aman", "CSE", 70, 75, 80, 0, 0));
        FileHandler.addStudent(new Student(2, "Riya", "IT", 60, 65, 70, 0, 0));

        // Add 3 more rows (marks4 & marks5 = 0)
        FileHandler.addStudent(new Student(3, "Karan", "ECE", 50, 55, 60, 0, 0));
        FileHandler.addStudent(new Student(4, "Neha", "ME", 80, 85, 90, 0, 0));
        FileHandler.addStudent(new Student(5, "Simran", "CSE", 75, 70, 65, 0, 0));

        System.out.println("\n--- After Creation ---");
        StudentService.display();

        // UPDATE
        StudentService.updateMarksAndPercentage();
        System.out.println("\n--- After Update ---");
        StudentService.display();

        // DELETE
        StudentService.deleteStudent(3);
        System.out.println("\n--- After Deletion ---");
        StudentService.display();

        // Exception Demo
        try {
            java.io.FileReader fr = new java.io.FileReader("wrongfile.csv");
        } catch (Exception e) {
            System.out.println("\nException Example: " + e.getMessage());
        }
    }
}
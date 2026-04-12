import java.io.*;
import java.util.*;

public class FileHandler {

    static String fileName = "C:\\invalid_folder\\students.csv";

    public static void writeHeader() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage");
            bw.newLine();
            System.out.println("Header added");
        } catch (IOException e) {
            System.out.println("Error writing header: " + e.getMessage());
        }
    }

    public static void addStudent(Student s) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(s.toCSV());
            bw.newLine();
            System.out.println("Added: " + s.name);
        } catch (IOException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public static List<String> readAll() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public static void writeAll(List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
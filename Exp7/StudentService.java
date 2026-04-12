import java.util.*;

public class StudentService {

    public static void updateMarksAndPercentage() {
        List<String> data = FileHandler.readAll();
        List<String> updated = new ArrayList<>();

        updated.add(data.get(0)); // header

        for (int i = 1; i < data.size(); i++) {
            String[] parts = data.get(i).split(",");

            int m1 = Integer.parseInt(parts[3]);
            int m2 = Integer.parseInt(parts[4]);
            int m3 = Integer.parseInt(parts[5]);

            // updating marks4 & marks5
            int m4 = 80;
            int m5 = 85;

            double percentage = (m1 + m2 + m3 + m4 + m5) / 5.0;

            String updatedRow = parts[0] + "," + parts[1] + "," + parts[2] + "," +
                                m1 + "," + m2 + "," + m3 + "," +
                                m4 + "," + m5 + "," + percentage;

            updated.add(updatedRow);
        }

        FileHandler.writeAll(updated);
        System.out.println("Updated marks and percentage");
    }

    public static void deleteStudent(int id) {
        List<String> data = FileHandler.readAll();
        List<String> updated = new ArrayList<>();

        updated.add(data.get(0)); // header

        for (int i = 1; i < data.size(); i++) {
            String[] parts = data.get(i).split(",");
            if (Integer.parseInt(parts[0]) != id) {
                updated.add(data.get(i));
            }
        }

        FileHandler.writeAll(updated);
        System.out.println("Deleted student with ID: " + id);
    }

    public static void display() {
        List<String> data = FileHandler.readAll();
        for (String line : data) {
            System.out.println(line);
        }
    }
}
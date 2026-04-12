public class Student {
    int studentId;
    String name;
    String branch;
    int m1, m2, m3, m4, m5;
    double percentage;

    public Student(int studentId, String name, String branch,
                   int m1, int m2, int m3, int m4, int m5) {
        this.studentId = studentId;
        this.name = name;
        this.branch = branch;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.m4 = m4;
        this.m5 = m5;
        this.percentage = 0;
    }

    public void calculatePercentage() {
        int total = m1 + m2 + m3 + m4 + m5;
        this.percentage = total / 5.0;
    }

    public String toCSV() {
        return studentId + "," + name + "," + branch + "," +
               m1 + "," + m2 + "," + m3 + "," +
               m4 + "," + m5 + "," + percentage;
    }
}

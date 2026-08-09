
import java.io.*;
import java.util.*;

public class StudentManagementSystem {

    ArrayList<Student> students = FileManager.load();

    void add(Student s) {
        students.add(s);
        FileManager.save(students);
        System.out.println("Student Added Successfully.");
    }

    void display() {
        if (students.isEmpty()) {
            System.out.println("No Students Found.");
            return;
        }
        for (Student s : students)
            System.out.println(s);
    }

    void update(int id, String name, double marks) {
        for (Student s : students)
            if (s.id == id) {
                s.name = name;
                s.marks = marks;
                FileManager.save(students);
                System.out.println("Student Updated.");
                return;
            }
        System.out.println("Student Not Found.");
    }

    void delete(int id) {
        for (Student s : students)
            if (s.id == id) {
                students.remove(s);
                FileManager.save(students);
                System.out.println("Student Deleted.");
                return;
            }
        System.out.println("Student Not Found.");
    }

    void search(int id) {
        for (Student s : students)
            if (s.id == id) {
                System.out.println(s);
                return;
            }
        System.out.println("Student Not Found.");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name : ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks : ");
                    double marks = sc.nextDouble();
                    sm.add(new Student(id, name, marks));
                    break;

                case 2:
                    sm.display();
                    break;

                case 3:
                    System.out.print("Enter Student ID : ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name : ");
                    name = sc.nextLine();
                    System.out.print("Enter New Marks : ");
                    marks = sc.nextDouble();
                    sm.update(id, name, marks);
                    break;

                case 4:
                    System.out.print("Enter Student ID : ");
                    sm.delete(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter Student ID : ");
                    sm.search(sc.nextInt());
                    break;

                case 6:
                    System.out.println("Thank You For Using My Student Management!");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }
        } while (choice != 6);

        sc.close();
    }
}

class Student implements Serializable {

    int id;
    String name;
    double marks;

    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return "ID : " + id +
                "\nName : " + name +
                "\nMarks : " + marks +
                "\n-------------------------";
    }
}

class FileManager {

    static final String FILE_NAME = "students.dat";

    static void save(ArrayList<Student> students) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(students);
            out.close();
        } catch (Exception e) {
            System.out.println("Error Saving File");
        }
    }

    static ArrayList<Student> load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            ArrayList<Student> students = (ArrayList<Student>) in.readObject();
            in.close();
            return students;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}

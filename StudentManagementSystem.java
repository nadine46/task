import java.util.*;
import java.util.stream.Collectors;

public class StudentManagementSystem {
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    filterPassingStudents();
                    break;
                case 5:
                    filterFailingStudents();
                    break;
                case 6:
                    searchStudentsByName();
                    break;
                case 7:
                    sortStudentsByName();
                    break;
                case 8:
                    sortStudentsByAvera
                    break;
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                    System.out.println("This feature is not implemented yet.");
                    break;
                case 0:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Please enter a number between 0 and 18.");
            }
            if (choice != 0) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("\n==========================================");
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("1. Register New Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Filter Passing Students (Avg >= 65)");
        System.out.println("5. Filter Failing Students (Avg < 65)");
        System.out.println("6. Search Students by Name");
        System.out.println("7. Sort Students by Name");
        System.out.println("8. Sort Students by Average Marks");
        System.out.println("9. Display All Courses (not implemented yet)");
        System.out.println("10. Display Unique Courses (not implemented yet)");
        System.out.println("11. Search Students by Course (not implemented yet)");
        System.out.println("12. Display Unique Skills (not implemented yet)");
        System.out.println("13. Group Students by Course (not implemented yet)");
        System.out.println("14. View Student Performance Statistics (not implemented yet)");
        System.out.println("15. View Best Performing Student (not implemented yet)");
        System.out.println("16. View Top 3 Students (not implemented yet)");
        System.out.println("17. Apply Bonus Marks (not implemented yet)");
        System.out.println("18. View Final Student Ranking (not implemented yet)");
        System.out.println("0. Exit");
        System.out.println("==========================================");
    }

    // Task 1: Student Registration and Management
    private static void registerStudent() {
        System.out.println("\n--- Register New Student ---");
        int id = getPositiveIntInput("Enter Student ID: ");

        // Check for duplicate ID using anyMatch() fuction
        boolean idExists = students.stream().anyMatch(s -> s.getId() == id);
        if (idExists) {
            System.out.println("Error: A student with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Student name cannot be blank.");
            System.out.print("Enter Student Name: ");
            name = scanner.nextLine().trim();
        }

        int age = getPositiveIntInput("Enter Age: ");

        Map<String, Double> courses = new HashMap<>();
        int numCourses = getPositiveIntInput("Enter Number of Courses: ");
        for (int i = 0; i < numCourses; i++) {
            System.out.print("Enter Course " + (i + 1) + " Name: ");
            String courseName = scanner.nextLine().trim();
            while (courseName.isEmpty() || courses.containsKey(courseName)) {
                System.out.println(courseName.isEmpty()
                        ? "Course name cannot be blank."
                        : "That course has already been entered for this student.");
                System.out.print("Enter Course " + (i + 1) + " Name: ");
                courseName = scanner.nextLine().trim();
            }
            double mark = getDoubleInput("Enter Marks for " + courseName + " (0-100): ");
            while (mark < 0 || mark > 100) {
                System.out.println("Marks must be between 0 and 100.");
                mark = getDoubleInput("Enter Marks for " + courseName + " (0-100): ");
            }
            courses.put(courseName, mark);
        }

        List<String> skills = new ArrayList<>();
        System.out.print("Enter skills separated by commas (e.g., Java, SQL, Git): ");
        String skillsInput = scanner.nextLine();
        if (!skillsInput.isEmpty()) {
            for (String skill : skillsInput.split(",")) {
                if (!skill.trim().isEmpty()) {
                    skills.add(skill.trim());
                }
            }
        }

        Student newStudent = new Student(id, name, age, courses, skills);
        students.add(newStudent);
        System.out.println("Student registered successfully!");
    }

    private static void displayAllStudents() {
        System.out.println("All Registered Students");
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        students.stream().forEach(System.out::println);
    }

    private static void searchStudentById() {
        System.out.println("Search Student by ID");
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        int id = getIntInput("Enter Student ID to search: ");
        Optional<Student> student = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        if (student.isPresent()) {
            System.out.println("Found: " + student.get());
        } else {
            System.out.println("No student found with ID " + id);
        }
    }

    // Task 2: Search, Filter, and Sort Students
    private static void filterPassingStudents() {
        System.out.println("Passing Students (Average >= 65)");
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        List<Student> passing = students.stream()
                .filter(s -> s.getAverageMarks() >= 65)
                .collect(Collectors.toList());

        if (passing.isEmpty()) {
            System.out.println("No passing students found.");
        } else {
            passing.forEach(System.out::println);
        }
    }

    private static void filterFailingStudents() {
        System.out.println("Failing Students (Average < 65) ");
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        List<Student> failing = students.stream()
                .filter(s -> s.getAverageMarks() < 65)
                .collect(Collectors.toList());

        if (failing.isEmpty()) {
            System.out.println("No failing students found.");
        } else {
            failing.forEach(System.out::println);
        }
    }

    private static void searchStudentsByName() {
        System.out.println("Search Students by Name");
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        System.out.print("Enter name or keyword to search: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Student> matched = students.stream()
                .filter(s -> s.getStudentName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        if (matched.isEmpty()) {
            System.out.println("No students matched the name: " + keyword);
        } else {
            matched.forEach(System.out::println);
        }
    }

    private static void sortStudentsByName() {
        System.out.println("\n--- Students Sorted by Name (Alphabetical) ---");
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        students.stream()
                .sorted(Comparator.comparing(Student::getStudentName, String.CASE_INSENSITIVE_ORDER))
                .forEach(System.out::println);
    }

    private static void sortStudentsByAverage() {
        System.out.println("\n--- Students Sorted by Average Marks (Highest to Lowest) ---");
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverageMarks).reversed())
                .forEach(System.out::println);
    }

    // Utility Helpers for Input Validations
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next();
            System.out.print(prompt);
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private static int getPositiveIntInput(String prompt) {
        int value = getIntInput(prompt);
        while (value <= 0) {
            System.out.println("Please enter a number greater than zero.");
            value = getIntInput(prompt);
        }
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
            System.out.print(prompt);
        }
        double val = scanner.nextDouble();
        scanner.nextLine(); 
        return val;
    }
}
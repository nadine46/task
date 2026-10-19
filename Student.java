import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private int id;
    private String studentName;
    private int age;
    private Map<String, Double> courses;
    private List<String> skills;

    public Student(int id, String studentName, int age, Map<String, Double> courses, List<String> skills) {
        this.id = id;
        this.studentName = studentName;
        this.age = age;
        this.courses = courses != null ? courses : new HashMap<>();
        this.skills = skills != null ? skills : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Double> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Double> courses) {
        this.courses = courses != null ? courses : new HashMap<>();
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills != null ? skills : new ArrayList<>();
    }

    public double getAverageMarks() {
        if (courses == null || courses.isEmpty()) {
            return 0.0;
        }
        return courses.values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return String.format("Student [ID, Name=%s, Age=%d, Average=%.2f, Courses=%s, Skills=%s]",
                id, studentName, age, getAverageMarks(), courses, skills);
    }
}

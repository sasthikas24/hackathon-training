
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentServiceVague {

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student must not be null");
        }

        if (findStudentById(student.getId()).isPresent()) {
            throw new IllegalArgumentException(
                    "A student with ID " + student.getId() + " already exists");
        }

        students.add(student);
    }

    public Optional<Student> findStudentById(int id) {
        validateId(id);

        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    public List<Student> getAllStudents() {
        return List.copyOf(students);
    }

    public boolean removeStudentById(int id) {
        validateId(id);
        return students.removeIf(student -> student.getId() == id);
    }

    private static void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be positive");
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Stores and manages students in memory.
 */
public class StudentService {

    private final List<Student> students = new ArrayList<>();

    /**
     * Adds a student, rejecting null values and IDs already in use.
     *
     * @throws IllegalArgumentException if student is null or its ID exists
     */
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

    /**
     * Finds a student by ID.
     *
     * @throws IllegalArgumentException if id is not positive
     */
    public Optional<Student> findStudentById(int id) {
        validateId(id);
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    /**
     * Returns an immutable snapshot of all stored students.
     */
    public List<Student> getAllStudents() {
        return List.copyOf(students);
    }

    /**
     * Removes a student by ID.
     *
     * @return true if a student was removed; false if the ID was not found
     * @throws IllegalArgumentException if id is not positive
     */
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
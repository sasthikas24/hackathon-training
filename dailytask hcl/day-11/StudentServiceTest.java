
public class StudentServiceTest {

    public static void main(String[] args) {
        StudentService service = new StudentService();

        Student first = new Student(1, "Anu", "anu@example.com");
        service.addStudent(first);

        // Test 1: Find existing student
        if (service.findStudentById(1).isEmpty()) {
            throw new AssertionError("Student should be found");
        }
        System.out.println("PASS: Student found");

        // Test 2: Reject duplicate IDs
        try {
            service.addStudent(
                    new Student(1, "Another Student", "other@example.com"));
            throw new AssertionError("Duplicate ID should be rejected");
        } catch (IllegalArgumentException expected) {
            System.out.println("PASS: Duplicate ID rejected");
        }

        // Test 3: Reject null students
        try {
            service.addStudent(null);
            throw new AssertionError("Null student should be rejected");
        } catch (IllegalArgumentException expected) {
            System.out.println("PASS: Null student rejected");
        }

        // Test 4: Returned list must be unmodifiable
        try {
            service.getAllStudents().clear();
            throw new AssertionError("Returned list should be unmodifiable");
        } catch (UnsupportedOperationException expected) {
            System.out.println("PASS: Returned list is unmodifiable");
        }

        // Test 5: Internal list must remain unchanged
        if (service.getAllStudents().size() != 1) {
            throw new AssertionError("Internal list was modified");
        }
        System.out.println("PASS: Internal list remains unchanged");

        // Test 6: Remove existing student
        if (!service.removeStudentById(1)) {
            throw new AssertionError("Student should be removed");
        }
        System.out.println("PASS: Student removed");

        // Test 7: Removed student should not be found
        if (service.findStudentById(1).isPresent()) {
            throw new AssertionError("Removed student should not be found");
        }
        System.out.println("PASS: Removed student not found");

        System.out.println("All tests passed!");
    }
}

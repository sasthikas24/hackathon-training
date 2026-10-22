
public class PostRepositoryTest {

    public static void main(String[] args) {
        PostRepository repository = new PostRepository();

        Post first = new Post(1, "Java Basics", 101, 10);
        repository.save(first);

        // Test 1: Find an existing post
        if (repository.findById(1).isEmpty()) {
            throw new AssertionError("Post should be found");
        }
        System.out.println("PASS: Post found");

        // Test 2: Reject null
        try {
            repository.save(null);
            throw new AssertionError("Null post should be rejected");
        } catch (IllegalArgumentException expected) {
            System.out.println("PASS: Null post rejected");
        }

        // Test 3: Reject duplicate IDs
        try {
            repository.save(new Post(1, "Another Post", 102, 0));
            throw new AssertionError("Duplicate ID should be rejected");
        } catch (IllegalArgumentException expected) {
            System.out.println("PASS: Duplicate ID rejected");
        }

        // Test 4: Missing ID returns an empty Optional
        if (repository.findById(99).isPresent()) {
            throw new AssertionError("Missing post should not be found");
        }
        System.out.println("PASS: Missing post handled");

        // Test 5: Returned list is unmodifiable
        try {
            repository.findAll().clear();
            throw new AssertionError("Returned list should be unmodifiable");
        } catch (UnsupportedOperationException expected) {
            System.out.println("PASS: Returned list is unmodifiable");
        }

        // Test 6: Repository data remains unchanged
        if (repository.findAll().size() != 1) {
            throw new AssertionError("Repository data was modified");
        }
        System.out.println("PASS: Repository data remains unchanged");

        // Test 7: Reject invalid IDs
        try {
            repository.findById(0);
            throw new AssertionError("Invalid ID should be rejected");
        } catch (IllegalArgumentException expected) {
            System.out.println("PASS: Invalid ID rejected");
        }

        // Test 8: Delete an existing post
        if (!repository.deleteById(1)) {
            throw new AssertionError("Post should be deleted");
        }
        System.out.println("PASS: Post deleted");

        // Test 9: Deleting a missing post returns false
        if (repository.deleteById(1)) {
            throw new AssertionError("Missing post should not be deleted");
        }
        System.out.println("PASS: Missing post deletion handled");

        System.out.println("All repository tests passed!");
    }
}

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory repository for posts, indexed by their IDs.
 *
 * <p>This repository is not persistent or thread-safe. Post iteration order
 * is unspecified.</p>
 */
public class PostRepository {

    private final Map<Integer, Post> postsById = new HashMap<>();

    /**
     * Saves a post if its ID is not already present.
     *
     * @throws IllegalArgumentException if post is null or its ID already exists
     */
    public void save(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("post must not be null");
        }

        int id = post.getId();
        if (postsById.putIfAbsent(id, post) != null) {
            throw new IllegalArgumentException("A post with ID " + id + " already exists");
        }
    }

    /**
     * Finds a post by ID.
     *
     * @throws IllegalArgumentException if id is not positive
     */
    public Optional<Post> findById(int id) {
        validateId(id);
        return Optional.ofNullable(postsById.get(id));
    }

    /**
     * Returns an immutable snapshot of all posts.
     */
    public List<Post> findAll() {
        return List.copyOf(postsById.values());
    }

    /**
     * Deletes the post with the given ID.
     *
     * @return true if a post was deleted; false if no post had that ID
     * @throws IllegalArgumentException if id is not positive
     */
    public boolean deleteById(int id) {
        validateId(id);
        return postsById.remove(id) != null;
    }

    private static void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be positive");
        }
    }
}
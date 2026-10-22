/**
 * Immutable blog post data whose identity is defined by its positive ID.
 */
public final class Post {

    private final int id;
    private final String title;
    private final int authorId;
    private final int viewCount;

    /**
     * Creates a post with validated identifiers, title, and view count.
     *
     * @throws IllegalArgumentException if either ID is not positive, the
     *                                  title is null or blank, or viewCount
     *                                  is negative
     */
    public Post(int id, String title, int authorId, int viewCount) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be positive");
        }
        if (authorId <= 0) {
            throw new IllegalArgumentException("authorId must be positive");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be null or blank");
        }
        if (viewCount < 0) {
            throw new IllegalArgumentException("viewCount must not be negative");
        }

        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.viewCount = viewCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getViewCount() {
        return viewCount;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Post post)) {
            return false;
        }
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    /**
     * Omits the title and author ID to avoid disclosing content or author data.
     */
    @Override
    public String toString() {
        return "Post{id=" + id + ", viewCount=" + viewCount + "}";
    }
}
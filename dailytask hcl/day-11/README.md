# Day 11: AI Integration — Core Java

## Objective
Practice using GitHub Copilot to generate Java boilerplate, review AI-generated code, and verify behavior through tests.

## Implementation
- `Student.java` — immutable Student POJO with validation.
- `StudentService.java` — in-memory student management service.
- `StudentServiceTest.java` — functional tests for student operations.
- `Post.java` — immutable Post entity with validation.
- `PostRepository.java` — in-memory repository using a Map keyed by post ID.
- `PostRepositoryTest.java` — functional tests for repository operations.
- `ReviewTable.md` — code-review findings and corrections.
- `AI-USAGE.md` — prompts, AI assistance, and verification notes.

## Compile
Run in PowerShell from this folder:

```powershell
javac -Xlint:all -Werror Student.java StudentService.java StudentServiceTest.java Post.java PostRepository.java PostRepositoryTest.java
```

## Run Tests
```powershell
java StudentServiceTest
java PostRepositoryTest
```

## Results
- Student service: all seven checks passed.
- Post repository: all nine checks passed.
- Compilation completed with warnings treated as errors.

## Design Notes
- Student and Post are immutable entities.
- StudentService uses an ArrayList.
- PostRepository uses a HashMap keyed by post ID.
- Both APIs protect their internal collections from external modification.
- The repositories are in-memory; data is not persisted after application termination.
- The implementations are not thread-safe.

## Remaining Work
- Complete the AI code-review drill with at least five genuine issues found and fixed.
- Implement CMS FR7 (bookmarks) and FR8 (author post-view statistics).

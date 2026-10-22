# Day 11 — AI Code Review Table

## Student POJO comparison

| Area | Basic/vague version | Improved version |
|---|---|---|
| Encapsulation | Public mutable fields in the illustrative basic version | Private final fields |
| Validation | No ID, name, or email validation in the illustrative basic version | Rejects invalid ID and null/blank text |
| Immutability | Fields can be changed externally | Final class with final fields |
| Equality | No `equals()` or `hashCode()` | Both use student ID consistently |
| Privacy | A generated `toString()` must be reviewed for sensitive data | Email is omitted from `toString()` |

**Note:** The basic POJO above was created as an illustrative comparison. These findings should not be described as defects from Copilot's output unless the actual generated version has the same behavior.

## StudentServiceVague review

| # | Finding in original generated service | Correction |
|---:|---|---|
| 1 | `addStudent()` accepted null | Reject null with `IllegalArgumentException` |
| 2 | Duplicate student IDs were allowed | Check for an existing ID before insertion |
| 3 | Lookup returned null for a missing student | Return `Optional<Student>` |
| 4 | Invalid IDs were not rejected | Validate that IDs are positive |
| 5 | Returned list was mutable | Return `List.copyOf(students)` for a read-only snapshot |

Finding 5 is an API-design improvement, not an internal-list exposure bug. The original `new ArrayList<>(students)` already returned a copy.

## Verification

- `StudentServiceTest`: 7 checks passed.
- `PostRepositoryTest`: 9 checks passed.
- Main classes compiled using `javac -Xlint:all -Werror`.
- Compile the corrected `StudentServiceVague.java` separately before marking it verified.

## Review checklist

- [x] Encapsulation and mutability reviewed
- [x] Input validation and null handling reviewed
- [x] Duplicate IDs reviewed
- [x] `equals()` and `hashCode()` reviewed
- [x] Returned collection behavior reviewed
- [x] Exception handling reviewed
- [x] Tests run for the student service and post repository

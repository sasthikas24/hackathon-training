# Day 11 — AI Usage Log

## Entry 1: Student POJO — Vague Prompt

**Prompt:** Create a basic Java Student POJO with an ID, name, and email. Include a constructor and getters. Keep it simple.

**Purpose:** Establish a minimal implementation for comparison.

**Review:** The basic illustrative version uses public mutable fields and lacks constructor validation, `equals()`, and `hashCode()`. These observations apply to the illustrative version, not necessarily to Copilot's original response.

## Entry 2: Student POJO — Specific Prompt

**Prompt:** Create an immutable Java Student class with private final fields, positive ID validation, non-null/non-blank name and email validation, getters, `equals()` and `hashCode()` based on ID, and a `toString()` that omits email.

**Review and changes:** Reviewed encapsulation, immutability, validation, equality, hashing, and privacy. Kept the improved implementation in `Student.java`.

**Verification:** The existing student-service test suite passed all seven checks after the change.

## Entry 3: StudentService — Vague Prompt and Review

**Prompt:** Generate a basic in-memory StudentService using an ArrayList, with methods to add, find, list, and remove students.

**Findings in the generated version:**
1. Null students were accepted.
2. Duplicate student IDs were allowed.
3. Missing lookups returned null.
4. Invalid IDs were not rejected.
5. The returned list was mutable, which may not meet a read-only API contract.

**Corrections:** Added null and duplicate checks, changed lookup to `Optional<Student>`, added positive-ID validation, and returned `List.copyOf(students)`.

**Verification:** The corrected `StudentServiceVague.java` was compiled separately. The existing `StudentServiceTest` and `PostRepositoryTest` suites passed seven and nine checks respectively.

## Entry 4: Post Entity and Repository

**AI assistance:** Used AI assistance to develop an immutable `Post` entity and an in-memory `PostRepository`.

**Review:** Checked ID and title validation, non-negative view counts, duplicate IDs, null input, `Optional` lookup, collection copying, and consistency between `equals()` and `hashCode()`.

**Verification:** `PostRepositoryTest` passed all nine checks.

## Responsible AI Practices

- Treat generated code as a draft, not as automatically correct.
- Check the actual code before recording a finding.
- Distinguish confirmed defects from design trade-offs.
- Compile with `javac -Xlint:all -Werror` and run tests.
- Do not claim a test or fix passed unless it was actually executed.
- Avoid logging sensitive data in `toString()` and application logs.

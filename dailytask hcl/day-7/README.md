# Day 7: Collections Framework — Part 1

## Library Waitlist

### Collections Used

- **ArrayList:** Stores the library catalogue, preserves order, and supports fast indexed access.
- **LinkedHashSet:** Removes duplicate books while preserving insertion order.
- **ArrayDeque:** Implements the reader waitlist using FIFO order.
- **PriorityQueue:** Processes faculty requests before student requests using priority values.
- **Iterator.remove():** Safely removes overdue books while iterating.
- **LinkedList:** Used to compare performance with ArrayList.

### Test Results

- Catalogue displayed successfully.
- Duplicate books were removed from the unique collection.
- Anu was served first from the waitlist.
- Faculty Meena was served before student requests.
- Clean Code was removed from the overdue list.
- The program compiled and ran successfully.

### Timing Results

| Collection | Indexed get total (ns) | Insert at start total (ns) |
|---|---:|---:|
| ArrayList | 420100 | 10004800 |
| LinkedList | 69177800 | 1619400 |

These are the observed results from this run. Timings vary by machine, JVM, and workload.

### Conclusion

ArrayList is suitable for catalogue operations requiring frequent indexed access. LinkedList can perform well for insertion at the beginning, while ArrayDeque is a good choice for a FIFO waitlist. LinkedHashSet preserves insertion order while removing duplicates, and PriorityQueue processes items according to priority.

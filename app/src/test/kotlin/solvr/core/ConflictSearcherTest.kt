package solvr.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException

class ConflictSearcherTest {
    @Test
    fun `test_001 should not identify conflict in empty file`() {
        val filename = "/solvr/core/001_empty"
        val input = this::class.java.getResourceAsStream(filename) ?: throw FileNotFoundException(filename)

        val conflictSearcher = ConflictSearcher()

        val result = conflictSearcher.search(input)

        Assertions.assertInstanceOf(NoConflictsFound::class.java, result)
    }

    @Test
    fun `test_002 should not identify conflict in file without conflicts`() {
        val filename = "/solvr/core/002_no_conflicts"
        val input = this::class.java.getResourceAsStream(filename) ?: throw FileNotFoundException(filename)

        val conflictSearcher = ConflictSearcher()

        val result = conflictSearcher.search(input)

        Assertions.assertInstanceOf(NoConflictsFound::class.java, result)
    }

    @Test
    fun `test_003 should detect conflict in line 1`() {
        val filename = "/solvr/core/003_one_conflict_line_1"
        val input = this::class.java.getResourceAsStream(filename) ?: throw FileNotFoundException(filename)

        val conflictSearcher = ConflictSearcher()

        val result = conflictSearcher.search(input)

        Assertions.assertInstanceOf(ConflictsFound::class.java, result)
    }

    @Test
    fun `test_004 should detect conflict in line 2`() {
        val filename = "/solvr/core/004_one_conflict_line_2"
        val input = this::class.java.getResourceAsStream(filename) ?: throw FileNotFoundException(filename)

        val conflictSearcher = ConflictSearcher()

        val result = conflictSearcher.search(input)

        Assertions.assertInstanceOf(ConflictsFound::class.java, result)
    }

    @Test
    fun `test_005 should return one conflict`() {
        val filename = "/solvr/core/005_one_conflict_line_1"
        val input = this::class.java.getResourceAsStream(filename) ?: throw FileNotFoundException(filename)

        val conflictSearcher = ConflictSearcher()

        val result = conflictSearcher.search(input)

        Assertions.assertInstanceOf(ConflictsFound::class.java, result)
        val conflicts = (result as ConflictsFound).conflicts

        val expectedConflicts = listOf(Conflict(2, 2, 4, 4))

        Assertions.assertEquals(expectedConflicts, conflicts)
    }

    @Test
    fun `test_006 should return two conflicts`() {
        val filename = "/solvr/core/006_two_conflicts"
        val input = this::class.java.getResourceAsStream(filename) ?: throw FileNotFoundException(filename)

        val conflictSearcher = ConflictSearcher()

        val result = conflictSearcher.search(input)

        Assertions.assertInstanceOf(ConflictsFound::class.java, result)
        val conflicts = (result as ConflictsFound).conflicts

        val expectedConflicts =
            listOf(
                Conflict(2, 2, 4, 4),
                Conflict(8, 8, 10, 10),
            )

        Assertions.assertEquals(expectedConflicts, conflicts)
    }
}

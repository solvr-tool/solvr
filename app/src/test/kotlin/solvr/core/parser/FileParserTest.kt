package solvr.core.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException

class FileParserTest {
    private val filePrefix = "/solvr/core/parser"
    private val inputFilePrefix = "$filePrefix/input"
    private val expectedFilePrefix = "$filePrefix/expected"

    @Test
    fun `test_001 given file without conflicts when parsing head should remain the same`() {
        val fileParser = FileParser()
        val inputFilePath = "$inputFilePrefix/001_no_conflicts"
        val inputString = getResourceAsString(inputFilePath)

        val resultFile = fileParser.getHeadFile(inputString)

        Assertions.assertEquals(inputString, resultFile)
    }

    @Test
    fun `test_002 given file with one conflict when parsing head should return head`() {
        val fileParser = FileParser()

        val inputFilePath = "$inputFilePrefix/002_one_conflict"
        val inputString = getResourceAsString(inputFilePath)

        val expectedFilePath = "$expectedFilePrefix/002"
        val expectedString = getResourceAsString(expectedFilePath)

        val resultFile = fileParser.getHeadFile(inputString)

        Assertions.assertEquals(expectedString, resultFile)
    }

    @Test
    fun `test_003 given file with two conflicts when parsing head should return head from both`() {
        val fileParser = FileParser()

        val inputFilePath = "$inputFilePrefix/003_two_conflicts"
        val inputString = getResourceAsString(inputFilePath)

        val expectedFilePath = "$expectedFilePrefix/003"
        val expectedString = getResourceAsString(expectedFilePath)

        val resultFile = fileParser.getHeadFile(inputString)

        Assertions.assertEquals(expectedString, resultFile)
    }

    private fun getResourceAsString(path: String): String {
        val input = this::class.java.getResourceAsStream(path) ?: throw FileNotFoundException(path)
        return input.bufferedReader().readText()
    }

    @Test
    fun `test_004 given a file with one conflict when parsing other should return other`() {
        val fileParser = FileParser()

        val inputFilePath = "$inputFilePrefix/004_one_conflict"
        val inputString = getResourceAsString(inputFilePath)

        val expectedFilePath = "$expectedFilePrefix/004"
        val expectedString = getResourceAsString(expectedFilePath)

        val resultFile = fileParser.getOtherFile(inputString)

        Assertions.assertEquals(expectedString, resultFile)
    }

    @Test
    fun `test_005 given a file with two conflicts when parsing other should return other in both`() {
        val fileParser = FileParser()

        val inputFilePath = "$inputFilePrefix/005_two_conflicts"
        val inputString = getResourceAsString(inputFilePath)

        val expectedFilePath = "$expectedFilePrefix/005"
        val expectedString = getResourceAsString(expectedFilePath)

        val resultFile = fileParser.getOtherFile(inputString)

        Assertions.assertEquals(expectedString, resultFile)
    }
}

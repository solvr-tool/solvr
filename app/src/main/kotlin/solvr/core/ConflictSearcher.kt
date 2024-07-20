package solvr.core

import java.io.BufferedReader
import java.io.InputStream

class ConflictSearcher {
    fun search(input: InputStream): ConflictSearchResult {
        val conflicts = searchAllConflicts(input)

        return if (conflicts.isEmpty()) {
            NoConflictsFound
        } else {
            ConflictsFound(conflicts)
        }
    }

    private fun searchAllConflicts(input: InputStream): MutableList<Conflict> {
        val bufferedReader = input.bufferedReader()
        val conflicts = mutableListOf<Conflict>()

        var currentLineNumber = 1
        var currentLine = bufferedReader.readLine()

        while (isNotEmpty(currentLine)) {
            if (hasConflictStartPattern(currentLine)) {
                val conflictResult = parseConflict(bufferedReader, currentLineNumber)
                currentLineNumber = conflictResult.second
                conflicts.add(conflictResult.first)
            }

            currentLine = bufferedReader.readLine()
            currentLineNumber += 1
        }

        return conflicts
    }

    private fun parseConflict(
        reader: BufferedReader,
        prevLineNumber: Int,
    ): Pair<Conflict, Int> {
        val headStartLine = prevLineNumber + 1

        var currentLine = reader.readLine()
        var currentLineNumber = prevLineNumber + 1

        while (!hasConflictMidPattern(currentLine)) {
            currentLine = reader.readLine()
            currentLineNumber += 1
        }

        val headEndLine = currentLineNumber - 1
        val branchStartLine = currentLineNumber + 1

        while (!hasConflictEndPattern(currentLine)) {
            currentLine = reader.readLine()
            currentLineNumber += 1
        }

        val branchEndLine = currentLineNumber - 1

        return Pair(
            Conflict(headStartLine, headEndLine, branchStartLine, branchEndLine),
            currentLineNumber,
        )
    }

    private fun hasConflictEndPattern(currentLine: String) = currentLine.contains(">>>>>>>")

    private fun hasConflictMidPattern(currentLine: String) = currentLine.contains("=======")

    private fun hasConflictStartPattern(nextLine: String) = nextLine.contains("<<<<<<<")

    private fun isNotEmpty(nextLine: String?) = nextLine != null
}

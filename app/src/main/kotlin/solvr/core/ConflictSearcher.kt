package solvr.core

import java.io.InputStream

class ConflictSearcher {
    fun search(input: InputStream): ConflictSearchResult {
        val bufferedReader = input.bufferedReader()

        var nextLine = bufferedReader.readLine()
        while (isNotEmpty(nextLine)) {
            if (hasConflictStartPattern(nextLine)) {
                return ConflictsFound
            }

            nextLine = bufferedReader.readLine()
        }

        return NoConflictsFound
    }

    private fun hasConflictStartPattern(nextLine: String) = nextLine.contains("<<<<<<<")

    private fun isNotEmpty(nextLine: String?) = nextLine != null
}

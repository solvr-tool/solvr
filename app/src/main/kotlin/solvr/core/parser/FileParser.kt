package solvr.core.parser

class FileParser {
    fun getHeadFile(inputFile: String): String {
        return getFile(inputFile, this::parseHeadFromConflict)
    }

    fun getOtherFile(inputFile: String): String {
        return getFile(inputFile, this::parseOtherFromConflict)
    }

    private fun getFile(
        inputFile: String,
        parsingFunction: (currentLine: Int, lines: List<String>, result: MutableList<String>) -> Int,
    ): String {
        val lines = inputFile.split("\n")
        var currentLine = 0

        val result = mutableListOf<String>()

        while (currentLine < lines.size) {
            if (isStartOfConflict(lines, currentLine)) {
                currentLine = parsingFunction(currentLine, lines, result)
                continue
            }

            result.add(lines[currentLine])
            currentLine += 1
        }

        return result.joinToString("\n")
    }

    private fun parseHeadFromConflict(
        currentLine: Int,
        lines: List<String>,
        result: MutableList<String>,
    ): Int {
        return parseOneChangeFromConflict(currentLine, lines, result, true)
    }

    private fun parseOtherFromConflict(
        currentLine: Int,
        lines: List<String>,
        result: MutableList<String>,
    ): Int {
        return parseOneChangeFromConflict(currentLine, lines, result, false)
    }

    private fun parseOneChangeFromConflict(
        currentLine: Int,
        lines: List<String>,
        result: MutableList<String>,
        shouldParseHead: Boolean,
    ): Int {
        var conflictCurrentLine = currentLine
        conflictCurrentLine += 1

        while (!isMiddleOfConflict(lines, conflictCurrentLine)) {
            if (shouldParseHead) result.add(lines[conflictCurrentLine])
            conflictCurrentLine += 1
        }

        conflictCurrentLine += 1

        while (!isEndOfConflict(lines, conflictCurrentLine)) {
            if (!shouldParseHead) result.add(lines[conflictCurrentLine])
            conflictCurrentLine += 1
        }

        conflictCurrentLine += 1
        return conflictCurrentLine
    }

    private fun isEndOfConflict(
        lines: List<String>,
        currentLine: Int,
    ) = lines[currentLine].contains(">>>>>>>")

    private fun isMiddleOfConflict(
        lines: List<String>,
        currentLine: Int,
    ) = lines[currentLine].contains("=======")

    private fun isStartOfConflict(
        lines: List<String>,
        currentLine: Int,
    ) = lines[currentLine].contains("<<<<<<<")
}

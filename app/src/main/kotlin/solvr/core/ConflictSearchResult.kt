package solvr.core

sealed interface ConflictSearchResult

data class ConflictsFound(val conflicts: List<Conflict>) : ConflictSearchResult

data object NoConflictsFound : ConflictSearchResult

data class Conflict(
    val headStartLine: Int,
    val headEndLine: Int,
    val branchStartLine: Int,
    val branchEndLine: Int,
)

package solvr.core

sealed interface ConflictSearchResult

data object ConflictsFound : ConflictSearchResult

data object NoConflictsFound : ConflictSearchResult

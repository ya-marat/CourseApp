package com.example.feature_course

sealed class CourseListUiState {

    object Initial: CourseListUiState()
    object Loading: CourseListUiState()

    data class Success(
        val courses: List<CourseUi>
    ): CourseListUiState()

    object Error: CourseListUiState()
}
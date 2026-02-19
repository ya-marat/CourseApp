package com.example.feature_course.mainscreen

import com.example.feature_course.list.main.CourseUi

sealed class CourseListUiState {

    object Initial: CourseListUiState()
    object Loading: CourseListUiState()

    data class Success(
        val courses: List<CourseUi>
    ): CourseListUiState()
}
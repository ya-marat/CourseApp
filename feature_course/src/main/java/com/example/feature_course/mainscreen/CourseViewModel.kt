package com.example.feature_course.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.result.Result
import com.example.domain.domain.usecase.LoadCourseListUseCase
import com.example.domain.domain.usecase.ObserveCoursesUseCase
import com.example.domain.domain.usecase.ToggleFavouriteCourseUseCase
import com.example.feature_course.CourseListUiState
import com.example.feature_course.mapper.CourseModuleMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CourseViewModel @Inject constructor(
    val loadCourseListUseCase: LoadCourseListUseCase,
    val observeCoursesUseCase: ObserveCoursesUseCase,
    val toggleFavouriteCourseUseCase: ToggleFavouriteCourseUseCase,
    val courseModuleMapper: CourseModuleMapper
) : ViewModel() {
    private var _state = MutableStateFlow<CourseListUiState>(CourseListUiState.Initial)
    val state = _state.asStateFlow()

    init {
        observeCourses()
        loadCourses()
    }

    fun toggleFavourite(courseId: Int) {
        viewModelScope.launch {
            toggleFavouriteCourseUseCase(courseId)
        }
    }

    private fun observeCourses() {
        viewModelScope.launch {
            observeCoursesUseCase().collect { courses ->
                _state.value = CourseListUiState.Success(
                    courses.map { course -> courseModuleMapper.mapDomainToCourseUi(course) }
                )
            }
        }
    }

    private fun loadCourses() {
        viewModelScope.launch {
            _state.value = CourseListUiState.Loading

            val result = loadCourseListUseCase()

            when (result) {
                is Result.Failure -> {
                    _state.value = CourseListUiState.Error
                }

                is Result.Success<*> -> {}
            }
        }
    }
}
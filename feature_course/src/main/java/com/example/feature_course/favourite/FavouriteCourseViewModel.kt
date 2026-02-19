package com.example.feature_course.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.usecase.ObserveFavouriteCoursesUseCase
import com.example.domain.domain.usecase.ToggleFavouriteCourseUseCase
import com.example.feature_course.CourseListUiState
import com.example.feature_course.mapper.CourseModuleMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteCourseViewModel @Inject constructor(
    val observeFavouriteCoursesUseCase: ObserveFavouriteCoursesUseCase,
    val toggleFavouriteCourseUseCase: ToggleFavouriteCourseUseCase,
    val mapper: CourseModuleMapper
) : ViewModel() {

    private val _state = MutableStateFlow<CourseListUiState>(CourseListUiState.Initial)
    val state = _state.asStateFlow()

    init {
        observeFavourites()
    }

    fun observeFavourites(){
        viewModelScope.launch {
            observeFavouriteCoursesUseCase().collect { courses ->
                _state.value  = CourseListUiState.Success(
                    courses.map { mapper.mapDomainToCourseUi(it) }
                )
            }
        }
    }

    fun toggleFavourite(courseId: Int) {
        viewModelScope.launch {
            toggleFavouriteCourseUseCase(courseId)
        }
    }
}
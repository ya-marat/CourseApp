package com.example.feature_course.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.result.Result
import com.example.domain.domain.usecase.LoadCourseListUseCase
import com.example.feature_course.list.main.CourseUi
import com.example.feature_course.mapper.CourseModuleMapper
import com.example.feature_course.toMillis
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CourseViewModel @Inject constructor(
    val loadCourseListUseCase: LoadCourseListUseCase,
    val courseModuleMapper: CourseModuleMapper
) : ViewModel() {

    private var _state = MutableStateFlow<CourseListUiState>(CourseListUiState.Initial)
    val state = _state.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            _state.value = CourseListUiState.Loading

            delay(1000)

            //testValues()
            val result = loadCourseListUseCase()

            if (result is Result.Success) {
                val list = result.data
                val mappedList =
                    list.map { course -> courseModuleMapper.mapDomainToCourseUi(course) }
                _state.value = CourseListUiState.Success(mappedList)
            }
        }
    }

    private fun testValues() {
        val list = mutableListOf<CourseUi>()
        repeat(10) { i ->
            list.add(CourseUi(
                id = i,
                title = "Test course title $i",
                text = "Test course description ${i}",
                price = "2200 Rub",
                rate = "2.5",
                publishDate = "2022-04-13",
                isFavourite = true,
                publishDateMillis = "2024-09-10".toMillis()
            ))
        }

        _state.value = CourseListUiState.Success(list)
    }
}
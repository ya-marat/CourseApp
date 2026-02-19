package com.example.feature_course.mapper

import com.example.domain.domain.models.Course
import com.example.domain.domain.result.DomainError
import com.example.feature_course.CourseUiError
import com.example.feature_course.CourseUi
import com.example.feature_course.R
import com.example.feature_course.toDateString
import javax.inject.Inject

class CourseModuleMapper @Inject constructor() {

    fun mapDomainToCourseUi(courseDomain: Course) = CourseUi(
        id = courseDomain.id,
        title = courseDomain.title,
        text = courseDomain.text,
        price = courseDomain.price,
        rate = courseDomain.rate,
        publishDate = courseDomain.publishDate.toDateString(),
        isFavourite = courseDomain.hasLike,
        publishDateMillis = courseDomain.publishDate,
        coverResId = getRandomCover(courseDomain.id)
    )

    /**
     * Сделана выборка обложки по id курса
     */
    private fun getRandomCover(courseId: Int):Int {
        return when(courseId) {
            100 -> R.drawable.course_cover_1
            101 -> R.drawable.course_cover_2
            102 -> R.drawable.course_cover_3
            104 -> R.drawable.course_cover_1
            else -> R.drawable.course_cover_2
        }
    }
}
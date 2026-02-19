package com.example.feature_course.mapper

import com.example.domain.domain.models.Course
import com.example.domain.domain.result.DomainError
import com.example.feature_course.CourseUiError
import com.example.feature_course.list.main.CourseUi
import com.example.feature_course.toDateString
import javax.inject.Inject

class CourseModuleMapper @Inject constructor() {

    /*
        Реализация пока такая. По сути задел на будущее,
        так как могут быть разные ошибки и по разному их надо обрабатывать
     */
    fun mapDomainErrorToCourseUiError(domainError: DomainError): CourseUiError {
        return when(domainError) {
            else -> CourseUiError.Unknow
        }
    }

    fun mapDomainToCourseUi(courseDomain: Course) = CourseUi(
        id = courseDomain.id,
        title = courseDomain.title,
        text = courseDomain.text,
        price = courseDomain.price,
        rate = courseDomain.rate,
        publishDate = courseDomain.publishDate.toDateString(),
        isFavourite = courseDomain.hasLike,
        publishDateMillis = courseDomain.publishDate
    )
}
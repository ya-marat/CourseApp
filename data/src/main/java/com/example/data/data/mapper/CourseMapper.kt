package com.example.data.data.mapper

import com.example.data.data.local.entity.CourseDbEntity
import com.example.data.data.remote.network.dto.CourseDto
import com.example.data.data.toMillis
import com.example.domain.domain.models.Course
import javax.inject.Inject

class CourseMapper @Inject constructor() {

    fun mapCourseDtoToDomain(dto: CourseDto) = Course(
        id = dto.id,
        title = dto.title,
        text = dto.text,
        price = dto.price,
        rate = dto.rate,
        hasLike = false,
        publishDate = dto.publishDate.toMillis()
    )

    fun mapDomainCourseToDbCourseEntity(course: Course) = CourseDbEntity(
        id = course.id,
        title = course.title,
        text = course.text,
        price = course.price,
        rate = course.rate,
        hasLike = course.hasLike,
        publishDate = course.publishDate
    )


    fun mapCourseDbEntityToCourseDomain(courseDbEntity: CourseDbEntity) = Course(
        id = courseDbEntity.id,
        title = courseDbEntity.title,
        text = courseDbEntity.text,
        price = courseDbEntity.price,
        rate = courseDbEntity.rate,
        hasLike = courseDbEntity.hasLike,
        publishDate = courseDbEntity.publishDate
    )

    fun mapCourseDtoToCourseDbEntity(courseDto: CourseDto) = CourseDbEntity(
        id = courseDto.id,
        title = courseDto.title,
        text = courseDto.text,
        price = courseDto.price,
        rate = courseDto.rate,
        hasLike = courseDto.hasLike,
        publishDate = courseDto.publishDate.toMillis()
    )
}
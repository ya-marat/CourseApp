package com.example.domain.domain.repository

import com.example.domain.domain.models.Course
import com.example.domain.domain.result.Result

interface RemoteCourseRepository {

    fun getCourses(): Result<List<Course>>
}
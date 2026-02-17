package com.example.feature_auth

import com.example.domain.domain.repository.CourseRepository

interface AuthDependencies {
    fun getCourseRepository(): CourseRepository
}
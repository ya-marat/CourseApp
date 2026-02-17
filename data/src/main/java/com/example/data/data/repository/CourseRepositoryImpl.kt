package com.example.data.data.repository

import android.util.Log
import com.example.data.data.local.db.DatabaseDao
import com.example.data.data.remote.network.ApiService
import com.example.domain.domain.models.Course
import com.example.domain.domain.repository.CourseRepository
import com.example.domain.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val databaseDao: DatabaseDao
): CourseRepository {

    override val favouriteCourses: Flow<List<Course>>
        get() = flow {

        }

    override fun addCourseToFavourite(course: Course): Result<Unit> {
        Log.d("CourseRepositoryImpl", "addCourseToFavourite")
        TODO("Not yet implemented")
    }

    override fun removeCourseFromFavourite(courseId: Int): Result<Unit> {
        Log.d("CourseRepositoryImpl", "removeCourseFromFavourite")
        TODO("Not yet implemented")
    }

    override fun loadCourses(): Result<List<Course>> {
        Log.d("CourseRepositoryImpl", "loadCourses")
        TODO("Not yet implemented")
    }
}
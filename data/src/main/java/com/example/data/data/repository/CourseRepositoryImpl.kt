package com.example.data.data.repository

import android.util.Log
import com.example.data.data.local.db.DatabaseDao
import com.example.data.data.mapper.CourseMapper
import com.example.data.data.remote.network.ApiService
import com.example.domain.domain.models.Course
import com.example.domain.domain.repository.CourseRepository
import com.example.domain.domain.result.DomainError
import com.example.domain.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val databaseDao: DatabaseDao,
    val mapper: CourseMapper
) : CourseRepository {

    override val favouriteCourses: Flow<List<Course>>
        get() = databaseDao.getFavouritesCourses()
            .map { entities ->
                entities.map { entity -> mapper.mapCourseDbEntityToCourseDomain(entity) }
            }

    override suspend fun addCourseToFavourite(course: Course): Result<Unit> {
        return dbCall {
            databaseDao.insertCourseToFavourite(mapper.mapDomainCourseToDbCourseEntity(course))
        }
    }

    override suspend fun removeCourseFromFavourite(courseId: Int): Result<Unit> {
        return dbCall {
            databaseDao.removeCourseFromFavourite(courseId)
        }
    }

    override suspend fun loadCourses(): Result<List<Course>> {
        return try {
            val loadedList = apiService.loadCourseList()
            val mappedData =
                loadedList.courses.map { dtoCourse -> mapper.mapCourseDtoToDomain(dtoCourse) }
            Result.Success(mappedData)
        } catch (e: Exception) {
            Result.Failure(DomainError.Unknown)
        }
    }

    private suspend fun <T> dbCall(
        dbMethodCall: suspend () -> T
    ): Result<T> {
        return try {
            Result.Success(dbMethodCall())
        } catch (e: Exception) {
            Result.Failure(DomainError.Unknown)
        }
    }
}
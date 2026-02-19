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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val databaseDao: DatabaseDao,
    val mapper: CourseMapper
) : CourseRepository {

    private val remoteCourses = MutableStateFlow<List<Course>>(emptyList())

    override fun observeFavouriteCourses(): Flow<List<Course>> {
        return combine(
            remoteCourses,
            databaseDao.getFavouritesCourses()
        ) { courses, favourites ->

            val favouriteIds = favourites.map { it.id }.toSet()

            courses
                .filter { favouriteIds.contains(it.id) }
                .map { it.copy(hasLike = true) }
        }
    }

    override fun observeCourses(): Flow<List<Course>> {
        return combine(
            remoteCourses,
            databaseDao.getFavouritesCourses()
        ) { courses, favourites ->

            val favouriteIds = favourites.map { it.id }.toSet()

            courses.map { course ->
                course.copy(
                    hasLike = favouriteIds.contains(course.id)
                )
            }
        }
    }

    override suspend fun toggleFavouriteCourse(courseId: Int): Result<Unit> {

        val isFavourite = databaseDao.isFavourite(courseId)
        return if (isFavourite) {
            dbCall { databaseDao.removeCourseFromFavourite(courseId) }
        } else {
            val remoteCoursesValue = remoteCourses.value
            val course = remoteCoursesValue.find { it.id == courseId }

            if (course == null) {
                return Result.Failure(DomainError.Unknown)
            }

            dbCall {
                databaseDao.insertCourseToFavourite(
                    mapper.mapDomainCourseToDbCourseEntity(
                        course
                    )
                )
            }
        }
    }

    override suspend fun loadCourses(): Result<Unit> {
        return try {
            val loadedList = apiService.loadCourseList()

            val dtoCourseList = loadedList.courses

            val initialFavourites = dtoCourseList
                .filter { it.hasLike }
                .map { mapper.mapCourseDtoToCourseDbEntity(it) }
            databaseDao.insertAllFavourites(initialFavourites)

            databaseDao.getFavouritesCourses().first()
            remoteCourses.value = dtoCourseList.map { mapper.mapCourseDtoToDomain(it) }

            Result.Success(Unit)
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
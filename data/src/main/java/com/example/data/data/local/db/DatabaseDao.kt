package com.example.data.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.data.local.entity.CourseDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {

    @Query("SELECT * FROM courses")
    fun getFavouritesCourses(): Flow<List<CourseDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourseToFavourite(courseDbEntity: CourseDbEntity)

    @Query("DELETE FROM courses WHERE id=:courseId")
    suspend fun removeCourseFromFavourite(courseId: Int)
}
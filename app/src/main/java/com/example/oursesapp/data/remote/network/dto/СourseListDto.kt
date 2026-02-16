package com.example.oursesapp.data.remote.network.dto

import com.google.gson.annotations.SerializedName

data class СourseListDto(

    @SerializedName("courses")
    val courses: List<CourseDto>
)

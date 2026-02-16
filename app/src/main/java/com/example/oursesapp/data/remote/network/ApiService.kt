package com.example.oursesapp.data.remote.network

import com.example.oursesapp.data.remote.network.dto.СourseListDto
import retrofit2.http.GET

interface ApiService {

    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun loadCourseList(): СourseListDto
}
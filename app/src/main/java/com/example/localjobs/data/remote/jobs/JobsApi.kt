package com.example.localjobs.data.remote.jobs

import com.example.localjobs.data.remote.jobs.dto.PageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {

    @GET("jobs")
    suspend fun getJobs(
        @Query("page") page: Long
    ) : PageDto

    companion object{
        const val BASE_URL = "https://testapi.getlokalapp.com/common/"
    }
}

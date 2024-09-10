package com.example.localjobs.data.remote.jobs.dto

import com.example.localjobs.data.remote.jobs.dto.model.Job
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageDto(
    @SerialName("results" ) var results : ArrayList<Job> = arrayListOf()
)

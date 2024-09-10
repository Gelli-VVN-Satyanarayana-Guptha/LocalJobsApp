package com.example.localjobs.domain.jobs.repository

import androidx.paging.PagingData
import com.example.localjobs.domain.jobs.model.JobData
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for accessing the Jobs Database
 */
interface JobsRepository {

    /**
     * Get the All the Bookmarked Jobs
     */
    suspend fun getBookmarkedJobs() : List<JobData>

    /**
     * Update the job in the room database
     * This is used to update the bookmark status of the job
     */
    suspend fun updateJob(jobData: JobData)

    /**
     * Clear all the jobs from the room database
     * Similar to clearing the cache, used for refreshing the jobs
     */
    suspend fun clearJobs()

}
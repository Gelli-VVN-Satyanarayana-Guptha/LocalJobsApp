package com.example.localjobs.data.local.jobs

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.localjobs.data.local.jobs.entity.JobEntity

@Dao
interface JobsDao {
    @Upsert(entity = JobEntity::class)
    suspend fun upsertAll(jobs : List<JobEntity>)

    @Query("SELECT * FROM jobs")
    fun pagingSource() : PagingSource<Int, JobEntity>

    @Query("SELECT * FROM jobs WHERE isBookmarked = 1")
    suspend fun getBookmarkedJobs() : List<JobEntity>

    @Update
    suspend fun updateJob(job: JobEntity)

    @Query("DELETE FROM jobs")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM jobs")
    fun getCount() : Long
}
package com.example.localjobs.data.local.jobs

import androidx.paging.PagingData
import com.example.localjobs.data.local.jobs.entity.JobEntity
import com.example.localjobs.domain.jobs.model.JobData
import com.example.localjobs.domain.jobs.repository.JobsRepository
import kotlinx.coroutines.flow.Flow

class JobsRepositoryImpl(
    private val dao: JobsDao
) : JobsRepository {

    override suspend fun getBookmarkedJobs(): List<JobData> {
        return dao.getBookmarkedJobs().map { it.toJobData() }
    }

    override suspend fun updateJob(jobData: JobData) {
        dao.updateJob(jobData.toJobEntity())
    }

    override suspend fun clearJobs() {
        dao.clearAll()
    }

}
package com.example.localjobs.data.remote.jobs

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.localjobs.data.local.jobs.JobsDatabase
import com.example.localjobs.data.local.jobs.entity.JobEntity
import java.io.IOException

/**
 * Remote Mediator for Jobs
 * Acts as a bridge between the local database and the remote API.
 * Will Handle the Pagination Logic
 */
@OptIn(ExperimentalPagingApi::class)
class JobsRemoteMediator (
    private val jobDb : JobsDatabase,
    private val jobApi: JobsApi,
) : RemoteMediator<Int, JobEntity>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, JobEntity>
    ): MediatorResult {

        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return  MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    (jobDb.dao.getCount() / 10) + 1
                }
            }
            Log.d(TAG, "loadKey (page) : $loadKey")

            // Load the jobs data from the API
            val jobs = jobApi.getJobs(page = loadKey)

            Log.d(TAG, "Loaded Job Count : ${jobs.results.size}")
            Log.d(TAG, "jobs : $jobs")

            jobDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    jobDb.dao.clearAll()
                }
                val jobEntities = jobs.results.map{ it.toJobEntity() }
                jobDb.dao.upsertAll(jobEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = jobs.results.isEmpty()
            )

        }catch (e : IOException){
            MediatorResult.Error(e)
        }catch (e : HttpException){
            MediatorResult.Error(e)
        }
    }

    companion object {
        const val TAG = "JobsRemoteMediator"
    }
}


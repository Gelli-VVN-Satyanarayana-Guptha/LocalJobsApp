package com.example.localjobs.presentation.screens.jobs

import android.util.Log
import com.example.localjobs.data.local.jobs.entity.JobEntity
import com.example.localjobs.domain.jobs.repository.JobsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.localjobs.domain.jobs.model.JobData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Jobs View Model used by both the Jobs Screen and Job Details Screen
 * Currently we are using the shared view model for both the screens
 */
@HiltViewModel
class JobsViewModel @Inject constructor(
    private val pager : Pager<Int, JobEntity>,
    private val jobsRepo : JobsRepository
) : ViewModel() {

    val jobsPagingFlow = pager.flow
        .map { pagingData ->
            pagingData.map { it.toJobData() }
        }.cachedIn(viewModelScope)

    var bookmarkedJobsList = MutableStateFlow<List<JobData>>(emptyList())

    var selectedJob = MutableStateFlow<JobData?>(null)

    fun refreshBookmarkedJobs() {
        viewModelScope.launch {
            bookmarkedJobsList.value = jobsRepo.getBookmarkedJobs()
        }
    }

    fun toggleBookmark(isBookmarked : Boolean) {
        viewModelScope.launch {
            selectedJob.value?.let {
                jobsRepo.updateJob(it.copy(isBookmarked = isBookmarked))
            }
        }
    }

}
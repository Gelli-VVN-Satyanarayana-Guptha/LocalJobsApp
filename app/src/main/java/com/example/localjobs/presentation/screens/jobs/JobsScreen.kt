package com.example.localjobs.presentation.screens.jobs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.localjobs.domain.jobs.model.JobData
import com.example.localjobs.presentation.screens.jobs.widgets.CustomNavigationBar
import com.example.localjobs.presentation.screens.jobs.widgets.JobCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreen (
    jobsViewModel: JobsViewModel,
    navController: NavController,
) {
    val jobs = jobsViewModel.jobsPagingFlow.collectAsLazyPagingItems()
    var showBookmarks by remember { mutableStateOf(false) }
    val bookmarkedJobList by jobsViewModel.bookmarkedJobsList.collectAsState()

    MaterialTheme {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (showBookmarks) "Bookmarks" else "Jobs",
                            textAlign = TextAlign.Center
                        )
                    },
                )
            },
            bottomBar =  {
                CustomNavigationBar {
                    if (it) jobsViewModel.refreshBookmarkedJobs()
                    showBookmarks = it
                }
            }
        ) { it ->
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (jobs.loadState.refresh is LoadState.Loading) {
                    Box (
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else if (jobs.loadState.hasError) {
                    Text(
                        text = "Error: " + (jobs.loadState.refresh as LoadState.Error).error.message,
                        color = Color.Red
                    )
                } else {
                    if (showBookmarks) {
                        BookmarksList(
                            bookmarkedJobList = bookmarkedJobList,
                            navigateToJobDetails = { job ->
                                jobsViewModel.selectedJob.value = job
                                navController.navigate("detail")
                            }
                        )
                    } else {
                        JobsList(
                            jobs = jobs,
                            navigateToJobDetails = { job ->
                                jobsViewModel.selectedJob.value = job
                                navController.navigate("detail")
                            }
                        )
                    }
                }
            }
        }
    }
}

// Loads all the jobs from the database and displays them in a list
@Composable
fun JobsList(
    jobs: LazyPagingItems<JobData>,
    navigateToJobDetails: (job: JobData) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(jobs.itemCount) {
            jobs[it]?.let { job ->
                if (job.title != null) {
                    JobCard(
                        jobData = job,
                        onClick = {
                            navigateToJobDetails(job)
                        }
                    )
                }
            }
        }

        item {
            if(jobs.loadState.append is LoadState.Loading){
                CircularProgressIndicator()
            }
        }
    }
}

// Loads all the bookmarked jobs from the database and displays them in a list
@Composable
fun BookmarksList(
    bookmarkedJobList : List<JobData>,
    navigateToJobDetails: (job : JobData) -> Unit
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(bookmarkedJobList) { job ->
            JobCard(
                jobData = job,
                onClick = { navigateToJobDetails(job) }
            )
        }
    }
}

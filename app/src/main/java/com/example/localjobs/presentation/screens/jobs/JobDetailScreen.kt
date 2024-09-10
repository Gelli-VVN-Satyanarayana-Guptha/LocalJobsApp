package com.example.localjobs.presentation.screens.jobs

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import com.example.localjobs.domain.jobs.model.JobData
import com.example.localjobs.presentation.screens.jobs.widgets.CustomNavigationBar
import com.example.localjobs.presentation.screens.jobs.widgets.JobCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailScreen(
    jobsViewModel: JobsViewModel = hiltViewModel(),
    navController: NavController,
) {
    val selectedJobData by jobsViewModel.selectedJob.collectAsState()
    var isBookmarked by remember { mutableStateOf(selectedJobData?.isBookmarked ?: false) }

    DisposableEffect(Unit) {
        onDispose {
            // update the bookmark of the job
            if (isBookmarked != selectedJobData?.isBookmarked) {
                jobsViewModel.toggleBookmark(isBookmarked)
            }
        }
    }

    MaterialTheme {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Job Details",
                            textAlign = TextAlign.Center
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            // Back arrow icon
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            },
        ) { it ->
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = selectedJobData?.title ?: "Unknown Job",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    IconButton(
                        onClick = {
                            // Toggle bookmark state
                            isBookmarked = !isBookmarked
                        }
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Bookmark"
                        )
                    }
                }

                Text(
                    text = "Place : ${selectedJobData?.primaryDetails?.place}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Salary : ${selectedJobData?.primaryDetails?.salary}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Experience : ${selectedJobData?.primaryDetails?.experience}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text (
                    text = "Company : ${selectedJobData?.companyName}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text (
                    text = "Whatsapp : ${selectedJobData?.whatsappNo}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text (
                    text = "Openings : ${selectedJobData?.openingsCount}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(1.dp))
                Text (
                    text = "Other Details: \n${selectedJobData?.otherDetails}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
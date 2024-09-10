package com.example.localjobs.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.localjobs.presentation.screens.jobs.JobDetailScreen
import com.example.localjobs.presentation.screens.jobs.JobsScreen
import com.example.localjobs.presentation.screens.jobs.JobsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // TODO Need to remove the view model injection from here
    val jobsViewModel: JobsViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "jobs") {

        composable("jobs") {
            JobsScreen(
                navController = navController,
                jobsViewModel = jobsViewModel
            )
        }

        composable("detail") {
            JobDetailScreen(
                navController = navController,
                jobsViewModel = jobsViewModel
            )
        }
    }
}
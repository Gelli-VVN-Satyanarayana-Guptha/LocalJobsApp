package com.example.localjobs.presentation.screens.jobs.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Custom Navigation Bar
// TODO Need to customize further and add nav controller
@Composable
fun CustomNavigationBar(
    filterBookmarks: (Boolean) -> Unit = {},
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Jobs", "Bookmarks")
    val icons = listOf(Icons.AutoMirrored.Filled.List, Icons.Default.Star)


    NavigationBar {
        items.forEachIndexed { idx, item ->
            val isSelected = selectedItem == idx

            NavigationBarItem(
                icon = {
                    Box(
                        modifier = Modifier.size(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icons[idx],
                            contentDescription = item,
                        )
                    }
                },
                label = {
                    Text(
                        text = item,
                    )
                },
                selected = isSelected,
                onClick = {
                    selectedItem = idx
                    if (selectedItem == 1) filterBookmarks(true)
                    else filterBookmarks(false)
                }
            )
        }
    }
}

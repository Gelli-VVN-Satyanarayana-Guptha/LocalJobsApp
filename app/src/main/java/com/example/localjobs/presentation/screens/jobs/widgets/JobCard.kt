package com.example.localjobs.presentation.screens.jobs.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localjobs.data.local.jobs.entity.PrimaryDetailsEntity
import com.example.localjobs.domain.jobs.model.JobData

@Composable
fun JobCard (
    jobData: JobData,
    onClick : () -> Unit
) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(
                border = BorderStroke(width = Dp.Hairline, color = Color.Gray),
                shape = RoundedCornerShape(10)
            )
            .clickable { onClick() },
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text (
                    text = jobData.title ?: "Unknown",
                    color = Color.Black,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text (
                    text = "Location: ${jobData.primaryDetails?.place}",
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text (
                    text = "Salary: ${jobData.primaryDetails?.salary}",
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text (
                    text = "Whatsapp: ${jobData.whatsappNo}",
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
@Preview
fun JobCardPreview() {
    JobCard(
        jobData = JobData(
            id = 1,
            title = "Software Engineer",
            primaryDetails = PrimaryDetailsEntity(
                place = "Hyderabad",
                salary = "10LPA"
            ),
            whatsappNo = "9876543210",
        ),
        onClick = {}
    )
}
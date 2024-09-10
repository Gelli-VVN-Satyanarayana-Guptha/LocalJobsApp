package com.example.localjobs.data.remote.jobs.dto.model

import com.example.localjobs.data.local.jobs.entity.JobTagsEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class JobTags (
    @SerialName("value"      ) var value     : String? = null,
    @SerialName("bg_color"   ) var bgColor   : String? = null,
    @SerialName("text_color" ) var textColor : String? = null
) {
    fun toJobTagsEntity(): JobTagsEntity {
        return JobTagsEntity(
            value = value,
            bgColor = bgColor,
            textColor = textColor
        )
    }
}
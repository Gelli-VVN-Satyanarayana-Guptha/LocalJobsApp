package com.example.localjobs.data.remote.jobs.dto.model

import com.example.localjobs.data.local.jobs.entity.CreativesEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Creatives (
    @SerialName("file"          ) var file         : String? = null,
    @SerialName("thumb_url"     ) var thumbUrl     : String? = null,
    @SerialName("creative_type" ) var creativeType : Int?    = null
) {
    fun toCreativesEntity(): CreativesEntity {
        return CreativesEntity(
            file = file,
            thumbUrl = thumbUrl,
            creativeType = creativeType
        )
    }
}

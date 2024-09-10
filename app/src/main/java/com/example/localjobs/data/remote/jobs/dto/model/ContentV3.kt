package com.example.localjobs.data.remote.jobs.dto.model

import com.example.localjobs.data.local.jobs.entity.ContentV3Entity
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ContentV3 (
    @SerialName("V3" ) var v3 : ArrayList<V3> = arrayListOf()
) {
    fun toContentV3Entity(): ContentV3Entity? {
        return ContentV3Entity(
            v3 = v3.map { it.toV3Entity() }
        )
    }
}

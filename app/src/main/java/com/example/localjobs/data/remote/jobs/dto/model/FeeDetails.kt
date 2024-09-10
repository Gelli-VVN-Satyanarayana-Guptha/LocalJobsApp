package com.example.localjobs.data.remote.jobs.dto.model

import com.example.localjobs.data.local.jobs.entity.FeeDetailsEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FeeDetails (
    @SerialName("V3" ) var v3 : ArrayList<V3> = arrayListOf()
) {
    fun toFeeDetailsEntity(): FeeDetailsEntity {
        return FeeDetailsEntity(
            v3 = v3.map { it.toV3Entity() }
        )
    }
}
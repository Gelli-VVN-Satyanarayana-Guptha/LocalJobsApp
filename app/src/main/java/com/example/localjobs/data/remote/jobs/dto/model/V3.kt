package com.example.localjobs.data.remote.jobs.dto.model

import com.example.localjobs.data.local.jobs.entity.V3Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class V3 (
    @SerialName("field_key"   ) var fieldKey   : String? = null,
    @SerialName("field_name"  ) var fieldName  : String? = null,
    @SerialName("field_value" ) var fieldValue : String? = null
) {
    fun toV3Entity(): V3Entity {
        return V3Entity(
            fieldKey = fieldKey,
            fieldName = fieldName,
            fieldValue = fieldValue
        )
    }
}
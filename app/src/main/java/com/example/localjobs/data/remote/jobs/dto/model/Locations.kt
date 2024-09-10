package com.example.localjobs.data.remote.jobs.dto.model

import com.example.localjobs.data.local.jobs.entity.LocationsEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Locations (
    @SerialName("id"     ) var id     : Int?    = null,
    @SerialName("locale" ) var locale : String? = null,
    @SerialName("state"  ) var state  : Int?    = null
) {
    fun toLocationsEntity(): LocationsEntity {
        return LocationsEntity(
            id = id,
            locale = locale,
            state = state
        )
    }
}

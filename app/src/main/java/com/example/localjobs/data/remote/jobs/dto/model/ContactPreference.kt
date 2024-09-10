package com.example.localjobs.data.remote.jobs.dto.model

import com.example.localjobs.data.local.jobs.entity.ContactPreferenceEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ContactPreference (
    @SerialName("preference"                ) var preference             : Int?    = null,
    @SerialName("whatsapp_link"             ) var whatsappLink           : String? = null,
    @SerialName("preferred_call_start_time" ) var preferredCallStartTime : String? = null,
    @SerialName("preferred_call_end_time"   ) var preferredCallEndTime   : String? = null
) {
    fun toContactPreferenceEntity(): ContactPreferenceEntity {
        return ContactPreferenceEntity(
            preference = preference,
            whatsappLink = whatsappLink,
            preferredCallStartTime = preferredCallStartTime,
        )
    }
}

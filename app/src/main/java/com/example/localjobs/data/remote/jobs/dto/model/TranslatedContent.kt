package com.example.localjobs.data.remote.jobs.dto.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslatedContent (
    @SerialName("translatedString" ) var translatedString : String? = null,
)

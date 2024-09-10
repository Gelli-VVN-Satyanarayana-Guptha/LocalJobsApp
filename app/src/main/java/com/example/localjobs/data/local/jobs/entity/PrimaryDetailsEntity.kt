package com.example.localjobs.data.local.jobs.entity

import kotlinx.serialization.Serializable

@Serializable
data class PrimaryDetailsEntity (
    var place         : String? = null,
    var salary        : String? = null,
    var jobType       : String? = null,
    var experience    : String? = null,
    var feesCharged   : String? = null,
    var qualification : String? = null
)
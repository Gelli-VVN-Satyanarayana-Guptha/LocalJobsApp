package com.example.localjobs.data.local.jobs.entity

data class ContactPreferenceEntity (
    var preference             : Int?    = null,
    var whatsappLink           : String? = null,
    var preferredCallStartTime : String? = null,
    var preferredCallEndTime   : String? = null
)

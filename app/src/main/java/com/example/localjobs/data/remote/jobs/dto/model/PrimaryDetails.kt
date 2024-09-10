package com.example.localjobs.data.remote.jobs.dto.model

import com.example.localjobs.data.local.jobs.entity.PrimaryDetailsEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PrimaryDetails (
    @SerialName("Place"         ) var place         : String? = null,
    @SerialName("Salary"        ) var salary        : String? = null,
    @SerialName("Job_Type"      ) var jobType       : String? = null,
    @SerialName("Experience"    ) var experience    : String? = null,
    @SerialName("Fees_Charged"  ) var feesCharged   : String? = null,
    @SerialName("Qualification" ) var qualification : String? = null
) {
    fun toPrimaryDetailsEntity(): PrimaryDetailsEntity {
        return PrimaryDetailsEntity(
            place = place,
            salary = salary,
            jobType = jobType,
            experience = experience,
        )
    }
}
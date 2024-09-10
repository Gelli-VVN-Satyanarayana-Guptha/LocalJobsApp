package com.example.localjobs.domain.jobs.model

import com.example.localjobs.data.local.jobs.entity.ContactPreferenceEntity
import com.example.localjobs.data.local.jobs.entity.ContentV3Entity
import com.example.localjobs.data.local.jobs.entity.CreativesEntity
import com.example.localjobs.data.local.jobs.entity.FeeDetailsEntity
import com.example.localjobs.data.local.jobs.entity.JobEntity
import com.example.localjobs.data.local.jobs.entity.JobTagsEntity
import com.example.localjobs.data.local.jobs.entity.LocationsEntity
import com.example.localjobs.data.local.jobs.entity.PrimaryDetailsEntity

/**
 * JobData model which is used to display the job data in the UI.
 * TODO currently commented out the fields which are not used in the UI.
 * TODO Need to implement the some data classes in Domain Layer which are currently using form Data Layer
 */
data class JobData(
    var id: Int = 0,
    var title: String?              = null,
    var type: Int?                 = null,
    var primaryDetails: PrimaryDetailsEntity?      = null,
//    var feeDetails: FeeDetailsEntity?          = null,
//    var jobTags: List<JobTagsEntity> = arrayListOf(),
    var jobType: Int?                 = null,
    var jobCategoryId: Int?                 = null,
    var qualification: Int?                 = null,
    var experience: Int?                 = null,
    var shiftTiming: Int?                 = null,
    var jobRoleId: Int?                 = null,
    var salaryMax: Int?                 = null,
    var salaryMin: Int?                 = null,
    var cityLocation: Int?                 = null,
    var locality: Int?                 = null,
    var premiumTill: String?              = null,
    var content: String?              = null,
    var companyName: String?              = null,
    var advertiser: Int?                 = null,
    var buttonText: String?              = null,
    var customLink: String?              = null,
    var amount: String?              = null,
    var views: Int?                 = null,
    var shares: Int?                 = null,
    var fbShares: Int?                 = null,
    var isBookmarked: Boolean?             = null,
    var isApplied: Boolean?             = null,
    var isOwner: Boolean?             = null,
    var updatedOn: String?              = null,
    var whatsappNo: String?              = null,
//    var contactPreference: ContactPreferenceEntity?   = null,
    var createdOn: String?              = null,
    var isPremium: Boolean?             = null,
//    var creatives: List<CreativesEntity> = arrayListOf(),
//    var videos: List<String> = arrayListOf(),
//    var locations: List<LocationsEntity> = arrayListOf(),
//    var tags: List<String> = arrayListOf(),
//    var contentV3: ContentV3Entity?           = null,
    var status: Int?                 = null,
    var expireOn: String?              = null,
    var jobHours: String?              = null,
    var openingsCount: Int?                 = null,
    var jobRole: String?              = null,
    var otherDetails: String?              = null,
    var jobCategory: String?              = null,
    var numApplications: Int?                 = null,
    var enableLeadCollection: Boolean?             = null,
    var isJobSeekerProfileMandatory: Boolean?             = null,
    var jobLocationSlug: String?              = null,
    var feesText: String?              = null,
    var questionBankId: String?              = null,
    var screeningRetry: String?              = null,
    var shouldShowLastContacted: Boolean?             = null,
    var feesCharged: Int?                 = null
) {
    fun toJobEntity(): JobEntity {
        return JobEntity(
            id = id,
            title = title,
            type = type,
            primaryDetails = primaryDetails,
//            feeDetails = feeDetails,
//            jobTags = jobTags,
            jobType = jobType,
            jobCategoryId = jobCategoryId,
            qualification = qualification,
            experience = experience,
            shiftTiming = shiftTiming,
            jobRoleId = jobRoleId,
            salaryMax = salaryMax,
            salaryMin = salaryMin,
            cityLocation = cityLocation,
            locality = locality,
            premiumTill = premiumTill,
            content = content,
            companyName = companyName,
            advertiser = advertiser,
            buttonText = buttonText,
            customLink = customLink,
            amount = amount,
            views = views,
            shares = shares,
            fbShares = fbShares,
            isBookmarked = isBookmarked,
            isApplied = isApplied,
            isOwner = isOwner,
            updatedOn = updatedOn,
            whatsappNo = whatsappNo,
//            contactPreference = contactPreference,
            createdOn = createdOn,
            isPremium = isPremium,
//            creatives = creatives,
//            videos = videos,
//            locations = locations,
//            tags = tags,
//            contentV3 = contentV3,
            status = status,
            expireOn = expireOn,
            jobHours = jobHours,
            openingsCount = openingsCount,
            jobRole = jobRole,
            otherDetails = otherDetails,
            jobCategory = jobCategory,
            numApplications = numApplications,
            enableLeadCollection = enableLeadCollection,
            isJobSeekerProfileMandatory = isJobSeekerProfileMandatory,
            jobLocationSlug = jobLocationSlug,
            feesText = feesText,
            questionBankId = questionBankId,
            screeningRetry = screeningRetry,
            shouldShowLastContacted = shouldShowLastContacted,
            feesCharged = feesCharged
        )
    }
}
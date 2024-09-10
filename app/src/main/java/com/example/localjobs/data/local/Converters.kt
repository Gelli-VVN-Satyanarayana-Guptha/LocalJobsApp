package com.example.localjobs.data.local

import androidx.room.TypeConverter
import com.example.localjobs.data.local.jobs.entity.PrimaryDetailsEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Json.encodeToString(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Json.decodeFromString<List<String>>(value)

    @TypeConverter
    fun fromPrimaryDetailsEntityToString(value: PrimaryDetailsEntity) : String = Json.encodeToString(value)

    @TypeConverter
    fun fromStringToPrimaryDetailsEntity(value: String) : PrimaryDetailsEntity = Json.decodeFromString(value)
}
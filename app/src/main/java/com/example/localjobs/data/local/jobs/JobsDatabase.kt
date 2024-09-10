package com.example.localjobs.data.local.jobs

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.localjobs.data.local.Converters
import com.example.localjobs.data.local.jobs.entity.JobEntity

@Database(
    entities = [
        JobEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class JobsDatabase : RoomDatabase() {
    abstract val dao : JobsDao
}
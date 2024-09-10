package com.example.localjobs.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.localjobs.data.local.jobs.JobsDatabase
import com.example.localjobs.data.local.jobs.JobsRepositoryImpl
import com.example.localjobs.data.local.jobs.PreferencesDataStore
import com.example.localjobs.data.local.jobs.entity.JobEntity
import com.example.localjobs.data.remote.jobs.JobsApi
import com.example.localjobs.data.remote.jobs.JobsRemoteMediator
import com.example.localjobs.domain.jobs.repository.JobsRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KotlinxSerializationFactory

@Module
@InstallIn( SingletonComponent::class )
object AppModule {

    @Provides
    @Singleton
    fun provideJobsDatabase(@ApplicationContext context: Context) : JobsDatabase {
        return Room.databaseBuilder(
            context,
            JobsDatabase::class.java,
            "jobs.db"
        ).allowMainThreadQueries().build()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideJobsPager(
        jobsDb : JobsDatabase,
        jobsApi: JobsApi
    ) : Pager<Int, JobEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                prefetchDistance = 0
            ),
            remoteMediator = JobsRemoteMediator(
                jobDb = jobsDb,
                jobApi = jobsApi
            ),
            pagingSourceFactory = {
                jobsDb.dao.pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun provideJobsRepository(jobsDb: JobsDatabase) : JobsRepository {
        return JobsRepositoryImpl(jobsDb.dao)
    }

    @Provides
    @Singleton
    fun provideJobsApi (
        @KotlinxSerializationFactory kotlinxSerializationFactory: Converter.Factory
    ) : JobsApi {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val newRequest: Request = chain.request().newBuilder().build()
                chain.proceed(newRequest)
            }).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(JobsApi.BASE_URL)
            .addConverterFactory(kotlinxSerializationFactory)
            .build()
            .create(JobsApi::class.java)
    }

    @Provides
    @Singleton
    @KotlinxSerializationFactory
    fun provideKotlinxSerializationFactory() : Converter.Factory {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        return json.asConverterFactory(contentType)
    }
}
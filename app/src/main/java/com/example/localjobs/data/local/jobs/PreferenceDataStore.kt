package com.example.localjobs.data.local.jobs

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class PreferencesDataStore(context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "page")
    private var pref = context.dataStore

    companion object {
        var page = 1
    }

    suspend fun increasePage(){
        pref.edit {
            page += 1
        }
    }

    fun getPage() = pref.data.map {
        page
    }
}
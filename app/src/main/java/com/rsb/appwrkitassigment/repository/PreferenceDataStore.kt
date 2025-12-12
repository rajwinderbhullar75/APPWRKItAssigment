package com.rsb.appwrkitassigment.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rsb.appwrkitassigment.model.ListItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

class PreferenceDataStore(private val context: Context) {

    private val gson = Gson()
    private val listDataKey = stringPreferencesKey("list_data")

    val listFlow: Flow<List<ListItemModel>> = context.dataStore.data
        .map { preferences ->
            val json = preferences[listDataKey]
            if (json == null) {
                emptyList<ListItemModel>()
            } else {
                val listType: Type = object : TypeToken<List<ListItemModel>>() {}.type
                gson.fromJson(json, listType)
            }
        }

    suspend fun saveList(list: List<ListItemModel>) {
        context.dataStore.edit { preferences ->
            val json = gson.toJson(list)
            preferences[listDataKey] = json
        }
    }

    suspend fun updateItem(updatedItem: ListItemModel) {
        context.dataStore.edit { preferences ->
            val currentJson = preferences[listDataKey]
            if (currentJson != null) {
                val listType: Type = object : TypeToken<List<ListItemModel>>() {}.type
                val currentList: MutableList<ListItemModel> = gson.fromJson(currentJson, listType)

                val index = currentList.indexOfFirst { it.id == updatedItem.id }
                if (index != -1) {
                    currentList[index] = updatedItem
                    val updatedJson = gson.toJson(currentList)
                    preferences[listDataKey] = updatedJson
                }
            }
        }
    }
}

package com.example.thrivein.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.thrivein.data.model.StoreModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataUserStoreStore: DataStore<Preferences> by preferencesDataStore(name = "store")

@Singleton
class StorePreference @Inject constructor(private val dataUserStoreStore: DataStore<Preferences>) {
    fun getStore(): Flow<StoreModel> {
        return dataUserStoreStore.data.map { preferences ->
            StoreModel(
                storeEmail = preferences[EMAIL_KEY] ?: "",
                storeName = preferences[NAME_KEY] ?: "",
                storePhone = preferences[PHONE_KEY] ?: "",
                type = preferences[TYPE_KEY] ?: "",
                address = preferences[ADDRESS_KEY] ?: "",
            )
        }
    }

    suspend fun saveStore(storeModel: StoreModel) {
        dataUserStoreStore.edit { preferences ->
            preferences[NAME_KEY] = storeModel.storeName
            preferences[EMAIL_KEY] = storeModel.storeEmail
            preferences[PHONE_KEY] = storeModel.storePhone
            preferences[TYPE_KEY] = storeModel.type
            preferences[ADDRESS_KEY] = storeModel.address
        }
    }

    suspend fun clearStore() {
        dataUserStoreStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {

        private val NAME_KEY = stringPreferencesKey("store_name")
        private val EMAIL_KEY = stringPreferencesKey("store_email")
        private val PHONE_KEY = stringPreferencesKey("store_phone")
        private val TYPE_KEY = stringPreferencesKey("type")
        private val ADDRESS_KEY = stringPreferencesKey("address")

    }
}
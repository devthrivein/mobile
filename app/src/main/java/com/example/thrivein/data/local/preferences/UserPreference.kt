package com.example.thrivein.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.thrivein.data.local.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataUserStore: DataStore<Preferences> by preferencesDataStore(name = "user")

@Singleton
class UserPreference @Inject constructor(private val dataUserStore: DataStore<Preferences>) {

    fun getUser(): Flow<UserModel> {
        return dataUserStore.data.map { preferences ->
            UserModel(
                userId = preferences[ID_KEY] ?: "",
                token = preferences[TOKEN_KEY] ?: "",
                email = preferences[EMAIL_KEY] ?: "",
                name = preferences[NAME_KEY] ?: "",
                phone = preferences[PHONE_KEY] ?: "",
                avatarUrl = preferences[AVATAR_KEY] ?: "",
            )
        }
    }

    suspend fun saveUser(user: UserModel) {
        dataUserStore.edit { preferences ->
            preferences[ID_KEY] = user.userId ?: ""
            preferences[NAME_KEY] = user.name
            preferences[TOKEN_KEY] = user.token ?: ""
            preferences[EMAIL_KEY] = user.email
            preferences[PHONE_KEY] = user.phone
            preferences[AVATAR_KEY] = user.email
        }
    }

    suspend fun logout() {
        dataUserStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {

        private val ID_KEY = stringPreferencesKey("userId")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PHONE_KEY = stringPreferencesKey("phone")
        private val AVATAR_KEY = stringPreferencesKey("avatarUrl")

    }

}
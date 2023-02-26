package dev.vladimir.session.data.storage

import android.content.SharedPreferences
import javax.inject.Inject

private const val SESSION_ID = "session_id"

class SessionStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {

    fun getSessionId(): String? = sharedPreferences.getString(SESSION_ID, null)

    fun saveSessionId(sessionId: String) {
        sharedPreferences.edit().putString(SESSION_ID, sessionId).apply()
    }
}
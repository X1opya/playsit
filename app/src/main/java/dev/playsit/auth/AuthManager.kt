package dev.playsit.auth

import android.content.Context

class AuthManager(val context: Context): LogoutListener {
    private val preferences by lazy {
        context.getSharedPreferences(
            PREF_KEY_AUTH,
            Context.MODE_PRIVATE
        )
    }
    var token
        get() = preferences.getString("token", null)
        set(value) {
            preferences.edit().putString("token", value).apply()
        }
    val bearerToken = "Bearer $token"

    override fun logout() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val PREF_KEY_AUTH = "auth"
    }
}

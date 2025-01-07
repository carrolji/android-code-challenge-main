package life.league.challenge.kotlin.main

import android.content.Context
import android.content.SharedPreferences
import life.league.challenge.kotlin.R

/**
 * Session manager to save and fetch token from SharedPreferences
 */
class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun saveAuthToken(token: String) = prefs.edit().putString(ACCESS_TOKEN, token).apply()

    fun fetchAuthToken(): String? = prefs.getString(ACCESS_TOKEN, null)

    companion object {
        const val ACCESS_TOKEN = "access_token"
    }
}
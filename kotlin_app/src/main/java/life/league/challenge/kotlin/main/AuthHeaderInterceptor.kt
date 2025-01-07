package life.league.challenge.kotlin.main

import okhttp3.Interceptor
import okhttp3.Response

class AuthHeaderInterceptor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        sessionManager.fetchAuthToken()?.let {
            builder.addHeader("x-access-token", it)
        }

        return chain.proceed(builder.build())
    }
}
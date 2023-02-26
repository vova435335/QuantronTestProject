package dev.vladimir.core.data.interceptor

import dev.vladimir.session.data.storage.SessionStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val SESSION_PARAM = "session_id"

class SessionInterceptor @Inject constructor(
    private val sessionStorage: SessionStorage,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val sessionId = sessionStorage.getSessionId()

        return if (sessionId != null) {
            val url = request.url()
                .newBuilder()
                .addQueryParameter(SESSION_PARAM, sessionId)
                .build()
            chain.proceed(request.newBuilder().url(url).build())
        } else {
            chain.proceed(request)
        }
    }
}

package dev.vladimir.core.data.interceptor

import dev.vladimir.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY_PARAM = "api_key"

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url()
            .newBuilder()
            .addQueryParameter(API_KEY_PARAM, BuildConfig.API_KEY)
            .build()
        return chain.proceed(request.newBuilder().url(url).build())
    }
}
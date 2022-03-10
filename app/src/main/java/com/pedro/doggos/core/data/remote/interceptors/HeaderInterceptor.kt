package com.pedro.doggos.core.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 *  An [Interceptor] that adds the necessary headers for requests to the Dog api.
 *
 *  Headers:
 *   - Cache-Control
 *   - Accept
 *   - x-api-key
 */

class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        builder.addHeader("Cache-Control", "no")
        builder.addHeader("Accept", "application/json")
        builder.addHeader("x-api-key",  "")

        return chain.proceed(builder.build())
    }
}
package com.example.unsplashapp

import okhttp3.Interceptor
import okhttp3.Response

class AuthorisationInterceptor : Interceptor {
    var token: String = "563492ad6f91700001000001c07eda46b8bf46e8a7beb74e140cc6dd";

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            if (!token.isNullOrEmpty()) {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }
        }
        return chain.proceed(request)
    }
}
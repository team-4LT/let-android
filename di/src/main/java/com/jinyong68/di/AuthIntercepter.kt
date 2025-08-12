package com.jinyong68.di

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import com.jinyong68.data.TokenManager
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    companion object {
        private const val TAG = "AuthInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        if (chain.request().headers["Auth"] == "false") {
            val newRequest = chain.request().newBuilder().removeHeader("Auth").build()
            return chain.proceed(newRequest)
        }

        var token: String = runBlocking {
            Log.d("auth", "at : ${tokenManager.getAccessToken().first()}")
            Log.d("auth", "rt : ${tokenManager.getRefreshToken().first()}")
            Log.d("auth", "ia : ${tokenManager.getAutologin().first()}")
            tokenManager.getAccessToken().first()
        }

        if (token.isNotEmpty())
            token = "Bearer $token"

        val request =
            chain.request().newBuilder().addHeader("Authorization", token).build()

        val response = chain.proceed(request)
        if (response.code == HTTP_OK) {
            val newAccessToken: String = response.header("Authorization", null) ?: return response
            Log.d(TAG, "new Access Token = $newAccessToken")

            CoroutineScope(Dispatchers.IO).launch {
                val existedAccessToken: String =
                    runBlocking { tokenManager.getAccessToken().first() }
                if (existedAccessToken != newAccessToken) {
                    tokenManager.saveAccessToken(newAccessToken)
                    Log.d(
                        TAG,
                        "newAccessToken = ${newAccessToken}\nExistedAccessToken = $existedAccessToken"
                    )
                }
            }
        } else {
            Log.e(TAG, "${response.code} : ${response.request} \n ${response.message}")
        }

        return response
    }
}
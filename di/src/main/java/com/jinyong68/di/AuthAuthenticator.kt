package com.jinyong68.di

import android.util.Log
import com.jinyong68.data.TokenManager
import com.jinyong68.di.Constant.BASE_URL
import com.jinyong68.network.account.AccountApi
import com.jinyong68.network.dto.ResponseDto
import com.jinyong68.network.dto.Token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
) : Authenticator {


    companion object {
        private const val TAG = "AuthAuthenticator"
        var expiredRefreshToken: MutableStateFlow<Boolean> = MutableStateFlow(false)
    }


    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking { tokenManager.getRefreshToken().first() }
        return runBlocking {
            val reissueToken = reissueToken(refreshToken)

            if (!reissueToken.isSuccessful || reissueToken.body() == null) {
                Log.d(TAG, "Refresh Token 만료")
                tokenManager.deleteData()
                expiredRefreshToken.value = true

                return@runBlocking null
            }

            reissueToken.body()?.let {
                tokenManager.saveAccessToken(it.data.accessToken)
                tokenManager.saveRefreshToken(it.data.refreshToken)

                val token = "Bearer ${it.data.accessToken}"

                response.request.newBuilder()
                    .header("Authorization", token)
                    .build()
            }
        }
    }

    private suspend fun reissueToken(
        refreshToken: String,
    ): retrofit2.Response<ResponseDto<Token>> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(AccountApi::class.java)
        return service.refresh("Bearer $refreshToken")
    }
}
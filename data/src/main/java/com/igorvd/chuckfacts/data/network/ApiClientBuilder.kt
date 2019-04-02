package com.igorvd.chuckfacts.data.network

import com.google.gson.Gson
import com.igorvd.chuckfacts.data.network.interceptor.getSimpleLogging
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import com.igorvd.chuckfacts.domain.BuildConfig

/**
 * Helper class that creates retrofit clients
 * @author Igor Vilela
 * @since 12/10/17
 */
class ApiClientBuilder {

    companion object {

        fun <S> createService(
                serviceClass: Class<S>,
                baseUrl: String,
                gson: Gson = Gson(),
                vararg interceptors: Interceptor
        ): S {

            val httpClientBuilder = OkHttpClient.Builder()

            interceptors.forEach { interceptor -> httpClientBuilder.addInterceptor(interceptor) }

            if (BuildConfig.DEBUG) {

                // Critical part, LogClient must be last one if you have more interceptors
                httpClientBuilder.addInterceptor(HttpLoggingInterceptor().getSimpleLogging())

            }

            val client = httpClientBuilder
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build()
            val retrofit = getClientBuilder(baseUrl, gson)
                    .client(client)
                    .build()
            return retrofit.create(serviceClass)
        }

        private fun getClientBuilder(baseUrl: String, gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
        }
    }

}
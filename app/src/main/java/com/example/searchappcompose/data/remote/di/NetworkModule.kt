package com.example.searchappcompose.data.remote.di

import com.example.searchappcompose.BuildConfig
import com.example.searchappcompose.data.remote.consts.Constants
import com.example.searchappcompose.data.remote.service.WebSearchApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()

//        okHttpBuilder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
//        okHttpBuilder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        okHttpBuilder.addInterceptor(Interceptor { chain ->

            val original = chain.request().newBuilder().apply {
                header(Constants.X_RAPIDAPI_KEY, BuildConfig.X_RAPIDAPI_KEY_SECRET)
                header(Constants.X_RAPIDAPI_HOST, BuildConfig.X_RAPIDAPI_HOST_SECRET)
            }

            chain.proceed(original.build())
        })

        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideWebSearchApi(okHttpClient: OkHttpClient): WebSearchApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
            .create()
    }
}
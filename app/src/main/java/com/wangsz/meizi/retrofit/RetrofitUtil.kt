package com.wangsz.meizi.retrofit

import android.util.Log
import com.wangsz.meizi.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * author: wangsz
 * date: On 2018/7/4 0004
 */
object RetrofitUtil {

    val CONNECT_TIME_OUT = 30//连接超时时长x秒
    val READ_TIME_OUT = 30//读数据超时时长x秒
    val WRITE_TIME_OUT = 30//写数据接超时时长x秒

    val retrofit: Retrofit by lazy {
        Log.d("RetrofitUtil", "retrofit init lazy")
        Retrofit.Builder()
                .baseUrl("http://gank.io/api/")   //本文以GitHub API为例
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        } else {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
        }
        // 设置请求头
        builder.addInterceptor { chain ->
            val time = (System.currentTimeMillis() / 1000).toString() + ""
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader("time", time)
            chain.proceed(requestBuilder.build())
        }
        return builder.build()
    }
}
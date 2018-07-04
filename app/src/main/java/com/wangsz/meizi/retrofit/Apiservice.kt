package com.wangsz.meizi.retrofit

import com.wangsz.meizi.model.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author: wangsz
 * date: On 2018/7/4 0004
 */
interface Apiservice {

    @GET("data/{type}/10/{page}")
    fun getGank(@Path("type") type: String, @Path("page") page: Int): Observable<Response<List<Result>>>
}
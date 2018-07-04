package com.wangsz.meizi.retrofit

import android.util.Log
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function

/**
 * http结果处理函数
 */
class ExceptionFunction<T> : Function<Throwable, Observable<T>> {
    override fun apply(@NonNull throwable: Throwable): Observable<T> {
        Log.e("ExceptionFunction", throwable.message)
        return Observable.error(ExceptionEngine().handleException(throwable))
    }
}

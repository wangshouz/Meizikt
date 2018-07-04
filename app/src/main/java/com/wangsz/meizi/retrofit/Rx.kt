package com.wangsz.meizi.retrofit

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author: wangsz
 * date: On 2018/7/4 0004
 */
object Rx {

    /**
     * Rxlifecycle绑定生命周期
     */
    fun <T, E> get(observable: Observable<Response<T>>, lifecycleProvider: LifecycleProvider<E>): Observable<T> {

        // 请求绑定生命周期，防止内存泄漏，同时返回回调之后页面已销毁造成的空指针错误
        if (lifecycleProvider is RxAppCompatActivity) {
            val rxAppCompatActivity = lifecycleProvider as RxAppCompatActivity
            observable.compose(rxAppCompatActivity.bindUntilEvent(ActivityEvent.DESTROY))
        } else if (lifecycleProvider is RxFragment) {
            val rxFragment = lifecycleProvider as RxFragment
            observable.compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY))
        }

        return observable
                .compose(HandleResult())
                .onErrorResumeNext(ExceptionFunction())
    }

    /**
     * 部分后台请求
     */
    fun <T> get(observable: Observable<Response<T>>): Observable<T> {

        return observable
                .compose(HandleResult())
                .onErrorResumeNext(ExceptionFunction())
    }

    private class HandleResult<T> : ObservableTransformer<Response<T>, T> {
        override fun apply(upstream: Observable<Response<T>>): ObservableSource<T> {
            return upstream.flatMap { response -> createResult(response) }
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun <T> createResult(response: Response<T>): Observable<T> {
        return Observable.create({ subscriber ->
            if (response.error)
                throw ApiException(-1, "服务器异常") // 一般来说，自己的服务器异常会返回相应的code和message
            else
                response.results?.let {
                    subscriber.onNext(response.results!!)
                } ?: subscriber.onComplete()
        })
    }

}
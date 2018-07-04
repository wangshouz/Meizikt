package com.wangsz.meizi.retrofit

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 数据返回处理
 */
abstract class HttpObserver<T> : Observer<T> {

    /**
     * 标记是否为特殊情况
     */
    private var resultNull: Boolean = true

    override fun onComplete() {
        // 特殊情况：当请求成功，但T == null时会跳过onNext，仍需当成功处理
        if (resultNull)
            onSuccess(null)
    }

    override fun onSubscribe(d: Disposable) {
        // 可在此处加上dialog
    }

    override fun onError(e: Throwable) {
        if (e is ApiException) {
            onError(e.code, e.msg)
        } else {
            onError(0, e.message)
        }
    }

    override fun onNext(t: T) {
        resultNull = false
        onSuccess(t)
    }

    abstract fun onSuccess(t: T?)

    /**
     * 统一处理失败，比如登录失效等
     *
     * @param code
     * @param msg
     */
    fun onError(code: Int, msg: String?) {

    }

}

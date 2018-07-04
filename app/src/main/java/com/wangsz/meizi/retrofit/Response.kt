package com.wangsz.meizi.retrofit

/**
 * 返回数据
 * author: wangsz
 * date: On 2018/7/4 0004
 */
class Response<T> {

    /**
     * 返回数据格式
     * {
     *  "error": false,
     *   "results":...
     * }
     */


    var error: Boolean = false
    var results: T? = null
}
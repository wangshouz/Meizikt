package com.wangsz.meizi.model

/**
 * author: wangsz
 * date: On 2018/7/4 0004
 */
class Result {

    /**
     * _id : 5b39d2b4421aa906dfdf9f95
     * createdAt : 2018-07-02T15:22:28.882Z
     * desc : 陀螺仪滑动图片,实现VR看房
     * images : ["http://img.gank.io/97cab7b8-5ab1-4eb7-ba8c-0dc1471eb656","http://img.gank.io/560da8fa-6225-4e6e-8d2f-1833da08326b"]
     * publishedAt : 2018-07-03T00:00:00.0Z
     * source : chrome
     * type : Android
     * url : https://github.com/JY39/GyroscopeImageDemo
     * used : true
     * who : 艾米
     */

    var _id: String? = null
    var createdAt: String? = null
    var desc: String? = null
    var publishedAt: String? = null
    var source: String? = null
    var type: String? = null
    var url: String? = null
    var isUsed: Boolean = false
    var who: String? = null
    var images: List<String>? = null

    /**
     * 妹子小图
     */
    fun meiziSmallUrl(): String {
        return url?.replace("large", "small") ?: ""
    }
}

package com.wangsz.meizi.ui

import android.os.Bundle
import com.wangsz.meizi.R
import com.wangsz.meizi.imageloader.ImageLoader
import com.wangsz.meizi.ui.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_photo_view.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class PhotoViewActivity : BaseActivity() {

    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        intent?.let {
            url = intent.getStringExtra("url")
            ImageLoader.load(mContext, url, photo_view)
        }

        photo_view.setOnClickListener({ finish() })

        photo_view.setOnLongClickListener {
            Observable.create<String>(object : ObservableOnSubscribe<String> {
                override fun subscribe(emitter: ObservableEmitter<String>) {

                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<String> {
                        override fun onComplete() {
                        }

                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(t: String) {
                        }

                        override fun onError(e: Throwable) {
                        }

                    })
//            Toast.makeText(App.instance, "图片缓存在：" + Glide.getPhotoCacheDir(mContext)!!.absolutePath, Toast.LENGTH_SHORT).show()
            true
        }
    }


    /**
     * oldPath: 图片缓存的路径
     * newPath: 图片缓存copy的路径
     */
    private fun copyFile(oldPath: String, newPath: String) {
        try {
            var byteRead: Int
            val oldFile = File(oldPath)
            if (oldFile.exists()) {
                val inStream = FileInputStream(oldPath)
                val fs = FileOutputStream(newPath)
                val buffer = ByteArray(1024)
                byteRead = inStream.read(buffer)
                while (byteRead != -1) {
                    fs.write(buffer, 0, byteRead)
                }
                inStream.close()
            }
        } catch (e: Exception) {
            println("复制文件操作出错")
            e.printStackTrace()
        }

    }
}

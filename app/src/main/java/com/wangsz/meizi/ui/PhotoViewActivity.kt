package com.wangsz.meizi.ui

import android.Manifest
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wangsz.meizi.App
import com.wangsz.meizi.R
import com.wangsz.meizi.imageloader.ImageLoader
import com.wangsz.meizi.ui.base.BaseActivity
import com.wangsz.meizi.util.PictureUtil
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
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        intent?.let {
            url = intent.getStringExtra("url")
            ImageLoader.load(mContext, url, photo_view)
        }

        photo_view.setOnClickListener { finish() }

        photo_view.setOnLongClickListener {
            AlertDialog.Builder(mContext)
                    .setMessage("把妹子搞到手机上？")
                    .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }.setPositiveButton(android.R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                        RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe { granted ->
                                    downloadImage(granted)
                                }
                    }.show()
            true
        }
    }

    /**
     * 当请求权限失败时保持至私有文件夹里，一般来说不会被系统展示
     */
    private fun downloadImage(granted: Boolean) {
        Glide.with(mContext).asBitmap().load(url).into<SimpleTarget<Bitmap>>(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                Observable.create(ObservableOnSubscribe<String> { emitter ->
                    if (granted)
                        emitter.onNext(PictureUtil.saveImageToGallery(mContext, resource, null, null, granted))
                    else
                        emitter.onNext(PictureUtil.saveImageToGallery(mContext, resource, null, null, granted))
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<String> {
                            override fun onComplete() {
                            }

                            override fun onSubscribe(d: Disposable) {
                                disposable = d
                            }

                            override fun onNext(s: String) {
                                if (!granted && !s.isEmpty()) Toast.makeText(App.instance, "捕捉成功至:$s", Toast.LENGTH_SHORT).show()
                                else if (!s.isEmpty()) Toast.makeText(App.instance, "捕捉成功", Toast.LENGTH_SHORT).show()
                                disposable?.dispose()
                            }

                            override fun onError(e: Throwable) {
                            }

                        })
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}

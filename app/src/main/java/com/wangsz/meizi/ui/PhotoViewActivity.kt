package com.wangsz.meizi.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.wangsz.meizi.R
import com.wangsz.meizi.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_photo_view.*

class PhotoViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        intent?.let {
            if (intent.hasExtra("gif") && intent.getBooleanExtra("gif", false)) {
                Glide.with(mContext).load(intent.getStringExtra("url"))
                        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                        .into(photo_view)
            } else {
                Glide.with(mContext).load(intent.getStringExtra("url"))
                        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                        .into(photo_view)
            }
        }

        photo_view.setOnClickListener({ finish() })
    }
}

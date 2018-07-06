package com.wangsz.meizi.ui

import android.os.Bundle
import com.wangsz.meizi.R
import com.wangsz.meizi.imageloader.ImageLoader
import com.wangsz.meizi.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_photo_view.*

class PhotoViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        intent?.let {
            ImageLoader.load(mContext, intent.getStringExtra("url"), photo_view)
        }

        photo_view.setOnClickListener({ finish() })
    }
}

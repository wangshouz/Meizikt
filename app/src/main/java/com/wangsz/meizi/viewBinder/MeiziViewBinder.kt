package com.wangsz.meizi.viewBinder

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.wangsz.meizi.R
import com.wangsz.meizi.model.Result
import com.wangsz.meizi.ui.PhotoViewActivity
import com.wangsz.meizi.util.ScreenUtil
import me.drakeet.multitype.ItemViewBinder


class MeiziViewBinder(var context: Context) : ItemViewBinder<Result, MeiziViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_meizi_view, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: MeiziViewBinder.ViewHolder, item: Result) {

        holder.ivMeizi.layoutParams.width = ScreenUtil.widthInt(0.5f) - ScreenUtil.dip2px(20)
        holder.ivMeizi.layoutParams.height = (holder.ivMeizi.layoutParams.width * 1.3).toInt()

        Glide.with(context).load(item.meiziSmallUrl())
                .apply(RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate())
                .into(holder.ivMeizi)

        holder.ivMeizi.setOnClickListener({
            val intent = Intent(context, PhotoViewActivity::class.java)
            intent.putExtra("url", item.url)
            context.startActivity(intent)
        })

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivMeizi: ImageView by lazy {
            itemView.findViewById<ImageView>(R.id.iv_meizi)
        }
    }

}

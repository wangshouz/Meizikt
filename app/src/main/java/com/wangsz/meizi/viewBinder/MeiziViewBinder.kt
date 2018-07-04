package com.wangsz.meizi.viewBinder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.wangsz.meizi.R
import com.wangsz.meizi.model.Result

import me.drakeet.multitype.ItemViewBinder

class MeiziViewBinder(var context: Context) : ItemViewBinder<Result, MeiziViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_meizi_view, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: MeiziViewBinder.ViewHolder, item: Result) {
        Glide.with(context).load(item.url).into(holder.ivMeizi)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMeizi: ImageView by lazy {
            itemView.findViewById<ImageView>(R.id.iv_meizi)
        }
    }
}

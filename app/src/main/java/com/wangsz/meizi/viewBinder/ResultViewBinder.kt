package com.wangsz.meizi.viewBinder

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jaeger.ninegridimageview.NineGridImageView
import com.jaeger.ninegridimageview.NineGridImageViewAdapter
import com.wangsz.meizi.R
import com.wangsz.meizi.imageloader.ImageLoader
import com.wangsz.meizi.model.Result
import com.wangsz.meizi.ui.PhotoViewActivity
import com.wangsz.meizi.ui.WebActivity
import com.wangsz.meizi.util.TextViewSetSpan
import me.drakeet.multitype.ItemViewBinder


class ResultViewBinder(var mContext: Context) : ItemViewBinder<Result, ResultViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_result_view, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ResultViewBinder.ViewHolder, item: Result) {

        holder.itemView.setOnClickListener({ WebActivity.startWeb(mContext, item.url) })
        holder.tvTitle.setOnClickListener({ WebActivity.startWeb(mContext, item.url) })

        val str = item.desc + "  (" + item.who + ")"
        val textViewSetSpan = TextViewSetSpan(holder.tvTitle, str)
        textViewSetSpan.setSizeAndColorSpan(item.desc!!.length, str.length, 12, mContext.resources.getColor(R.color.text_999)).show()

        if (item.images != null && item.images!!.isNotEmpty()) {
            holder.ngImages.setAdapter(object : NineGridImageViewAdapter<String>() {
                override fun onDisplayImage(context: Context?, imageView: ImageView?, t: String?) {
                    if (imageView != null) {
                        ImageLoader.load(mContext, t!!, imageView)
                    }
                }
            })
            holder.ngImages.setItemImageClickListener { context, imageView, index, list ->
                val intent = Intent(context, PhotoViewActivity::class.java)
                intent.putExtra("url", list[index])
                context.startActivity(intent)
            }
            holder.ngImages.setImagesData(item.images)
            holder.ngImages.visibility = View.VISIBLE
        } else {
            holder.ngImages.visibility = View.GONE
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_title)
        }

        val ngImages: NineGridImageView<String> by lazy {
            itemView.findViewById<NineGridImageView<String>>(R.id.ng_images)
        }
    }


}

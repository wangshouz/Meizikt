package com.wangsz.meizi.viewBinder

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.wangsz.meizi.R
import com.wangsz.meizi.model.Result
import com.wangsz.meizi.model.SizeModel
import me.drakeet.multitype.ItemViewBinder


class MeiziViewBinder(var context: Context) : ItemViewBinder<Result, MeiziViewBinder.ViewHolder>() {

    private var sizeModel: ArrayList<SizeModel> = arrayListOf()

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_meizi_view, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: MeiziViewBinder.ViewHolder, item: Result) {

        if (sizeModel.size <= holder.adapterPosition) {
            sizeModel.add(SizeModel())
        }
        if (sizeModel[holder.adapterPosition].url == item.meiziSmallUrl()) {
            setLayoutParams(holder.itemView, sizeModel[holder.adapterPosition].width, sizeModel[holder.adapterPosition].height)
        } else {
            sizeModel[holder.adapterPosition].url = item.meiziSmallUrl()
        }

        Glide.with(context).asBitmap().load(item.meiziSmallUrl())
                .apply(RequestOptions().centerCrop().format(DecodeFormat.PREFER_RGB_565))
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val viewWidth = holder.itemView.width
                        val scale = resource.width / (viewWidth * 1.0f)
                        val viewHeight = (resource.height * scale).toInt()
                        setLayoutParams(holder.itemView, viewWidth, viewHeight)
                        sizeModel[holder.adapterPosition].width = viewWidth
                        sizeModel[holder.adapterPosition].height = viewHeight
                        holder.ivMeizi.setImageBitmap(resource)
                    }
                })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMeizi: ImageView by lazy {
            itemView.findViewById<ImageView>(R.id.iv_meizi)
        }
    }

    private fun setLayoutParams(view: View, width: Int, height: Int) {
        val layoutParams = view.layoutParams
        layoutParams.width = width
        layoutParams.height = height
//        view.layoutParams = layoutParams
    }
}

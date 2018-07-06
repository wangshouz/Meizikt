package com.wangsz.meizi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wangsz.meizi.R
import com.wangsz.meizi.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_blog.setOnClickListener({ WebActivity.startWeb(mContext, "https://wangshouz.github.io/") })
        tv_github.setOnClickListener({ WebActivity.startWeb(mContext, "https://github.com/wangshouz/Meizikt") })
    }

    companion object {
        @JvmStatic
        fun newInstance() = AboutFragment().apply {}
    }
}

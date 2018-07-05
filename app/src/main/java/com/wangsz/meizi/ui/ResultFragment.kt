package com.wangsz.meizi.ui

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.wangsz.meizi.R
import com.wangsz.meizi.model.Result
import com.wangsz.meizi.retrofit.Api
import com.wangsz.meizi.retrofit.HttpObserver
import com.wangsz.meizi.retrofit.Rx
import com.wangsz.meizi.ui.base.BaseListFragment
import com.wangsz.meizi.viewBinder.MeiziViewBinder
import com.wangsz.meizi.viewBinder.ResultViewBinder
import kotlinx.android.synthetic.main.fragment_list.*

private const val ARG_TYPE = "arg_type"

class ResultFragment : BaseListFragment() {

    override fun initAdapterRegister() {
        when (type) {
            R.id.nav_meizi -> mMultiTypeAdapter.register(Result::class.java, MeiziViewBinder(mContext))
            else -> mMultiTypeAdapter.register(Result::class.java, ResultViewBinder(mContext))
        }
    }

    override fun loadData() {
        Rx.get(Api.apiservice.getGank(getType(), mIntPage), this).subscribe(object : HttpObserver<List<Result>>() {
            override fun onSuccess(t: List<Result>?) {
                getDataSuccess(t)
            }

            override fun onError(code: Int, msg: String?) {
                super.onError(code, msg)
                getDataFailed()
            }
        })
    }

    private var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getInt(ARG_TYPE, 0)
        }
        Log.d("ResultFragment", "onCreate = $type")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (type == R.id.nav_meizi)
            recycleView.layoutManager = GridLayoutManager(mContext, 2)
    }

    private fun getType(): String {
        return when (type) {
            R.id.nav_meizi -> "福利"
            R.id.nav_android -> "Android"
            R.id.nav_ios -> "iOS"
            R.id.nav_web -> "前端"
            R.id.nav_app -> "App"
            R.id.nav_recommend -> "瞎推荐"
            else -> ""
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(type: Int) =
                ResultFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_TYPE, type)
                    }
                }
    }

}

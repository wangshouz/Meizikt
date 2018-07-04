package com.wangsz.meizi.ui

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.wangsz.meizi.model.Result
import com.wangsz.meizi.retrofit.Api
import com.wangsz.meizi.retrofit.HttpObserver
import com.wangsz.meizi.retrofit.Rx
import com.wangsz.meizi.ui.base.BaseListFragment
import com.wangsz.meizi.viewBinder.MeiziViewBinder
import kotlinx.android.synthetic.main.fragment_list.*


class MeiziFragment : BaseListFragment() {

    override fun initAdapterRegister() {
        mMultiTypeAdapter.register(Result::class.java, MeiziViewBinder(mContext))
    }

    override fun loadData() {
        Rx.get(Api.apiservice.getGank("福利", mIntPage), this).subscribe(object : HttpObserver<List<Result>>() {
            override fun onSuccess(t: List<Result>?) {
                getDataSuccess(t)
            }

            override fun onError(code: Int, msg: String?) {
                super.onError(code, msg)
                getDataFailed()
            }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 瀑布流
        recycleView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    companion object {

        @JvmStatic
        fun newInstance() = MeiziFragment().apply {}
    }
}

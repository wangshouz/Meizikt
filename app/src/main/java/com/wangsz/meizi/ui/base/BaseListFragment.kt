package com.wangsz.meizi.ui.base

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.wangsz.meizi.R
import kotlinx.android.synthetic.main.fragment_list.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

/**
 * author: wangsz
 * date: On 2018/5/14 0014
 */
abstract class BaseListFragment : BaseFragment(), BGARefreshLayout.BGARefreshLayoutDelegate {

    lateinit var mMultiTypeAdapter: MultiTypeAdapter
    lateinit var mItems: Items
    var mIntPage = 1
    private var mBooleanMore = true

    abstract fun initAdapterRegister()
    abstract fun loadData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bga_refresh.setPullDownRefreshEnable(true)
        bga_refresh.setDelegate(this)
        bga_refresh.setRefreshViewHolder(BGANormalRefreshViewHolder(mContext, true))
        recycleView.layoutManager = LinearLayoutManager(mContext)
        mMultiTypeAdapter = MultiTypeAdapter()
        initAdapterRegister()
        mItems = Items()
        mMultiTypeAdapter.items = mItems
        recycleView.adapter = mMultiTypeAdapter
        // 默认直接开始加载
        bga_refresh.beginRefreshing()
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout) {
        mIntPage = 1
        loadData()
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout): Boolean {
        return if (mBooleanMore) {
            mIntPage++
            loadData()
            true
        } else {
            false
        }
    }

    /**
     * 获取数据成功
     */
    fun getDataSuccess(list: List<*>?) {

        mBooleanMore = list != null && !list.isEmpty()

        if (mIntPage == 1) {
            mItems.clear()
            list?.let {
                mItems.addAll(list)
            }
            bga_refresh.endRefreshing()
            mMultiTypeAdapter.notifyDataSetChanged()
        } else {
            bga_refresh.endLoadingMore()
            var size = mItems.size
            list?.let {
                mItems.addAll(list)
            }
            mMultiTypeAdapter.notifyItemRangeInserted(size, list?.size ?: 0)
        }
    }

    /**
     * 获取数据失败
     */
    fun getDataFailed() {

        bga_refresh.endRefreshing()
        bga_refresh.endLoadingMore()
    }

}

package com.wangsz.meizi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wangsz.meizi.R
import com.wangsz.meizi.ui.base.BaseFragment

class AboutFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AboutFragment().apply {}
    }
}

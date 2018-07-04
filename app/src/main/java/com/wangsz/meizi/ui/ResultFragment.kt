package com.wangsz.meizi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wangsz.meizi.R
import com.wangsz.meizi.ui.base.BaseFragment

private const val ARG_TYPE = "arg_type"

class ResultFragment : BaseFragment() {

    private var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getInt(ARG_TYPE, 0)
        }
        Log.d("ResultFragment", "onCreate = $type")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
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

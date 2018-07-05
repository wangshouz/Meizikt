package com.wangsz.meizi.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.wangsz.meizi.R
import com.wangsz.meizi.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var fragments: ArrayList<Fragment?> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.setCheckedItem(R.id.nav_meizi)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        hideFragments(fragmentTransaction)
        showFragment(fragmentTransaction, nav_view.checkedItem!!.title.toString(), nav_view.checkedItem!!.itemId)
        fragmentTransaction.commit()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        // 开启一个Fragment事务
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(fragmentTransaction)

        showFragment(fragmentTransaction, item.title.toString(), item.itemId)

        fragmentTransaction.commit()

        toolbar.title = item.title.toString()

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        for (f in fragments) {
            f?.let {
                transaction.hide(f)
            }
        }
    }

    private fun showFragment(transaction: FragmentTransaction, tag: String, type: Int) {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            transaction.show(fragment)
        } else {

            fragment = when (type) {
            //                R.id.nav_meizi -> fragment = MeiziFragment.newInstance()
                R.id.nav_about -> AboutFragment.newInstance()
                else -> ResultFragment.newInstance(type)
            }
            fragments.add(fragment)
            transaction.add(R.id.frameLayout, fragment, tag)
        }
    }
}

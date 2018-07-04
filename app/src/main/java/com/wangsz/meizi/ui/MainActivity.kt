package com.wangsz.meizi.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.wangsz.meizi.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var fragments: Array<Fragment?> = arrayOfNulls(6)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

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

            when (type) {
                R.id.nav_meizi -> fragment = MeiziFragment.newInstance()
                R.id.nav_about -> fragment = AboutFragment.newInstance()
                else -> fragment = ResultFragment.newInstance(type)
            }

            transaction.add(R.id.frameLayout, fragment, tag)
        }
    }
}

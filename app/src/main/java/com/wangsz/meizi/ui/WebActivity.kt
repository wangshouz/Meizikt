package com.wangsz.meizi.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.wangsz.meizi.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    lateinit var agentWeb: AgentWeb
    lateinit var webChromeClient: WebChromeClient
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.setOnMenuItemClickListener { p0 ->
            if (p0!!.itemId == R.id.menu_chrome) {
                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            false
        }

        url = intent?.let {
            intent.getStringExtra("url")
        } ?: ""

        webChromeClient = WebChromeClient()

        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(appBar.parent as ViewGroup, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(0xFF3F51B5.toInt())// 使用默认进度条
                .setWebChromeClient(object : WebChromeClient() {
                    override fun onReceivedTitle(view: WebView?, title: String?) {
                        super.onReceivedTitle(view, title)
                        toolbar.title = title
                    }
                })
                .createAgentWeb()
                .ready().go(url)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_web, menu)
        return true
    }

    override fun onResume() {
        agentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        agentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    companion object {

        fun startWeb(content: Context, url: String) {
            val intent = Intent(content, WebActivity::class.java)
            intent.putExtra("url", url)
            content.startActivity(intent)
        }

    }


}

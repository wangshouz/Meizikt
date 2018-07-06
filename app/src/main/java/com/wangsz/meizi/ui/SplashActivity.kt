package com.wangsz.meizi.ui

import android.content.Intent
import android.os.Bundle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.wangsz.meizi.ui.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(object : Observer<Long> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Long) {
                        var intent = Intent(mContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onError(e: Throwable) {

                    }

                })
    }
}

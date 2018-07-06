package com.wangsz.meizi.util

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.widget.TextView

import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * 设置textview 多种样式
 *
 *
 * new XTextViewSetSpan(mMyCircleSiteUsersItem.mTvName, mStrXqName)
 * .setForegroundColorSpan(start, end, MyColor.c).show();
 */
class TextViewSetSpan {

    private var sp: SpannableString? = null
    private var mTextView: TextView? = null

    private var mStr = ""
    private var mIntStrLong = 0


    /**
     * 设置textview 多种样式
     *
     * @param myTextView
     * @param str
     */
    constructor(myTextView: TextView, str: String) {

        this.mTextView = myTextView
        this.mStr = str
        //str = "这句话中有百度超链接,有高亮显示，这样，或者这样，还有斜体.";
        this.mIntStrLong = str.length
        // 创建一个 SpannableString对象
        this.sp = SpannableString(str)

    }

    /**
     * 设置textview 多种样式
     *
     * @param myTextView
     * @param str        str
     */
    constructor(myTextView: TextView, str: SpannableString) {

        this.mTextView = myTextView
        this.mStr = myTextView.text.toString()

        //str = "这句话中有百度超链接,有高亮显示，还有斜体，或者含有图片";
        this.mIntStrLong = str.length
        // 创建一个 SpannableString对象
        this.sp = str

    }

    /**
     * 设置高亮样式一	字体背景
     *
     * @param start
     * @param end
     */
    fun setBackgroundColorSpan(start: Int, end: Int, color: Int): TextViewSetSpan {
        if (mIntStrLong >= end && end > start)
            sp!!.setSpan(BackgroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     * 设置高亮样式一	字体背景
     *
     * @param startC
     * @param endC
     */
    fun setBackgroundColorSpan(startC: String, endC: String, color: Int): TextViewSetSpan {
        if (mStr.contains(startC) && mStr.contains(endC)) {
            val start = mStr.indexOf(startC)
            val end = mStr.indexOf(endC) + 1
            setBackgroundColorSpan(start, end, color)
        }
        return this
    }

    /**
     * 设置高亮样式二	字体本身
     *
     * @param start
     * @param end
     */
    fun setForegroundColorSpan(start: Int, end: Int, color: Int): TextViewSetSpan {
        if (mIntStrLong >= end && end > start)
            sp!!.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        return this
    }

    /**
     * 设置高亮样式二	字体本身
     *
     * @param startC
     * @param endC
     */
    fun setForegroundColorSpan(startC: String, endC: String, color: Int): TextViewSetSpan {
        if (mStr.contains(startC) && mStr.contains(endC)) {
            val start = mStr.indexOf(startC)
            val end = mStr.indexOf(endC) + 1
            setForegroundColorSpan(start, end, color)
        }
        return this
    }

    /**
     * 关键字高亮变色
     *
     * @param color   变化的色值
     * @param keyword 文字中的关键字
     * @return
     */
    fun matcherSearchTitle(keyword: String, color: Int): TextViewSetSpan {
        val p = Pattern.compile(keyword)
        val m = p.matcher(mStr)
        while (m.find()) {
            val start = m.start()
            val end = m.end()
            sp!!.setSpan(ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return this
    }


    /**
     * 设置斜体
     *
     * @param start
     * @param end
     */
    fun setStyleSpan(start: Int, end: Int): TextViewSetSpan {
        if (mIntStrLong >= end && end > start)
            sp!!.setSpan(StyleSpan(android.graphics.Typeface.BOLD_ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        return this
    }

    /**
     * 设置黑体
     *
     * @param start
     * @param end
     */
    fun setStyleSpanBold(start: Int, end: Int): TextViewSetSpan {
        if (mIntStrLong >= end && end > start)
            sp!!.setSpan(StyleSpan(android.graphics.Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        return this
    }

    /**
     * 设置斜体
     *
     * @param startC
     * @param endC
     */
    fun setStyleSpan(startC: String, endC: String): TextViewSetSpan {

        if (mStr.contains(startC) && mStr.contains(endC)) {
            val start = mStr.indexOf(startC)
            val end = mStr.indexOf(endC) + 1
            setStyleSpan(start, end)
        }
        return this
    }

    /**
     * 设置字体大小
     *
     * @param start
     * @param end
     * @param size
     * @return
     */
    fun setSizeSpan(start: Int, end: Int, size: Int): TextViewSetSpan {
        if (mIntStrLong >= end && end > start)
            sp!!.setSpan(AbsoluteSizeSpan(size, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    /**
     * 设置字体大小
     *
     * @param startC
     * @param endC
     * @param size
     * @return
     */
    fun setSizeSpan(startC: String, endC: String, size: Int): TextViewSetSpan {
        if (mStr.contains(startC) && mStr.contains(endC)) {
            val start = mStr.indexOf(startC)
            val end = mStr.indexOf(endC) + 1
            setSizeSpan(start, end, size)
        }
        return this
    }

    /**
     * 同时设置字体大小及颜色
     *
     * @param start
     * @param end
     * @param size
     * @param color
     * @return
     */
    fun setSizeAndColorSpan(start: Int, end: Int, size: Int, color: Int): TextViewSetSpan {
        if (mIntStrLong >= end && end > start) {
            sp!!.setSpan(AbsoluteSizeSpan(size, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            sp!!.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        return this
    }

    /**
     * SpannableString对象设置给TextView
     */
    fun show() {
        // SpannableString对象设置给TextView
        mTextView!!.text = sp
        // 设置TextView可点击
        mTextView!!.movementMethod = LinkMovementMethod.getInstance()
        sp = null
    }
}
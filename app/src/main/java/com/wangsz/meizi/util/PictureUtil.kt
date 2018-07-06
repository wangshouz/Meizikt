package com.wangsz.meizi.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.support.annotation.NonNull
import android.text.TextUtils
import com.wangsz.meizi.App
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


object PictureUtil {

    /**
     * 保存图片到内存卡并通知图库更新
     *
     * @param context 上下文
     * @param bitmap  要保存的图片
     * @param dir     保存图片的文件夹路径(可缺省)
     * @param imgName 文件名(可缺省)
     */
    fun saveImageToGallery(@NonNull context: Context, @NonNull bitmap: Bitmap, dir: String?, imgName: String?, granted: Boolean): String {

        val imgDir: File
        // 1.保存图片
        if (granted) {
            imgDir = getCorrectDir(dir)
        } else {
            imgDir = getCorrectDirNoPermission()
        }
        val fileName = getCorrectName(imgName)
        val file = File(imgDir, fileName)

        try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)   // 默认压缩为png格式
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }

        // 2.通知图库更新
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.absolutePath)))

        return file.absolutePath
    }


    /**
     * 获取正确的图片保存文件夹
     *
     * @param dir 文件夹
     * @return 文件夹的file对象
     */
    private fun getCorrectDir(dir: String?): File {
        var dir = dir
        // 如果没有指定dir 则默认为/storage/emulated/0/Picture/meizi
        if (TextUtils.isEmpty(dir)) {
            dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath + "/meizi"
        }

        val fileDir = File(dir)
        if (!fileDir.exists()) {
            fileDir.mkdir()
        }

        return fileDir
    }

    /**
     * 获取正确的图片保存文件夹
     *
     * @param dir 文件夹
     * @return 文件夹的file对象
     */
    fun getCorrectDirNoPermission(): File {
        var dir = App.instance.getExternalFilesDir(Environment.DIRECTORY_PICTURES).absolutePath

        val fileDir = File(dir)
        if (!fileDir.exists()) {
            fileDir.mkdir()
        }

        return fileDir
    }

    /**
     * 获取正确的文件名
     *
     * @param fileName 文件名
     * @param exName   扩展名
     * @return
     */
    private fun getCorrectName(fileName: String?): String {
        var fileName = fileName
        // 没有指定文件名则用当前时间作为文件名
        if (TextUtils.isEmpty(fileName)) {
            fileName = "gank_" + System.currentTimeMillis().toString()
        }

        return "$fileName.png"
    }

}
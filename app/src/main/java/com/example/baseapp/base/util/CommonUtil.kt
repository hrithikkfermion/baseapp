package com.example.baseapp.base.util

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat.JPEG
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.MediaStore
import android.text.method.PasswordTransformationMethod
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import com.example.baseapp.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CommonUtil {
    const val DATE_FORMAT_DDMMYYYY = "dd-MM-yyyy"
    const val DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy"

    class AsteriskPasswordTransformationMethod : PasswordTransformationMethod() {
        override fun getTransformation(source: CharSequence?, view: View?): CharSequence {
            return object : CharSequence {
                override val length: Int
                    get() = source?.length ?: 0

                override fun get(index: Int): Char {
                    return '*'
                }

                override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
                    return source?.subSequence(startIndex, endIndex) ?: ""
                }
            }
        }
    }

    fun Bitmaptobase64(bitmap: Bitmap): String? {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(JPEG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }

    fun convertBitmapToBase64(bitmap: Bitmap): String? {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }

    fun encodeImage(path: String): String? {
        val imagefile = File(path)
        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(imagefile)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val bm: Bitmap = BitmapFactory.decodeStream(fis)
        val baos = ByteArrayOutputStream()
        bm.compress(JPEG, 100, baos)
        val b = baos.toByteArray()
        //Base64.de
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun toastMessage(context: Context?, errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
    }

    fun getDateFromString(enteredTime: String?, format: String?): Date? {
        val sdf = SimpleDateFormat(format)
        try {
            return sdf.parse(enteredTime)
        } catch (e: ParseException) {
            e.message
        }
        return null
    }



    fun getStringFromDate(
        time: Date?,
        dateFormat: String?
    ): String? {
        val sdf = SimpleDateFormat(dateFormat)
        return sdf.format(time)
    }
    fun showLoadingDialog(context: Context?): ProgressDialog? {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_layout)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }



}
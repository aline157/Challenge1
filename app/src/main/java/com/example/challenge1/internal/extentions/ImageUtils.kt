package com.example.challenge1.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import android.R

import android.graphics.BitmapFactory
import android.util.Base64


fun Uri.toBitmap(context: Context):Bitmap{
        try {
            return if(Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(
                    context.contentResolver,
                    this
                )
            } else {
                val source =ImageDecoder.createSource(context.contentResolver, this)
                source.let { ImageDecoder.decodeBitmap(it) }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return BitmapFactory.decodeResource(
            context.resources,
            R.drawable.ic_delete)
    }
fun Bitmap.toBase64():String{
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    val imageBytes = baos.toByteArray()
    return android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT)
}

fun String.toBitmap():Bitmap{
    val imageBytes: ByteArray = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}


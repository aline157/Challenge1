package com.example.challenge1.internal

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import com.example.challenge1.data.db.entity.AccountModel
import com.example.challenge1.ui.toBitmap
import kotlin.collections.HashMap


fun Activity.addDataToPrefs(fields : HashMap<String,String>){
    val shared : SharedPreferences = getSharedPreferences("logindata" , Context.MODE_PRIVATE)

    val edit = shared.edit()
    for (field in fields)
        edit.putString(field.key,field.value)
    edit.apply()
}

fun Activity.verifyAccount(email : String, password: String): Boolean{
    val shared : SharedPreferences = getSharedPreferences("logindata" , Context.MODE_PRIVATE)
    val emailPref = shared.getString("email",null)
    val passwordPref = shared.getString("password",null)
    if(email == emailPref && password == passwordPref)
        return true
    return false
}
fun Activity.checkAccount(): Boolean{
    val shared : SharedPreferences = getSharedPreferences("logindata" , Context.MODE_PRIVATE)
    val email = shared.getString("email",null)
    val password = shared.getString("password",null)
    if(email != null && password != null)
        return true
    return false
}

fun Activity.getCurrentAccount(): AccountModel {
    val shared : SharedPreferences = getSharedPreferences("logindata" , Context.MODE_PRIVATE)
    return AccountModel(shared.getString("firstname",null),
        shared.getString("lastname",null),
        shared.getString("email",null),
        shared.getString("password",null),
        shared.getString("isSignIn",null) )
}

fun Activity.saveImage(imageString:String){
    val shared : SharedPreferences = getSharedPreferences("logindata" , Context.MODE_PRIVATE)

    val edit = shared.edit()
    edit.putString("image",imageString)
    edit.apply()

}

fun Activity.getImage(): Bitmap?{
    val shared : SharedPreferences = getSharedPreferences("logindata" , Context.MODE_PRIVATE)
    return when(val image = shared.getString("image",null)){
        null -> null
        else -> image.toBitmap()
    }
}
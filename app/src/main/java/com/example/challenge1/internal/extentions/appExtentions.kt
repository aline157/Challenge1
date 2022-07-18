package com.example.challenge1.internal

import android.view.View
import com.google.android.material.textfield.TextInputEditText


fun TextInputEditText.getTextTrimed() : String{
    return this.text.toString().trim()
}

//fun View.setVisibility(state: Boolean){
//    if (state){
//        this.visibility = View.VISIBLE
//    }else{
//        this.visibility = View.INVISIBLE
//    }
//}
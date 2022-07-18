package com.example.challenge1.internal.Utils

import java.util.regex.Pattern

object ValidationManager{

    init {

    }
    //check if email is valid
    fun isValidEmail(email: String?) : Boolean{
        val pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        val match = pattern.matcher(email)
        return match.matches()
    }

    //check if password is valid: contain at least 8 chars+at least 1 capital +speacial character
    fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        } ?: return false
    }

    //check if password field 1 and 2 match
    fun isPasswordMatch(password1: String,password2: String) : Boolean{
        return password1 == password2
    }

    //check if the remaining field has a length grater than 3
    fun isFieldValid(field: String) : Boolean {
        if (field.length >=3){
            return true
        }
        return false
    }


}
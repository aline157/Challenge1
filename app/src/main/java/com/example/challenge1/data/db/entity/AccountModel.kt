package com.example.challenge1.data.db.entity

class AccountModel(
    val firstName: String?,
    val LastName: String?,
    val email: String?,
    val password: String?,
    val isSignIn: String?
) {

    override fun toString(): String {
        return "AccountModel(firstName=$firstName, LastName=$LastName, email=$email, password=$password)"
    }
}
package com.example.challenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challenge1.databinding.ActivitySignUpBinding
import com.example.challenge1.internal.Utils.ValidationManager
import com.example.challenge1.internal.addDataToPrefs
import com.example.challenge1.internal.getTextTrimed
//import es.dmoral.toasty.Toasty

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding.signupBtn.setOnClickListener{
            if(checkAllFields()){
                val account:HashMap<String,String> = HashMap()
                account["firstname"] = binding.firstNameField.getTextTrimed()
                account["lastname"] = binding.lastNameField.getTextTrimed()
                account["email"] = binding.emailField.getTextTrimed()
                account["password"] = binding.passwordField.getTextTrimed()
                account["isSignIn"] = "false"
                addDataToPrefs(account)


//                Toasty.success(this, "Success! ${account["email"]}", Toast.LENGTH_SHORT, true).show()
                Toast.makeText(this,"Success! ${account["email"]}", Toast.LENGTH_SHORT).show()
                this.finish()
            }else{
//                Toasty.error(this, "Error! one of the field does not match input requirement", Toast.LENGTH_SHORT, true).show()
                Toast.makeText(this,"Error! one of the field does not match input requirement", Toast.LENGTH_SHORT).show()
            }

        }

    }
    //check all fields
    fun checkAllFields() : Boolean{
        val firstName = binding.firstNameField.getTextTrimed()
        val lastName = binding.lastNameField.getTextTrimed()
        val email = binding.emailField.getTextTrimed()
        val password = binding.passwordField.getTextTrimed()
        val passwordConf = binding.confirmPasswordField.getTextTrimed()

        var emailfield = false
        var passwordfield = false
        var matchpasswordfield = false
        var fnamefield = false
        var lnamefield = false

        if(ValidationManager.isValidEmail(email)){
            emailfield =true
        }else{
            binding.emailField.error = "Please enter a valid email"
        }
        if(ValidationManager.isValidPassword(password)){
            passwordfield =true
        }else{
            binding.passwordField.error = "Wrong format"
        }
        if(ValidationManager.isPasswordMatch(password,passwordConf)){
            matchpasswordfield =true
        }else{
            binding.confirmPasswordField.error = "Passwords does not match"
        }
        if(ValidationManager.isFieldValid(firstName)){
            fnamefield =true
        }else{
            binding.firstNameField.error = "Enter a valid name"
        }
        if(ValidationManager.isFieldValid(lastName)){
            lnamefield =true
        }else{
            binding.lastNameField.error = "Enter a valid name"
        }
        return emailfield && passwordfield && matchpasswordfield && fnamefield && lnamefield
    }

}
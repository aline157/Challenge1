package com.example.challenge1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challenge1.databinding.ActivityLoginBinding
import com.example.challenge1.internal.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        if (checkAccount()){
//            startActivity(Intent(this, WelcomeActivity::class.java))
//            this.finish()
//        }
        if (getCurrentAccount().isSignIn=="true"){
            startActivity(Intent(this, WelcomeActivity::class.java))
            this.finish()
        }


        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


        binding.loginBtn.setOnClickListener {

            if(verifyAccount(binding.emailField.getTextTrimed(),binding.passwordField.getTextTrimed())){


                val account:HashMap<String,String> = HashMap()
                account["firstname"] = getCurrentAccount().firstName.toString()
                account["lastname"] = getCurrentAccount().LastName.toString()
                account["email"] = getCurrentAccount().email.toString()
                account["password"] = getCurrentAccount().password.toString()
                account["isSignIn"] = "true"
                addDataToPrefs(account)

                startActivity(Intent(this, WelcomeActivity::class.java))
                this.finish()
//                Toasty.success(this, "Success! Welcome ${getCurrentAccount().firstName}", Toast.LENGTH_SHORT, true).show()
                Toast.makeText(this,"Success! Welcome ${getCurrentAccount().firstName}", Toast.LENGTH_SHORT).show()
            }else{
//                Toasty.error(this, "Error! User does not exist", Toast.LENGTH_SHORT, true).show()
                Toast.makeText(this,"Error! Invalid credentials", Toast.LENGTH_SHORT).show()
            }


        }
    }
}
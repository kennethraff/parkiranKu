package com.example.appparkir

import Model.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.appparkir.databinding.ActivityHomeBinding
import com.example.appparkir.databinding.ActivityMainBinding
import com.example.appparkir.databinding.ActivityNewAdminBinding

class newAdminActivity : AppCompatActivity() {

    lateinit var user:User
    private lateinit var binding: ActivityNewAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        listener()
    }

    private fun listener(){
        binding.addAdminButtonNewAdmin.setOnClickListener {
            var username = binding.usernameInputLayoutNewAdmin.editText?.text.toString().trim()
            var email = binding.emailInputLayoutNewAdmin.editText?.text.toString().trim()
            var password = binding.passwordInputLayoutNewAdmin.editText?.text.toString().trim()

            user = User(username, email, password)
            GlobalVar.Database.list.add(user)
            checker()
        }
    }

    private fun checker(){
        var isCompleted:Boolean = true
            if (user.username!!.isEmpty()){
                binding.usernameInputLayoutNewAdmin.error = "Username still empty"
                isCompleted = false
            } else{
                binding.usernameInputLayoutNewAdmin.error = ""
            }

            if (user.email!!.isEmpty()){
                binding.emailInputLayoutNewAdmin.error = "Email still empty"
                isCompleted = false
            }else{
                if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()){
                    binding.emailInputLayoutNewAdmin.error = "Email invalid"
                    isCompleted = false
                }else{
                    binding.emailInputLayoutNewAdmin.error = ""
                }
            }

            if (user.password!!.isEmpty()){
                binding.passwordInputLayoutNewAdmin.error = "Password still empty"
                isCompleted = false
            }else{
                if (user.password!!.length < 8) {
                    binding.passwordInputLayoutNewAdmin.error = "Password too short"
                    isCompleted = false
                }else if (!user.password!!.matches(".*[a-z].*".toRegex())){
                    binding.passwordInputLayoutNewAdmin.error = "Password must contain lower case"
                    isCompleted = false
                }else if (!user.password!!.matches(".*[A-Z].*".toRegex())){
                    binding.passwordInputLayoutNewAdmin.error = "Password must contain upper case"
                    isCompleted = false
                }else{
                    binding.passwordInputLayoutNewAdmin.error=""
                }
            }

            if (isCompleted){
                Toast.makeText(this, "Create Successful", Toast.LENGTH_LONG).show()
                val myIntent = Intent(this, HomeActivity::class.java)
                startActivity(myIntent)
            }
    }
}
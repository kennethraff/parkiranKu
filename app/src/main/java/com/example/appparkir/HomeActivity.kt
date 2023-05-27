package com.example.appparkir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appparkir.databinding.ActivityHomeBinding
import com.example.appparkir.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        listener()
    }



    private fun listener(){
        binding.createAdminButtonHome.setOnClickListener{
            val myIntent = Intent(this, newAdminActivity::class.java)
            startActivity(myIntent)
        }

        binding.loginButtonHome.setOnClickListener{
            var isCorrect = false
            for (info in GlobalVar.Database.list){
                if(info.username!!.trim().equals(binding.usernameInputLayoutHome.editText?.text!!.toString().trim()) && info.password!!.trim().equals(
                        binding.passwordInputLayoutHome.editText?.text!!.toString().trim())){
                    val myIntent = Intent(this, RegisterActivity::class.java)
                    startActivity(myIntent)
                    isCorrect = true
                    finish()
                }
            }
            if (isCorrect == false){
                Toast.makeText(this, "Email and Password Invalid, Please Try Again", Toast.LENGTH_LONG).show()
            }
        }
    }


}
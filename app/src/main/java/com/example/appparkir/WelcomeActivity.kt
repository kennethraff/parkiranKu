package com.example.appparkir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appparkir.databinding.ActivityMainBinding
import com.example.appparkir.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        listener()
    }
    private fun listener(){
        binding.button.setOnClickListener(){
            val myIntent= Intent(this,HomeActivity::class.java)
            startActivity(myIntent)
        }
    }
}
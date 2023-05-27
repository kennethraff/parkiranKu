package com.example.appparkir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appparkir.databinding.ActivityNewAdminBinding
import com.example.appparkir.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.motorButtonRegister.setOnClickListener { val myIntent = Intent(this, RegisterMotorActivity::class.java)
            startActivity(myIntent) }
        binding.cekKendaraanButtonRegister.setOnClickListener{
            val myIntent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(myIntent)
        }
        binding.historyButtonRegister.setOnClickListener {
            val myIntent = Intent(this, HistoryActivity::class.java)
            startActivity(myIntent)
        }

        binding.buttonLogoutRegister.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            startActivity(myIntent)
        }
    }
}
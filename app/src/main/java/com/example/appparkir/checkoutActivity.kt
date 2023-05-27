package com.example.appparkir

import Model.Kendaraan
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appparkir.databinding.ActivityCheckoutBinding

class checkoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding



    private var price = 0;
    private var total = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()





            binding.radioGroup.setOnCheckedChangeListener { group, checkedID ->
                if (checkedID == R.id.radioButtonMotor){
                    var price = 2000
                    var time = binding.lamaWaktuCheckout.editText?.text.toString().toInt()
                    binding.checkoutButtonCheckout.setOnClickListener {
                        total = price * time
                        binding.subtotalTextViewCheckout.text = total.toString().trim()
                    }
                } else if (checkedID == R.id.radioButtonMobil){
                    var price = 5000
                    var time = binding.lamaWaktuCheckout.editText?.text.toString().toInt()
                    binding.checkoutButtonCheckout.setOnClickListener {
                        total = price * time
                        binding.subtotalTextViewCheckout.text = total.toString().trim()
                    }
                } else if (checkedID ==  R.id.radioButtonTruk) {
                    var price = 10000
                    var time = binding.lamaWaktuCheckout.editText?.text.toString().toInt()
                    binding.checkoutButtonCheckout.setOnClickListener {
                        total = price * time
                        binding.subtotalTextViewCheckout.text = total.toString().trim()
                    }
                } else {
                    binding.subtotalTextViewCheckout.text = "Kosong"
                }
            }


        binding.doneButtonCheckout.setOnClickListener {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
            Toast.makeText(this, "Checkout Sukses", Toast.LENGTH_LONG).show()
        }
    }
}
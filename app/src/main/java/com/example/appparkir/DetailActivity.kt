package com.example.appparkir

import GlobalVar.DatabaseKendaraan
import Model.Kendaraan
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appparkir.databinding.ActivityDetailBinding
import com.example.appparkir.databinding.ActivityHomeBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        listener()
        GetIntent()
    }

    private fun listener(){
        binding.backButtonDetailLayout.setOnClickListener() {
            val myIntent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(myIntent)
        }
        binding.deleteButtonDetail.setOnClickListener {
           DatabaseKendaraan.listDataKendaraan.removeAt(position)
            val myIntent = Intent(this, checkoutActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        binding.editFloatingActionButton.setOnClickListener{
            val myIntent = Intent(this, RegisterMotorActivity::class.java).apply {
                putExtra("position", position)
            }
            startActivity(myIntent)
        }
    }
    private fun GetIntent(){
        position = intent.getIntExtra("position", -1)
        val movie = DatabaseKendaraan.listDataKendaraan[position]
        Display(movie)
    }
    private fun Display(kendaraan: Kendaraan){
        binding.nopolTextViewDetail.text = kendaraan.nopol
        binding.namaPemilikTextViewDetail.text = kendaraan.namapemilik
        binding.merkTextViewDetail.text = kendaraan.merek
        if (kendaraan.imageUri.isNotEmpty()) {
            binding.imageViewDetail.setImageURI(Uri.parse(kendaraan.imageUri))
        }
    }

    override fun onResume() {
        super.onResume()
        val kendaraan = DatabaseKendaraan.listDataKendaraan[position]
        Display(kendaraan)
    }
}
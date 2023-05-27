package com.example.appparkir

import GlobalVar.DatabaseKendaraan
import GlobalVar.DatabaseLaporan
import Interface.CardHistoryListener
import adapter.ListHistoryRVAdapter
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appparkir.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity(), CardHistoryListener {

    private val adapter = ListHistoryRVAdapter(DatabaseLaporan.listDataLaporan, this)
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupRecyclerView()

        checkPermissions()
        textHidden()

    }

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(baseContext, 2)
        binding.recyclerViewHistory.layoutManager= layoutManager //Set layout
        binding.recyclerViewHistory.adapter=adapter //Set adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun textHidden(){
        if(DatabaseKendaraan.listDataKendaraan.isEmpty()){
            binding.DataMasihKosongTextViewHistory.isVisible = true
        } else {
            binding.DataMasihKosongTextViewHistory.isInvisible = true
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), DatabaseKendaraan.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), DatabaseKendaraan.CAMERA_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Camera Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == DatabaseKendaraan.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == DatabaseKendaraan.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCardClick(position: Int) {

    }
}
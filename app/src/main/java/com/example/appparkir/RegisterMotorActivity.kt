package com.example.appparkir

import GlobalVar.DatabaseKendaraan.Companion.listDataKendaraan
import GlobalVar.DatabaseLaporan
import Model.History
import Model.Kendaraan
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.appparkir.databinding.ActivityRegisterBinding
import com.example.appparkir.databinding.ActivityRegisterMotorBinding

class RegisterMotorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterMotorBinding


    private var position = -1
    private var isCompleted =false
    private lateinit var kendaraan: Kendaraan
    private lateinit var history: History
    private var tempUri=""


    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== Activity.RESULT_OK){ //aplikasi gallery sukses mendapatkan
            val uri = it.data?.data // Get path to image from gallery
            binding.imagePosterRegisterMotor.setImageURI(uri) // menampilkan di image view
            if(uri!=null){
                if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){
                    baseContext.getContentResolver().takePersistableUriPermission(
                        uri, Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                tempUri = uri.toString()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterMotorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        listener()
        GetIntent()

        GetEdit()
    }

    private fun GetEdit(){
        position = intent.getIntExtra("position",-1)
        if(position!=-1){
            binding.registerMotorButton.setText("Save")
            binding.textView17.setText("Edit detail")
            val tes = listDataKendaraan[position]
            Display(tes)
        }
    }

    private fun checker() {
        var isCompleted: Boolean = true
        if (kendaraan.nopol!!.isEmpty()) {
            binding.nopolInputLayoutRegisterMotor.error = "Nopol harus diisi"
            isCompleted = false
        } else {
            binding.nopolInputLayoutRegisterMotor.error = ""
        }

        if (kendaraan.namapemilik!!.isEmpty()) {
            binding.namaInputLayoutRegisterMotor.error = "Nama harus diisi"
            isCompleted = false
        } else {
            binding.namaInputLayoutRegisterMotor.error = ""
        }

        if (kendaraan.merek!!.isEmpty()) {
            binding.merkInputLayoutRegisterMotor.error = "Merk harus diisi"
            isCompleted = false
        } else {
            binding.merkInputLayoutRegisterMotor.error = ""
        }

        if (isCompleted) {
            if (position == -1) {
                kendaraan.imageUri = tempUri
                listDataKendaraan.add(kendaraan)
                DatabaseLaporan.listDataLaporan.add(history)
                Toast.makeText(this, "Kendaraan sukses ditambahkan", Toast.LENGTH_SHORT).show()
                val myIntent = Intent(this, RegisterActivity::class.java)
                startActivity(myIntent)
            } else{
                if(tempUri== listDataKendaraan[position].imageUri){
                    kendaraan.imageUri = listDataKendaraan[position].imageUri
                    history.imageUri =  DatabaseLaporan.listDataLaporan[position].imageUri
                }else if(tempUri==""){
                    kendaraan.imageUri = listDataKendaraan[position].imageUri
                    kendaraan.imageUri = DatabaseLaporan.listDataLaporan[position].imageUri
                }else{
                    kendaraan.imageUri = tempUri
                }
                listDataKendaraan[position]=kendaraan
                DatabaseLaporan.listDataLaporan[position]= history
                Toast.makeText(this, "Edit berhasil", Toast.LENGTH_SHORT).show()
                val myIntent = Intent(this,RegisterActivity::class.java)
                startActivity(myIntent)
            }
            finish()
        }
    }
    private fun listener() {
        binding.backButtonRegisterMotor.setOnClickListener() {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent);
        }


        binding.imagePosterRegisterMotor.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type= "image/*"
            GetResult.launch(myIntent)
        }



        binding.registerMotorButton.setOnClickListener{
            var nopol = binding.nopolInputLayoutRegisterMotor .editText?.text.toString().trim()
            var nama = binding.namaInputLayoutRegisterMotor.editText?.text.toString().trim()
            var merk = binding.merkInputLayoutRegisterMotor.editText?.text.toString().trim()

            kendaraan= Kendaraan(nopol, nama, merk)
            history = History(nopol, nama, merk)

            checker()




        }
    }
    private fun GetIntent() {
        position = intent.getIntExtra("position", -1)
        if (position!=-1) {
            val kendaraan = listDataKendaraan[position]

            Display(kendaraan)

        }



    }
    private fun Display(kendaraan: Kendaraan?) {
        binding.nopolInputLayoutRegisterMotor.editText!!.setText(kendaraan!!.nopol)
        binding.namaInputLayoutRegisterMotor.editText!!.setText(kendaraan!!.namapemilik)
        binding.merkInputLayoutRegisterMotor.editText!!.setText(kendaraan!!.merek)
        binding.imagePosterRegisterMotor.setImageURI(Uri.parse(kendaraan.imageUri))



    }
}
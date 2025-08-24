package com.example.qrcodescanner

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.qrcodescanner.databinding.ActivityQrcodegeneratorBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

private const val TAG = "qrcodegenrator"
private const val QR_SIZE = 1024
class qrcodegenerator : AppCompatActivity() {
    private lateinit var binding: ActivityQrcodegeneratorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQrcodegeneratorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        registerUIlistener()
    }

    private fun registerUIlistener() {
        binding.generateqrbutton.setOnClickListener {
            generateQrcode()
        }
    }

    private fun generateQrcode() {
        val inputText = binding.editTextText.text.toString()
        try {
            val encoder = BarcodeEncoder()
            val bitmap = encoder.encodeBitmap(inputText, BarcodeFormat.QR_CODE,QR_SIZE,QR_SIZE)
            binding.imageView.setImageBitmap(bitmap)
        }catch (e : WriterException){
            Log.e(TAG,"generateQrcode: ${e.message}" )
        }
    }
}

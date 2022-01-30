package com.example.background

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import com.example.background.databinding.ActivitySelectImageBinding

class SelectImageActivity : AppCompatActivity(){

    private lateinit var binding:ActivitySelectImageBinding
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectImage.setOnClickListener {

            val actionView = Intent(this,BlurActivity::class.java)
            startActivity(actionView)



        }
    }
}
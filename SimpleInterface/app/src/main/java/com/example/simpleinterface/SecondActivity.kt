package com.example.simpleinterface

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.ImageView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_second)

        val imageView1 = findViewById<ImageView>(R.id.ivFeaturedImage)
        val imageView2 = findViewById<ImageView>(R.id.ivFeaturedImage2)
        val imageView3 = findViewById<ImageView>(R.id.ivFeaturedImage3)

        Glide.with(this)
            .load("https://players.com.ua/wp-content/uploads/2024/11/photo_5310117846619121418_y-1024x680.jpeg.webp")
            .centerCrop()
            .into(imageView1)

        Glide.with(this)
            .load("https://players.com.ua/wp-content/uploads/2024/11/photo_5310117846619121419_y-1024x680.jpeg.webp")
            .centerCrop()
            .into(imageView2)

        Glide.with(this)
            .load("https://players.com.ua/wp-content/uploads/2024/11/photo_5310117846619121420_y-1024x680.jpeg.webp")
            .centerCrop()
            .into(imageView3)
    }
}
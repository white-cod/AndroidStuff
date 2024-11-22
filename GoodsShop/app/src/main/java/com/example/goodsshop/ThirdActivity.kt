package com.example.goodsshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.ImageView

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_third)

        val imageView1 = findViewById<ImageView>(R.id.ivFeaturedImage)

        Glide.with(this)
            .load("https://content1.rozetka.com.ua/goods/images/big/296306598.jpg")
            .centerCrop()
            .into(imageView1)
    }
}
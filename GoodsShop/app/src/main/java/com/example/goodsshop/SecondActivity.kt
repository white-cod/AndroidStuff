package com.example.goodsshop

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

        Glide.with(this)
            .load("https://content2.rozetka.com.ua/goods/images/big/437340809.jpg")
            .centerCrop()
            .into(imageView1)
    }
}
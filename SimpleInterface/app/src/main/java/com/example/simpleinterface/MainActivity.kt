package com.example.simpleinterface

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import android.widget.ImageView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardViewArticle1 = findViewById<CardView>(R.id.cardview_article1)
        val imageViewArticle1 = findViewById<ImageView>(R.id.iv_article1_image)

        Glide.with(this)
            .load("https://players.com.ua/wp-content/uploads/2024/11/photo_5310117846619121418_y-1024x680.jpeg.webp")
            .centerCrop()
            .into(imageViewArticle1)

        cardViewArticle1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val cardViewArticle2 = findViewById<CardView>(R.id.cardview_article2)
        val imageViewArticle2 = findViewById<ImageView>(R.id.iv_article2_image)

        Glide.with(this)
            .load("https://i.obozrevatel.com/gallery/2024/11/14/zobrazhennya2024-11-14164233064.png")
            .centerCrop()
            .into(imageViewArticle2)

        cardViewArticle2.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}
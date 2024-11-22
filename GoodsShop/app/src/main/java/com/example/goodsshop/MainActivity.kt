package com.example.goodsshop

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("theme_pref", MODE_PRIVATE)
        applySavedTheme()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggleThemeButton = findViewById<Button>(R.id.btn_toggle_theme)
        toggleThemeButton.setOnClickListener {
            toggleTheme()
        }

        val cardViewArticle1 = findViewById<CardView>(R.id.cardview_article1)
        val imageViewArticle1 = findViewById<ImageView>(R.id.iv_article1_image)

        Glide.with(this)
            .load("https://content.rozetka.com.ua/goods/images/big/437340805.jpg")
            .centerCrop()
            .into(imageViewArticle1)

        cardViewArticle1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val cardViewArticle2 = findViewById<CardView>(R.id.cardview_article2)
        val imageViewArticle2 = findViewById<ImageView>(R.id.iv_article2_image)

        Glide.with(this)
            .load("https://content.rozetka.com.ua/goods/images/big/296306600.jpg")
            .centerCrop()
            .into(imageViewArticle2)

        cardViewArticle2.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    private fun applySavedTheme() {
        val isDarkTheme = sharedPreferences.getBoolean("is_dark_theme", false)
        if (isDarkTheme) {
            setTheme(R.style.Theme_GoodsShop_Dark)
        } else {
            setTheme(R.style.Theme_GoodsShop)
        }
    }

    private fun toggleTheme() {
        val isCurrentlyDark = sharedPreferences.getBoolean("is_dark_theme", false)
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_dark_theme", !isCurrentlyDark)
        editor.apply()

        recreate()
    }
}
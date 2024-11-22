package com.example.simpleinterface

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
        val imageView2 = findViewById<ImageView>(R.id.ivFeaturedImage2)
        val imageView3 = findViewById<ImageView>(R.id.ivFeaturedImage3)

        Glide.with(this)
            .load("https://i.obozrevatel.com/gallery/2024/11/14/zobrazhennya2024-11-14164233064.png")
            .centerCrop()
            .into(imageView1)

        Glide.with(this)
            .load("https://i.obozrevatel.com/gallery/2024/11/14/zobrazhennya2024-11-14163558776.png")
            .centerCrop()
            .into(imageView2)

        Glide.with(this)
            .load("https://i.obozrevatel.com/gallery/2024/11/14/zobrazhennya2024-11-14163639222.png")
            .centerCrop()
            .into(imageView3)
    }
}
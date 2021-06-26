package com.dr.retrofitmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_show_image.*

class ShowImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        var intent = intent

        imageTitle.text = intent.getStringExtra("title")
        Glide.with(this)
                .load(intent.getStringExtra("url"))
                .into(selectedImage)
    }
}
package com.young.animeapp_with_volley_glide.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.young.animeapp_with_volley_glide.R

class AnimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime)
        // hide the default actionbar
        supportActionBar?.hide()
        // Recieve data
        val name = intent.extras?.getString("anime_name")
        val description = intent.extras?.getString("anime_description")
        val studio = intent.extras?.getString("anime_studio")
        val category = intent.extras?.getString("anime_category")
        val nb_episode = intent.extras?.getInt("anime_nb_episode")
        val rating = intent.extras?.getString("anime_rating")
        val image_url = intent.extras?.getString("anime_img")
        // ini views
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsingtoolbar_id)
        collapsingToolbarLayout.isTitleEnabled = true
        val tv_name = findViewById<TextView>(R.id.aa_anime_name)
        val tv_studio = findViewById<TextView>(R.id.aa_studio)
        val tv_categorie = findViewById<TextView>(R.id.aa_categorie)
        val tv_description = findViewById<TextView>(R.id.aa_description)
        val tv_rating = findViewById<TextView>(R.id.aa_rating)
        val img = findViewById<ImageView>(R.id.aa_thumbnail)
        // setting values to each view
        tv_name.text = name
        tv_categorie.text = category
        tv_description.text = description
        tv_rating.text = rating
        tv_studio.text = studio
        collapsingToolbarLayout.title = name
        val requestOptions = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(
            R.drawable.loading_shape
        )
        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img)
    }
}
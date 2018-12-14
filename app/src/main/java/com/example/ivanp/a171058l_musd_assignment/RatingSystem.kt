package com.example.ivanp.a171058l_musd_assignment

import android.content.Intent
import android.graphics.Movie
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rating_system.*
import kotlinx.android.synthetic.main.movielist.*

class RatingSystem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_system)

        var MovieInfo = applicationContext as MovieGetSet
        ReviewTextbox.setText("Enter your review for the movie:" + MovieInfo.getMovieName())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movieratingmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.SubmitBtn){
            var MovieInfo = applicationContext as MovieGetSet
            val ptMovieRate = findViewById<EditText>(R.id.ratingreview)
            val movieRate: String? = ptMovieRate.text.toString()
            if(movieRate.isNullOrEmpty()){
                ptMovieRate.setError("Please enter a rating")
            }
            else {
                MovieInfo.setMovieStar(RatingBar.rating)
                MovieInfo.setMovieRatings(ratingreview.text.toString())
                val intent = Intent(this, ViewMovieDetails::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val intent = Intent(this, ViewMovieDetails::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

}

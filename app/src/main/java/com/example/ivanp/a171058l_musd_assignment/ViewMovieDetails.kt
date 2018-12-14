package com.example.ivanp.a171058l_musd_assignment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_view_movie_details.*

class ViewMovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie_details)

        var MovieInfo = applicationContext as MovieGetSet
        titleinfo.text = MovieInfo.getMovieName()
        descript.text = MovieInfo.getMovieDesc()
        lang.text = MovieInfo.getMovieLang()
        releasedate.text = MovieInfo.getMovieDate()

        if(!MovieInfo.getMovieRatings().isNullOrBlank()){
            YesReviewText.text = MovieInfo.getMovieRatings()
            RatingBar.rating = MovieInfo.getMovieStar()
            RatingBar.visibility = View.VISIBLE
            RatingBar.setIsIndicator(true)
            NoReviewText.visibility = View.GONE
        }

        if(MovieInfo.getMovieSuitable()){
            SuitChild.text = "Yes"
        }
        else{
            if(MovieInfo.getMovieViolence()){
                SuitChild.text = "Not Suitable\nViolence"
            }
            else if(MovieInfo.getMovieStrongLang()){
                SuitChild.text = "Not Suitable\nLanguage Used Inappropriate"
            }
            else if(MovieInfo.getMovieStrongLang() && MovieInfo.getMovieViolence()){
                SuitChild.text = "Not Suitable\nViolence\nLanguage Used Inappropriate"
            }
            else{
                SuitChild.text = "Not Suitable"
            }
        }

        registerForContextMenu(NoReviewText)
        registerForContextMenu(YesReviewText)
    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.NoReviewText || v?.id == R.id.YesReviewText){
            menu?.add(1, 1001, 1, "Add Review")

        }

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1001) {
            var myIntent = Intent(this, RatingSystem::class.java)
            startActivity(myIntent)
        }else if(item?.itemId == 1002) {
            var myIntent = Intent(this, EditMovie::class.java)
            startActivity(myIntent)
        }
        return super.onContextItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movieviewmovie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.EditMovie){
            var myIntent = Intent(this, EditMovie::class.java)
            startActivity(myIntent)
        }

        return super.onOptionsItemSelected(item)
    }

}

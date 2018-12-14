package com.example.ivanp.a171058l_musd_assignment


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cbSuitable.setOnClickListener(View.OnClickListener {

            if (cbSuitable.isChecked == true)
            {
                NotSuitable.visibility = View.VISIBLE
            } else {
                NotSuitable.visibility = View.INVISIBLE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movieaddmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.ClearAddEntry)
        {
            MovieName.setText("")
            Description.setText("")
            LangEnglish.isChecked= true
            ReleaseDate.setText("")
            cbSuitable.isChecked= false
            MovieName.requestFocus()
            NotSuitable.visibility = View.INVISIBLE

        }

        else if(item?.itemId ==R.id.Addbtn)
        {
            var MovieInfo = applicationContext as MovieGetSet
            val MovieName = findViewById<TextView>(R.id.MovieName)
            val movien: String? = MovieName.text.toString()
            val Description = findViewById<TextView>(R.id.Description)
            val MovieDesc: String? = Description.text.toString()
            val ReleaseDate = findViewById<TextView>(R.id.ReleaseDate)
            val rDate: String? = ReleaseDate.text.toString()

            val radioLangGroup = findViewById<RadioGroup>(R.id.RGLanguage)
            val idSelected = radioLangGroup.checkedRadioButtonId
            val radioLangText = findViewById<RadioButton>(idSelected).text

            if (movien.isNullOrBlank()) {
                MovieName.setError("Field empty")
            }
            if (MovieDesc.isNullOrBlank()) {
                Description.setError("Field empty")
            }
            if (rDate.isNullOrBlank()) {
                ReleaseDate.setError("Field empty")
            }

            if(!rDate.isNullOrBlank() && !movien.isNullOrBlank() && !MovieDesc.isNullOrBlank()){
                MovieInfo.setMovieName(movien.toString())
                MovieInfo.setMovieDesc(MovieDesc.toString())
                MovieInfo.setMovieLang(radioLangText.toString())
                MovieInfo.setMovieDate(rDate.toString())
                if(cbSuitable.isChecked){
                    MovieInfo.setMovieSuitable(false)
                    if(cbLanguage.isChecked){
                        MovieInfo.setMovieStrongLang(true)
                    }
                    if(cbViolence.isChecked){
                        MovieInfo.setMovieViolence(true)
                    }
                }
                val intent = Intent(this, ViewMovieDetails::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

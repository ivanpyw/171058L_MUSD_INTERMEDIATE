package com.example.ivanp.a171058l_musd_assignment

import android.content.ClipData
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.movieedit.*

class EditMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movieedit)

        cbSuitableEdit.setOnClickListener(View.OnClickListener {

            if (cbSuitableEdit.isChecked == true) {
                NotSuitableEdit.visibility = View.VISIBLE
            } else {
                NotSuitableEdit.visibility = View.INVISIBLE
            }
        })

        if (cbSuitableEdit.isChecked == true) {
            NotSuitableEdit.visibility = View.VISIBLE
        } else {
            NotSuitableEdit.visibility = View.INVISIBLE
        }

        var MovieInfo = applicationContext as MovieGetSet
        MovieNameEdit.setText(MovieInfo.getMovieName())
        DescriptionEdit.setText(MovieInfo.getMovieDesc())
        ReleaseDateEdit.setText(MovieInfo.getMovieDate())

        when(MovieInfo.getMovieLang()){
            "English" -> LangEnglishEdit.isChecked=true
            "Chinese" -> LangChineseEdit.isChecked=true
            "Malay" -> LangMalayEdit.isChecked=true
            "Tamil" -> LangTamilEdit.isChecked=true
        }

        cbLanguageEdit.isChecked=MovieInfo.getMovieStrongLang()
        cbSuitableEdit.isChecked=MovieInfo.getMovieSuitable()
        cbViolenceEdit.isChecked=MovieInfo.getMovieViolence()

        ReleaseDateEdit.setText(MovieInfo.getMovieDate())
        if(!MovieInfo.getMovieSuitable()) {
            cbSuitableEdit.isChecked = true
            if (MovieInfo.getMovieStrongLang()) {
                cbLanguageEdit.isChecked = true
            }
            if (MovieInfo.getMovieViolence()) {
                cbViolenceEdit.isChecked = true
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movieeditmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.SaveBtn){
            var MovieInfo = applicationContext as MovieGetSet



            if (MovieNameEdit.text.isNullOrBlank()) {
                MovieNameEdit.setError("Field empty")
            }
            else if (DescriptionEdit.text.isNullOrBlank()) {
                DescriptionEdit.setError("Field empty")
            }
            else if (ReleaseDateEdit.text.isNullOrBlank()) {
                ReleaseDateEdit.setError("Field empty")
            }else {

                val radioLangGroup = findViewById<RadioGroup>(R.id.RGEdit)
                val idSelected = radioLangGroup.checkedRadioButtonId
                val radioLangText = findViewById<RadioButton>(idSelected).text
                MovieInfo.setMovieDate(ReleaseDateEdit.text.toString())
                MovieInfo.setMovieDesc(DescriptionEdit.text.toString())
                MovieInfo.setMovieLang(radioLangText.toString())
                MovieInfo.setMovieName(MovieNameEdit.text.toString())

                if(cbSuitableEdit.isChecked){
                    MovieInfo.setMovieSuitable(false)
                    if(cbLanguageEdit.isChecked){
                        MovieInfo.setMovieStrongLang(true)
                    }else
                    {
                        MovieInfo.setMovieStrongLang(false)
                    }

                    if(cbViolenceEdit.isChecked){
                        MovieInfo.setMovieViolence(true)
                    } else{
                        MovieInfo.setMovieViolence(false)
                    }
                } else
                {
                    MovieInfo.setMovieSuitable(true)
                }
                val intent = Intent(this, ViewMovieDetails::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
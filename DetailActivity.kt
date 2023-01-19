package de.syntax_institut.musikApp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import de.syntax_institut.musikApp.data.Datasource
import de.syntax_institut.musikApp.databinding.DetailActivityBinding

/**
 * Dies ist die Klasse für das zugehörige Layout detail_activity
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailActivityBinding

    /**
     * Die lifecycle methode onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.detail_activity)

        // Die Informationen werden aus dem intent Objekt geholt
        // TODO Schreibe hier deinen Code
        val imageID = intent.extras?.getInt("imageID")



        // Die Informationen werden aus dem intent Objekt geholt
        // TODO Schreibe hier deinen Code
        val titleID = intent.extras?.getInt("titleID")

        var songTitle = ""

        // Die Informationen werden zugewiesen (nach Test auf null)
        // TODO Schreibe hier deinen Code
        if(titleID!=null){
            songTitle = getString(titleID)
            binding.tvTitleDetail.text = songTitle
        }
        if (imageID!=null){

            binding.ivCoverDetail.setImageResource(imageID)
        }


        binding.btnShare.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,
            "Höre dir diesen Song : $titleID an ")
            intent.type = "text/plain"
            val chooser = Intent.createChooser(intent, "Songify")
            startActivity(chooser)
        }
        binding.ibNext.setOnClickListener{
            changeSong()
        }
        binding.ibPrevious.setOnClickListener{
            changeSong()
        }



    }
    private fun changeSong(){
        val nextIntent = Intent(this, DetailActivity::class.java)

        val song = Datasource(this).loadSongs().random()

        nextIntent.putExtra("imageID", song.imageResource )
        nextIntent.putExtra("titleID",song.stringResource)

        this.startActivity(nextIntent)

        finish()
    }
}
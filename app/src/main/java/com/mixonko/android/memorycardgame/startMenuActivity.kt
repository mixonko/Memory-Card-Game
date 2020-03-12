package com.mixonko.android.memorycardgame

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class StartMenuActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var newGame: Button
    lateinit var loadGame: Button
    lateinit var settings: Button
    lateinit var exit: Button
    lateinit var sp: SharedPreferences

    lateinit var mediaPlayerMusic: MediaPlayer
    lateinit var mediaPlayerButtonsClick: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_menu)

        sp = PreferenceManager.getDefaultSharedPreferences(this)

        mediaPlayerButtonsClick = MediaPlayer.create(this, R.raw.buttons_sound)

        newGame = findViewById(R.id.new_game)
        loadGame = findViewById(R.id.load_game)
        settings = findViewById(R.id.settings)
        exit = findViewById(R.id.exit)

        newGame.setOnClickListener(this)

        loadGame.setOnClickListener(this)

        settings.setOnClickListener(this)

        exit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        vibrate()
        startButtonSound()

        when (v?.id) {
            R.id.new_game -> startActivity(Intent(this, MainActivity::class.java))
            R.id.load_game -> loadGame()
            R.id.settings ->  startActivity(Intent(this, PrefActivity::class.java))
            R.id.exit -> finish()
        }
    }

    private fun loadGame(){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(LOAD_GAME, 1)
        startActivity(intent)

    }

    private fun vibrate() {
        if (sp.getBoolean("vibration", true)) {
            val vibe = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibe.vibrate(40)
        }
    }

    private fun startButtonSound(){
        if (sp.getBoolean("music", true)) {

            mediaPlayerButtonsClick.start()
        }
    }

    override fun onResume() {
        super.onResume()
        if (sp.getBoolean("music", true)) {
            mediaPlayerMusic = MediaPlayer.create(this, R.raw.music)
            mediaPlayerMusic.isLooping = true
            mediaPlayerMusic.start()
        } else try {
            mediaPlayerMusic.stop()
        }catch (e: Exception){}

    }


    override fun onPause() {
        super.onPause()
        try {
            mediaPlayerMusic.stop()
        }catch (e: Exception){}
    }

}

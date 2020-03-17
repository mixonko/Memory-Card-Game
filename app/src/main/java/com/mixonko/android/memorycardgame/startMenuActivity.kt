package com.mixonko.android.memorycardgame

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import android.preference.PreferenceManager
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.BounceInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class StartMenuActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var newGame: Button
    lateinit var settings: Button
    lateinit var support: Button
    lateinit var sp: SharedPreferences

    lateinit var mediaPlayerMusic: MediaPlayer
    lateinit var mediaPlayerButtonsClick: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_start_menu)

        sp = PreferenceManager.getDefaultSharedPreferences(this)

        mediaPlayerButtonsClick = MediaPlayer.create(this, R.raw.buttons_sound)

        newGame = findViewById(R.id.play)
        settings = findViewById(R.id.setting)
        support = findViewById(R.id.support)

        newGame.setOnClickListener(this)

        settings.setOnClickListener(this)

        support.setOnClickListener(this)

        startButtonsAnimation()
    }

    override fun onClick(v: View) {
        vibrate()
        startButtonSound()

        when (v.id) {
            R.id.play -> startActivity(Intent(this, GameActivity::class.java))
            R.id.setting -> startActivity(Intent(this, PrefActivity::class.java))
            R.id.support -> finish()
        }
    }

    private fun vibrate() {
        if (vibrationCheck()) {
            val vibe = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibe.vibrate(40)
        }
    }

    private fun startButtonSound() {
        if (musicCheck()) {

            mediaPlayerButtonsClick.start()
        }
    }

    private fun musicCheck(): Boolean = sp.getBoolean("music", true)

    private fun vibrationCheck(): Boolean = sp.getBoolean("vibration", true)

    override fun onResume() {
        super.onResume()
        if (musicCheck()) {
            mediaPlayerMusic = MediaPlayer.create(this, R.raw.music)
            mediaPlayerMusic.isLooping = true
            mediaPlayerMusic.start()
        } else try {
            mediaPlayerMusic.stop()
        } catch (e: Exception) {
        }

    }

    override fun onPause() {
        super.onPause()
        try {
            mediaPlayerMusic.stop()
        } catch (e: Exception) {
        }
    }

    private fun startButtonsAnimation() {
        val bounceInterpolator = BounceInterpolator()
        val objectAnimator1 = ObjectAnimator.ofFloat(newGame, View.TRANSLATION_Y, -700f, 0f)
        objectAnimator1.setInterpolator(bounceInterpolator)
        objectAnimator1.setDuration(900).start()

        val objectAnimator3 = ObjectAnimator.ofFloat(settings, View.TRANSLATION_Y, 200f, 0f)
        objectAnimator3.setInterpolator(bounceInterpolator)
        objectAnimator3.setDuration(900).start()

        val objectAnimator4 = ObjectAnimator.ofFloat(support, View.TRANSLATION_Y, 300f, 0f)
        objectAnimator4.setInterpolator(bounceInterpolator)
        objectAnimator4.setDuration(900).start()
    }

}

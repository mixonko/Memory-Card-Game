package com.mixonko.android.memorycardgame

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import android.os.Vibrator
import android.animation.ObjectAnimator
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.preference.PreferenceManager
import android.view.View.TRANSLATION_Y
import android.view.animation.*
import android.widget.*
import java.lang.Exception
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

var backgroundStyle = 1

class MainActivity : AppCompatActivity() {

    private val cardBack = R.drawable.card_back

    private val image11 = R.drawable.ic_image11
    private val image12 = R.drawable.ic_image12
    private val image13 = R.drawable.ic_image13
    private val image14 = R.drawable.ic_image14
    private val image15 = R.drawable.ic_image15
    private val image16 = R.drawable.ic_image16
    private val image17 = R.drawable.ic_image17
    private val image18 = R.drawable.ic_image18
    private val image21 = R.drawable.ic_image21
    private val image22 = R.drawable.ic_image22
    private val image23 = R.drawable.ic_image23
    private val image24 = R.drawable.ic_image24
    private val image25 = R.drawable.ic_image25
    private val image26 = R.drawable.ic_image26
    private val image27 = R.drawable.ic_image27
    private val image28 = R.drawable.ic_image28

    private val background1 = R.drawable.background1
    private val background2 = R.drawable.background2
    private val background3 = R.drawable.background3
    private val background4 = R.drawable.background4

    private lateinit var imageView11: ImageView
    private lateinit var imageView12: ImageView
    private lateinit var imageView13: ImageView
    private lateinit var imageView14: ImageView
    private lateinit var imageView21: ImageView
    private lateinit var imageView22: ImageView
    private lateinit var imageView23: ImageView
    private lateinit var imageView24: ImageView
    private lateinit var imageView31: ImageView
    private lateinit var imageView32: ImageView
    private lateinit var imageView33: ImageView
    private lateinit var imageView34: ImageView
    private lateinit var imageView41: ImageView
    private lateinit var imageView42: ImageView
    private lateinit var imageView43: ImageView
    private lateinit var imageView44: ImageView

    private lateinit var background: ImageView

    private lateinit var line1: LinearLayout
    private lateinit var line2: LinearLayout
    private lateinit var line3: LinearLayout
    private lateinit var line4: LinearLayout

    private lateinit var firstPointsTextView: TextView
    private lateinit var secondPointsTextView: TextView
    private lateinit var firstGlobalPointsTextView: TextView
    private lateinit var secondGlobalPointsTextView: TextView

    private var firstCard: Int? = 0
    private var secondCard: Int? = 0

    private var clickedFirst: Int? = 0
    private var clickedSecond: Int? = 0

    private var cardNumber: Int = 1

    private var turn: Int = 1
    private var firstPlayerPoints: Int = 0
    private var secondPlayerPoints: Int = 0
    private var firstPlayerGlobalPoints: Int = 0
    private var secondPlayerGlobalPoints: Int = 0

    private var cardsArray =
          mutableListOf(11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 24, 25, 26, 27, 28)

    private lateinit var sp: SharedPreferences
    private lateinit var settings: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var soundPlayerDone: MediaPlayer
    private lateinit var soundPlayerNot: MediaPlayer

    private lateinit var gson: Gson

    private lateinit var firstImage: ImageView
    private lateinit var secondImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        sp = PreferenceManager.getDefaultSharedPreferences(this)

        gson = Gson()

        findViewById()

        startLayoutAnimation()

        firstPointsTextView.setTextColor(Color.GREEN)
        secondPointsTextView.setTextColor(Color.GRAY)

        imageView11.setTag(0)
        imageView12.setTag(1)
        imageView13.setTag(2)
        imageView14.setTag(3)
        imageView21.setTag(4)
        imageView22.setTag(5)
        imageView23.setTag(6)
        imageView24.setTag(7)
        imageView31.setTag(8)
        imageView32.setTag(9)
        imageView33.setTag(10)
        imageView34.setTag(11)
        imageView41.setTag(12)
        imageView42.setTag(13)
        imageView43.setTag(14)
        imageView44.setTag(15)

        Collections.shuffle(cardsArray)

        setOnClickListener()

    }

    private fun startLayoutAnimation() {
        val bounceInterpolator = BounceInterpolator()
        val objectAnimator1 = ObjectAnimator.ofFloat(line1, TRANSLATION_Y, -850f, 0f)
        objectAnimator1.setInterpolator(bounceInterpolator)
        objectAnimator1.setDuration(800).start()

        val objectAnimator2 = ObjectAnimator.ofFloat(line2, TRANSLATION_Y, -500f, 0f)
        objectAnimator2.setInterpolator(bounceInterpolator)
        objectAnimator2.setDuration(800).start()

        val objectAnimator3 = ObjectAnimator.ofFloat(line3, TRANSLATION_Y, 500f, 0f)
        objectAnimator3.setInterpolator(bounceInterpolator)
        objectAnimator3.setDuration(800).start()

        val objectAnimator4 = ObjectAnimator.ofFloat(line4, TRANSLATION_Y, 850f, 0f)
        objectAnimator4.setInterpolator(bounceInterpolator)
        objectAnimator4.setDuration(800).start()
    }

    private fun findViewById() {
        imageView11 = findViewById(R.id.imageView11)
        imageView12 = findViewById(R.id.imageView12)
        imageView13 = findViewById(R.id.imageView13)
        imageView14 = findViewById(R.id.imageView14)
        imageView21 = findViewById(R.id.imageView21)
        imageView22 = findViewById(R.id.imageView22)
        imageView23 = findViewById(R.id.imageView23)
        imageView24 = findViewById(R.id.imageView24)
        imageView31 = findViewById(R.id.imageView31)
        imageView32 = findViewById(R.id.imageView32)
        imageView33 = findViewById(R.id.imageView33)
        imageView34 = findViewById(R.id.imageView34)
        imageView41 = findViewById(R.id.imageView41)
        imageView42 = findViewById(R.id.imageView42)
        imageView43 = findViewById(R.id.imageView43)
        imageView44 = findViewById(R.id.imageView44)

        background = findViewById(R.id.background)
        when (backgroundStyle) {
            1 -> background.setImageResource(background1)
            2 -> background.setImageResource(background2)
            3 -> background.setImageResource(background3)
            4 -> background.setImageResource(background4)
        }

        firstPointsTextView = findViewById(R.id.points)
        secondPointsTextView = findViewById(R.id.topPoints)
        firstGlobalPointsTextView = findViewById(R.id.first_global_points_tv)
        secondGlobalPointsTextView = findViewById(R.id.second_global_points_tv)

        line1 = findViewById(R.id.line_1)
        line2 = findViewById(R.id.line_2)
        line3 = findViewById(R.id.line_3)
        line4 = findViewById(R.id.line_4)
    }

    private fun setOnClickListener() {
        imageView11.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView11, theCard)
        }
        imageView12.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView12, theCard)
        }
        imageView13.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView13, theCard)
        }
        imageView14.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView14, theCard)
        }
        imageView21.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView21, theCard)
        }
        imageView22.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView22, theCard)
        }
        imageView23.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView23, theCard)
        }
        imageView24.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView24, theCard)
        }
        imageView31.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView31, theCard)
        }
        imageView32.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView32, theCard)
        }
        imageView33.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView33, theCard)
        }
        imageView34.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView34, theCard)
        }
        imageView41.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView41, theCard)
        }
        imageView42.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView42, theCard)
        }
        imageView43.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView43, theCard)
        }
        imageView44.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView44, theCard)

        }

    }

    private fun showCardImage(imageView: ImageView, image: Int) {
        imageView.animate().withLayer()
            .rotationY(90f)
            .setDuration(150)
            .withEndAction(
                Runnable() {
                    imageView.setImageResource(image)

                    imageView.setRotationY(-90f)
                    imageView.animate().withLayer()
                        .rotationY(0f)
                        .setDuration(150)
                        .start()
                }
            ).start()
    }

    private fun showBackCardImage(vararg imageView: ImageView) {
        imageView.forEach {
            it.animate().withLayer()
                .rotationY(90f)
                .setDuration(150)
                .withEndAction(
                    Runnable() {
                        it.setImageResource(cardBack)

                        it.setRotationY(-90f)
                        it.animate().withLayer()
                            .rotationY(0f)
                            .setDuration(150)
                            .start()
                    }
                ).start()
        }

    }

    private fun startDoneAnimation(vararg imageView: ImageView){
        imageView.forEach {
            it.animate().withLayer()
                .scaleX(0.1f)
                .scaleXBy(0.3f)
                .scaleY(0.1f)
                .scaleYBy(0.3f)
                .setDuration(600)
                .withEndAction(
                    Runnable() {
                        it.animate().withLayer()
                            .scaleX (0.001f)
                            .scaleY(0.001f)
                            .setDuration(400)
                            .start()
                    }
                ).start()
        }
    }

    private fun startNotAnimation(vararg imageView: ImageView){
        imageView.forEach {
            it.animate().withLayer()
                .rotationY(40f)
                .setDuration(150)
                .withEndAction(
                    Runnable() {
                        it.setRotationY(-40f)
                        it.animate().withLayer()
                            .rotationY(0f)
                            .setDuration(150)
                            .start()
                    }
                ).start()
        }
    }

    private fun doStuff(imageView: ImageView, card: Int) {
        startVibration()

        when (cardsArray[card]) {
            11 -> showCardImage(imageView, image11)
            12 -> showCardImage(imageView, image12)
            13 -> showCardImage(imageView, image13)
            14 -> showCardImage(imageView, image14)
            15 -> showCardImage(imageView, image15)
            16 -> showCardImage(imageView, image16)
            17 -> showCardImage(imageView, image17)
            18 -> showCardImage(imageView, image18)
            21 -> showCardImage(imageView, image21)
            22 -> showCardImage(imageView, image22)
            23 -> showCardImage(imageView, image23)
            24 -> showCardImage(imageView, image24)
            25 -> showCardImage(imageView, image25)
            26 -> showCardImage(imageView, image26)
            27 -> showCardImage(imageView, image27)
            28 -> showCardImage(imageView, image28)

        }

        if (cardNumber == 1) {
            firstImage = imageView
            firstCard = cardsArray[card]
            if (firstCard!! > 20) {
                firstCard = firstCard!! - 10
            }

            cardNumber = 2
            clickedFirst = card

            imageView.isEnabled = false

        } else if (cardNumber == 2) {
            secondImage = imageView
            secondCard = cardsArray[card]
            if (secondCard!! > 20) {
                secondCard = secondCard!! - 10
            }
            cardNumber = 1
            clickedSecond = card

            imageViewIsEnabled(false)

            if (firstCard == secondCard) {
                startSoundDone()
                val handler = Handler()
                handler.postDelayed(Runnable {
                    startDoneAnimation(firstImage, secondImage)
                }, 300)


            } else {
                startSoundNot()
                val handler = Handler()
                handler.postDelayed(Runnable {
                    startNotAnimation(firstImage, secondImage)

                }, 300)

            }

            val handler = Handler()
            handler.postDelayed(Runnable {
                calculate()
            }, 1300)

        }

    }

    private fun calculate() {
        if (firstCard == secondCard) {
            when (clickedFirst) {
                0 -> imageView11.visibility = View.INVISIBLE
                1 -> imageView12.visibility = View.INVISIBLE
                2 -> imageView13.visibility = View.INVISIBLE
                3 -> imageView14.visibility = View.INVISIBLE
                4 -> imageView21.visibility = View.INVISIBLE
                5 -> imageView22.visibility = View.INVISIBLE
                6 -> imageView23.visibility = View.INVISIBLE
                7 -> imageView24.visibility = View.INVISIBLE
                8 -> imageView31.visibility = View.INVISIBLE
                9 -> imageView32.visibility = View.INVISIBLE
                10 -> imageView33.visibility = View.INVISIBLE
                11 -> imageView34.visibility = View.INVISIBLE
                12 -> imageView41.visibility = View.INVISIBLE
                13 -> imageView42.visibility = View.INVISIBLE
                14 -> imageView43.visibility = View.INVISIBLE
                15 -> imageView44.visibility = View.INVISIBLE
            }

            when (clickedSecond) {
                0 -> imageView11.visibility = View.INVISIBLE
                1 -> imageView12.visibility = View.INVISIBLE
                2 -> imageView13.visibility = View.INVISIBLE
                3 -> imageView14.visibility = View.INVISIBLE
                4 -> imageView21.visibility = View.INVISIBLE
                5 -> imageView22.visibility = View.INVISIBLE
                6 -> imageView23.visibility = View.INVISIBLE
                7 -> imageView24.visibility = View.INVISIBLE
                8 -> imageView31.visibility = View.INVISIBLE
                9 -> imageView32.visibility = View.INVISIBLE
                10 -> imageView33.visibility = View.INVISIBLE
                11 -> imageView34.visibility = View.INVISIBLE
                12 -> imageView41.visibility = View.INVISIBLE
                13 -> imageView42.visibility = View.INVISIBLE
                14 -> imageView43.visibility = View.INVISIBLE
                15 -> imageView44.visibility = View.INVISIBLE
            }

            if (turn == 1) {
                firstPlayerPoints++
                firstPointsTextView.setText("$firstPlayerPoints")
            } else if (turn == 2) {
                secondPlayerPoints++
                secondPointsTextView.setText("$secondPlayerPoints")
            }
        } else {
            showBackCardImage(firstImage, secondImage)

            if (turn == 1) {
                turn = 2
                firstPointsTextView.setTextColor(Color.GRAY)
                secondPointsTextView.setTextColor(Color.GREEN)
            } else if (turn == 2) {
                turn = 1
                firstPointsTextView.setTextColor(Color.GREEN)
                secondPointsTextView.setTextColor(Color.GRAY)
            }
        }
        imageViewIsEnabled(true)
        checkEndGame()

    }

    private fun startVibration() {
        if (vibrationCheck()) {
            val vibe = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibe.vibrate(40)
        }
    }

    private fun startSoundDone() {
        if (musicCheck()) {
            soundPlayerDone = MediaPlayer.create(this, R.raw.done)
            soundPlayerDone.start()
        }
    }

    private fun startSoundNot() {
        if (musicCheck()) {
            soundPlayerNot = MediaPlayer.create(this, R.raw.not)
            soundPlayerNot.start()
        }
    }

    private fun musicCheck(): Boolean = sp.getBoolean("music", true)

    private fun vibrationCheck(): Boolean = sp.getBoolean("vibration", true)

    private fun imageViewIsEnabled(b: Boolean) {
        imageView11.isEnabled = b
        imageView12.isEnabled = b
        imageView13.isEnabled = b
        imageView14.isEnabled = b
        imageView21.isEnabled = b
        imageView22.isEnabled = b
        imageView23.isEnabled = b
        imageView24.isEnabled = b
        imageView31.isEnabled = b
        imageView32.isEnabled = b
        imageView33.isEnabled = b
        imageView34.isEnabled = b
        imageView41.isEnabled = b
        imageView42.isEnabled = b
        imageView43.isEnabled = b
        imageView44.isEnabled = b
    }

    private fun checkEndGame() {
        if (imageView11.visibility == View.INVISIBLE && imageView12.visibility == View.INVISIBLE &&
            imageView13.visibility == View.INVISIBLE && imageView14.visibility == View.INVISIBLE &&
            imageView21.visibility == View.INVISIBLE && imageView22.visibility == View.INVISIBLE &&
            imageView23.visibility == View.INVISIBLE && imageView24.visibility == View.INVISIBLE &&
            imageView31.visibility == View.INVISIBLE && imageView32.visibility == View.INVISIBLE &&
            imageView33.visibility == View.INVISIBLE && imageView34.visibility == View.INVISIBLE &&
            imageView41.visibility == View.INVISIBLE && imageView42.visibility == View.INVISIBLE &&
            imageView43.visibility == View.INVISIBLE && imageView44.visibility == View.INVISIBLE
        ) {

            var message: String = ""
            if (firstPlayerPoints == secondPlayerPoints) {
                message = "It's a draw!"
            }
            if (firstPlayerPoints > secondPlayerPoints) {
                message = "First Player Win!"
            }

            if (firstPlayerPoints < secondPlayerPoints) {
                message = "Second Player Win!"
            }

            AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.game_over)
                .setMessage(message)
                .setPositiveButton(
                    R.string.continue_game,
                    DialogInterface.OnClickListener { dialog, which ->
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        intent.putExtra(LOAD_GAME, 2)
                        if (backgroundStyle == 4) {
                            backgroundStyle = 1
                        } else backgroundStyle++

                        if (firstPlayerPoints == secondPlayerPoints) {
                            firstPlayerGlobalPoints++
                            secondPlayerGlobalPoints++
                            firstGlobalPointsTextView.setText("$firstPlayerGlobalPoints")
                            secondGlobalPointsTextView.setText("$secondPlayerGlobalPoints")
                        }
                        if (firstPlayerPoints > secondPlayerPoints) {
                            firstPlayerGlobalPoints++
                            firstGlobalPointsTextView.setText("$firstPlayerGlobalPoints")
                        }

                        if (firstPlayerPoints < secondPlayerPoints) {
                            secondPlayerGlobalPoints++
                            secondGlobalPointsTextView.setText("$secondPlayerGlobalPoints")
                        }

                        startActivity(intent)
                        finish()
                    })
                .setNegativeButton(R.string.exit, DialogInterface.OnClickListener { dialog, which ->
                    finish()
                }).show()
        }


    }

    override fun onResume() {
        super.onResume()

        if (musicCheck()) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music2)
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        } else try {
            mediaPlayer.stop()
        } catch (e: Exception) {
        }

        val intent = getIntent()
        val isLoadGame = intent.getIntExtra(LOAD_GAME, 0)
        if (isLoadGame == 1) {
            loadGame()
            checkEndGame()
        }
        if (isLoadGame == 2) {
            contimueGame()
        }

    }

    private fun setVisible() {
        imageView11.visibility = View.VISIBLE
        imageView12.visibility = View.VISIBLE
        imageView13.visibility = View.VISIBLE
        imageView14.visibility = View.VISIBLE
        imageView21.visibility = View.VISIBLE
        imageView22.visibility = View.VISIBLE
        imageView23.visibility = View.VISIBLE
        imageView24.visibility = View.VISIBLE
        imageView31.visibility = View.VISIBLE
        imageView32.visibility = View.VISIBLE
        imageView33.visibility = View.VISIBLE
        imageView34.visibility = View.VISIBLE
        imageView41.visibility = View.VISIBLE
        imageView42.visibility = View.VISIBLE
        imageView43.visibility = View.VISIBLE
        imageView44.visibility = View.VISIBLE
    }


    override fun onPause() {
        super.onPause()

        saveInfo()

        try {
            mediaPlayer.stop()
            soundPlayerNot.stop()
            soundPlayerDone.stop()
        } catch (e: Exception) {
        }
    }

    private fun contimueGame() {
        setVisible()
        firstPlayerPoints = 0
        secondPlayerPoints = 0
        firstPointsTextView.setText("$firstPlayerPoints")
        secondPointsTextView.setText("$secondPlayerPoints")
        firstPlayerGlobalPoints = settings.getInt(FIRST_PLAYER_GLOBAL_POINTS, 0)
        secondPlayerGlobalPoints = settings.getInt(SECOND_PLAYER_GLOBAL_POINTS, 0)
        firstGlobalPointsTextView.setText("$firstPlayerGlobalPoints")
        secondGlobalPointsTextView.setText("$secondPlayerGlobalPoints")
        turn = settings.getInt(TURN, 1)
        if (turn == 2) {
            firstPointsTextView.setTextColor(Color.GRAY)
            secondPointsTextView.setTextColor(Color.GREEN)
        }
    }

    private fun loadGame() {
        try {
            firstPlayerGlobalPoints = settings.getInt(FIRST_PLAYER_GLOBAL_POINTS, 0)
            secondPlayerGlobalPoints = settings.getInt(SECOND_PLAYER_GLOBAL_POINTS, 0)
            firstGlobalPointsTextView.setText("$firstPlayerGlobalPoints")
            secondGlobalPointsTextView.setText("$secondPlayerGlobalPoints")
            firstPlayerPoints = settings.getInt(FIRST_PLAYER_POINTS, 0)
            secondPlayerPoints = settings.getInt(SECOND_PLAYER_POINTS, 0)
            firstPointsTextView.setText("$firstPlayerPoints")
            secondPointsTextView.setText("$secondPlayerPoints")

            val turnsType = object : TypeToken<List<Int>>() {}.type
            cardsArray = gson.fromJson(settings.getString(ARRAY, ""), turnsType)

            imageView11.visibility = settings.getInt(IMAGE_VIEW_11_IS_VISIBLE, 1)
            imageView12.visibility = settings.getInt(IMAGE_VIEW_12_IS_VISIBLE, 1)
            imageView13.visibility = settings.getInt(IMAGE_VIEW_13_IS_VISIBLE, 1)
            imageView14.visibility = settings.getInt(IMAGE_VIEW_14_IS_VISIBLE, 1)
            imageView21.visibility = settings.getInt(IMAGE_VIEW_21_IS_VISIBLE, 1)
            imageView22.visibility = settings.getInt(IMAGE_VIEW_22_IS_VISIBLE, 1)
            imageView23.visibility = settings.getInt(IMAGE_VIEW_23_IS_VISIBLE, 1)
            imageView24.visibility = settings.getInt(IMAGE_VIEW_24_IS_VISIBLE, 1)
            imageView31.visibility = settings.getInt(IMAGE_VIEW_31_IS_VISIBLE, 1)
            imageView32.visibility = settings.getInt(IMAGE_VIEW_32_IS_VISIBLE, 1)
            imageView33.visibility = settings.getInt(IMAGE_VIEW_33_IS_VISIBLE, 1)
            imageView34.visibility = settings.getInt(IMAGE_VIEW_34_IS_VISIBLE, 1)
            imageView41.visibility = settings.getInt(IMAGE_VIEW_41_IS_VISIBLE, 1)
            imageView42.visibility = settings.getInt(IMAGE_VIEW_42_IS_VISIBLE, 1)
            imageView43.visibility = settings.getInt(IMAGE_VIEW_43_IS_VISIBLE, 1)
            imageView44.visibility = settings.getInt(IMAGE_VIEW_44_IS_VISIBLE, 1)

            turn = settings.getInt(TURN, 1)
            if (turn == 2) {
                firstPointsTextView.setTextColor(Color.GRAY)
                secondPointsTextView.setTextColor(Color.GREEN)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "LOADING ERROR", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveInfo() {

        editor = settings.edit()
        editor.putInt(FIRST_PLAYER_GLOBAL_POINTS, firstPlayerGlobalPoints)
        editor.putInt(SECOND_PLAYER_GLOBAL_POINTS, secondPlayerGlobalPoints)
        editor.putInt(FIRST_PLAYER_POINTS, firstPlayerPoints)
        editor.putInt(SECOND_PLAYER_POINTS, secondPlayerPoints)
        editor.putInt(TURN, turn)
        val jsonArray = gson.toJson(cardsArray)
        editor.putString(ARRAY, jsonArray)
        editor.putInt(IMAGE_VIEW_11_IS_VISIBLE, imageView11.visibility)
        editor.putInt(IMAGE_VIEW_12_IS_VISIBLE, imageView12.visibility)
        editor.putInt(IMAGE_VIEW_13_IS_VISIBLE, imageView13.visibility)
        editor.putInt(IMAGE_VIEW_14_IS_VISIBLE, imageView14.visibility)
        editor.putInt(IMAGE_VIEW_21_IS_VISIBLE, imageView21.visibility)
        editor.putInt(IMAGE_VIEW_22_IS_VISIBLE, imageView22.visibility)
        editor.putInt(IMAGE_VIEW_23_IS_VISIBLE, imageView23.visibility)
        editor.putInt(IMAGE_VIEW_24_IS_VISIBLE, imageView24.visibility)
        editor.putInt(IMAGE_VIEW_31_IS_VISIBLE, imageView31.visibility)
        editor.putInt(IMAGE_VIEW_32_IS_VISIBLE, imageView32.visibility)
        editor.putInt(IMAGE_VIEW_33_IS_VISIBLE, imageView33.visibility)
        editor.putInt(IMAGE_VIEW_34_IS_VISIBLE, imageView34.visibility)
        editor.putInt(IMAGE_VIEW_41_IS_VISIBLE, imageView41.visibility)
        editor.putInt(IMAGE_VIEW_42_IS_VISIBLE, imageView42.visibility)
        editor.putInt(IMAGE_VIEW_43_IS_VISIBLE, imageView43.visibility)
        editor.putInt(IMAGE_VIEW_44_IS_VISIBLE, imageView44.visibility)
        editor.apply()
    }
 
}


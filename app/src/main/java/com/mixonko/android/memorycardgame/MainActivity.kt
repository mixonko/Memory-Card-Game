package com.mixonko.android.memorycardgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import java.util.*

class MainActivity : AppCompatActivity() {

    val cardBack = R.drawable.card_back

    val image11 = R.drawable.ic_image11
    val image12 = R.drawable.ic_image12
    val image13 = R.drawable.ic_image13
    val image14 = R.drawable.ic_image14
    val image15 = R.drawable.ic_image15
    val image16 = R.drawable.ic_image16
    val image17 = R.drawable.ic_image17
    val image18 = R.drawable.ic_image18
    val image21 = R.drawable.ic_image21
    val image22 = R.drawable.ic_image22
    val image23 = R.drawable.ic_image23
    val image24 = R.drawable.ic_image24
    val image25 = R.drawable.ic_image25
    val image26 = R.drawable.ic_image26
    val image27 = R.drawable.ic_image27
    val image28 = R.drawable.ic_image28

    lateinit var imageView11: ImageView
    lateinit var imageView12: ImageView
    lateinit var imageView13: ImageView
    lateinit var imageView14: ImageView
    lateinit var imageView21: ImageView
    lateinit var imageView22: ImageView
    lateinit var imageView23: ImageView
    lateinit var imageView24: ImageView
    lateinit var imageView31: ImageView
    lateinit var imageView32: ImageView
    lateinit var imageView33: ImageView
    lateinit var imageView34: ImageView
    lateinit var imageView41: ImageView
    lateinit var imageView42: ImageView
    lateinit var imageView43: ImageView
    lateinit var imageView44: ImageView

    lateinit var pointsTextView: TextView
    lateinit var topPointsTextView: TextView

    var firstCard: Int? = 0
    var secondCard: Int? = 0

    var clickedFirst: Int? = 0
    var clickedSecond: Int? = 0

    var cardNumber: Int = 1

    var turn: Int? = 0
    var points: Int = 0
    var topPoints: Int = 0

    val cardsArray =
        mutableListOf(11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 24, 25, 26, 27, 28)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        pointsTextView = findViewById(R.id.points)
        topPointsTextView = findViewById(R.id.topPoints)

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

//        Collections.shuffle(cardsArray)

        setOnClickListener()
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

    private fun doStuff(imageView: ImageView, card: Int) {
        when (cardsArray[card]){
            11 -> imageView.setImageResource(image11)
            12 -> imageView.setImageResource(image12)
            13 -> imageView.setImageResource(image13)
            14 -> imageView.setImageResource(image14)
            15 -> imageView.setImageResource(image15)
            16 -> imageView.setImageResource(image16)
            17 -> imageView.setImageResource(image17)
            18 -> imageView.setImageResource(image18)
            21 -> imageView.setImageResource(image21)
            22 -> imageView.setImageResource(image22)
            23 -> imageView.setImageResource(image23)
            24 -> imageView.setImageResource(image24)
            25 -> imageView.setImageResource(image25)
            26 -> imageView.setImageResource(image26)
            27 -> imageView.setImageResource(image27)
            28 -> imageView.setImageResource(image28)

        }

        if(cardNumber == 1){
            firstCard = cardsArray[card]
            if (firstCard!! > 20){
                firstCard = firstCard!! - 10
            }

            cardNumber = 2
            clickedFirst = card

            imageView.isEnabled = false

        }else if (cardNumber == 2){
            secondCard = cardsArray[card]
            if (secondCard!! > 20){
                secondCard = secondCard!! - 10
            }
            cardNumber = 1
            clickedSecond = card

            imageView11.isEnabled = false
            imageView12.isEnabled = false
            imageView13.isEnabled = false
            imageView14.isEnabled = false
            imageView21.isEnabled = false
            imageView22.isEnabled = false
            imageView23.isEnabled = false
            imageView24.isEnabled = false
            imageView31.isEnabled = false
            imageView32.isEnabled = false
            imageView33.isEnabled = false
            imageView34.isEnabled = false
            imageView41.isEnabled = false
            imageView42.isEnabled = false
            imageView43.isEnabled = false
            imageView44.isEnabled = false

            val handler = Handler()
            handler.postDelayed(Runnable {
                calculate()
            },500)

        }

    }

    private fun calculate() {
        if (firstCard == secondCard){
            when (clickedFirst){
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
                10 ->imageView33.visibility = View.INVISIBLE
                11 ->imageView34.visibility = View.INVISIBLE
                12 ->imageView41.visibility = View.INVISIBLE
                13 ->imageView42.visibility = View.INVISIBLE
                14 ->imageView43.visibility = View.INVISIBLE
                15 ->imageView44.visibility = View.INVISIBLE
            }

            when (clickedSecond){
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
                10 ->imageView33.visibility = View.INVISIBLE
                11 ->imageView34.visibility = View.INVISIBLE
                12 ->imageView41.visibility = View.INVISIBLE
                13 ->imageView42.visibility = View.INVISIBLE
                14 ->imageView43.visibility = View.INVISIBLE
                15 ->imageView44.visibility = View.INVISIBLE
            }

            if (turn == 1){
                points++
                pointsTextView.setText("$points")
            }else if(turn == 2){
                topPoints++
                topPointsTextView.setText("$topPoints")
            }
        } else {
            imageView11.setImageResource(cardBack)
                imageView12.setImageResource(cardBack)
                imageView13.setImageResource(cardBack)
                imageView14.setImageResource(cardBack)
                imageView21.setImageResource(cardBack)
                imageView22.setImageResource(cardBack)
                imageView23.setImageResource(cardBack)
                imageView24.setImageResource(cardBack)
                imageView31.setImageResource(cardBack)
                imageView32.setImageResource(cardBack)
                imageView33.setImageResource(cardBack)
                imageView34.setImageResource(cardBack)
                imageView41.setImageResource(cardBack)
                imageView42.setImageResource(cardBack)
                imageView43.setImageResource(cardBack)
                imageView44.setImageResource(cardBack)


        }
        imageView11.isEnabled = true
        imageView12.isEnabled = true
        imageView13.isEnabled = true
        imageView14.isEnabled = true
        imageView21.isEnabled = true
        imageView22.isEnabled = true
        imageView23.isEnabled = true
        imageView24.isEnabled = true
        imageView31.isEnabled = true
        imageView32.isEnabled = true
        imageView33.isEnabled = true
        imageView34.isEnabled = true
        imageView41.isEnabled = true
        imageView42.isEnabled = true
        imageView43.isEnabled = true
        imageView44.isEnabled = true

        chechEndGame()

    }

    private fun chechEndGame() {

    }

}

package com.mixonko.android.memorycardgame

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class startMenuActivity : AppCompatActivity() {

    lateinit var newGame: Button
    lateinit var loadGame: Button
    lateinit var settings: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_menu)

        newGame = findViewById(R.id.new_game)
        loadGame = findViewById(R.id.load_game)
        settings = findViewById(R.id.settings)
    }
}

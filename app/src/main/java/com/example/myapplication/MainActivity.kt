package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var answer = 0
    var isGameOver = false
    var numOfAttempts = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        generateAnswer()
    }
    fun generateAnswer(){

        answer = Random.nextInt(1,25)

    }
    fun startOver (){

        isGameOver = false
        generateAnswer()
        numOfAttempts = 0
        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "Number is Hidden"

        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        submitButton.isEnabled = true

         val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Guess a Number Between 1 to 25"

        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        editTextGuess.text.clear()

    }
    fun  btnStartOverTapped(view: View){
        startOver()

    }
    fun btnSubmitTapped (view: View){
        val guess = getUsersGuess() ?: -999
        val textView = findViewById<TextView>(R.id.textView)

        if (guess !in 1..25){
            textView.text = "Your Guess must be between 1 to 25"
            return
        }
        var message = ""
        numOfAttempts++

        if (guess == answer){
            message = "Correct! Guess(es): $numOfAttempts"

        isGameOver = true
        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = answer.toString()

           val submitButton = findViewById<Button>(R.id.buttonSubmit)
           submitButton.isEnabled = false

        }else{
            message = if (guess < answer) "Guess Too Low!" else "Guess Too High!"
        }

        textView.text = message




    }
    fun getUsersGuess(): Int? {
        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        val usersGuess = editTextGuess.text.toString()

        var guessAsInt = 0

        try {
            guessAsInt = usersGuess.toInt()
        }
        catch (e:Exception){
            return null
        }
        return guessAsInt

    }
}
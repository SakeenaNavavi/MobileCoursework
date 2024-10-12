package com.example.coursework

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.animation.Animation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Flashing : AppCompatActivity() {
    var imageviewflashing: ImageView? = null
    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashing)
        imageviewflashing = findViewById<ImageView>(R.id.imageactivity)
        startSiren()
        startLights()
    }

    fun startSiren() {
        mediaPlayer = MediaPlayer.create(this, R.raw.police_siren)
        mediaPlayer!!.start()
        mediaPlayer!!.isLooping = true
    }

    // @SuppressLint :- android.annotation.SuppressLint. Indicates that Lint should ignore the specified warnings for the annotated element
    @SuppressLint("WrongConstant")
    fun startLights() {
        val anim = ObjectAnimator.ofInt(imageviewflashing, "BackgroundColor", Color.RED, Color.BLUE)
        anim.setDuration(120)
        anim.setEvaluator(ArgbEvaluator()) // read by tapping on it
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        anim.start()
    }

    override fun onBackPressed() {
        mediaPlayer!!.stop()
        super.onBackPressed()
    }
}
package com.fearaujo.lottielocalanim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieCompositionFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    private companion object {
        // 1 MB
        const val BUFFER_SIZE = 1024
    }

    private val localStorage = LocalCacheStorage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btShowAnimation.setOnClickListener {
            loadAnimationFromLocalPathAsync(localStorage.fetchAnimationPathFromCache())
            lottieAnimationView.playAnimation()
        }

    }

    private fun loadAnimationFromLocalPath(path: String) {
        val inputStream = BufferedInputStream(FileInputStream(path), BUFFER_SIZE)
        val lottieResult = LottieCompositionFactory.fromJsonInputStreamSync(inputStream, null)
        val value = lottieResult.value

        value?.let {
            lottieAnimationView.setComposition(it)
        }
    }

    private fun loadAnimationFromLocalPathAsync(path: String) {
        val inputStream = BufferedInputStream(FileInputStream(path), BUFFER_SIZE)
        val task = LottieCompositionFactory.fromJsonInputStream(inputStream, null)
        task.addListener {
            lottieAnimationView.setComposition(it)
        }
    }


}

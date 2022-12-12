package com.example.poultrymanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.os.Handler as AndroidOsHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    supportActionBar?.hide()
    window.setFlags(
    WindowManager.LayoutParams.FLAG_FULLSCREEN,
    WindowManager.LayoutParams.FLAG_FULLSCREEN
    )

    AndroidOsHandler().postDelayed({
        val intent = Intent( this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }, 3000)
}
}

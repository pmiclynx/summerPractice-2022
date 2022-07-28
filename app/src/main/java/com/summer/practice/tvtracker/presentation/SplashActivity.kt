package com.summer.practice.tvtracker.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.summer.practice.tvtracker.R
import com.summer.practice.tvtracker.presentation.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide();
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            val user = Firebase.auth.currentUser
            if (user != null) {
                val intent= Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                intent= Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()

            } },2000)
    }
}
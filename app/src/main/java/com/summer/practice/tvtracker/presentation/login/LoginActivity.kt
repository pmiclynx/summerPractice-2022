package com.summer.practice.tvtracker.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.summer.practice.tvtracker.presentation.MainActivity
import com.summer.practice.tvtracker.presentation.registration.RegistrationActivity
import com.summer.practice.tvtracker.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {
            if (binding.emailField.text.isEmpty() or binding.passwordField.text.isEmpty()) {
                val text = "Please fill all fields!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            val emailText=binding.emailField.getText().toString()
            if(!(Patterns.EMAIL_ADDRESS.matcher(emailText).matches())){
                val text = "Email invalid"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            //TODO authentication
            firebaseAuth.signInWithEmailAndPassword(binding.emailField.text.toString(), binding.passwordField.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                    Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()
            }
        }

        binding.linkToRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
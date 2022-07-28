package com.summer.practice.tvtracker.presentation.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.summer.practice.tvtracker.presentation.MainActivity
import com.summer.practice.tvtracker.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.registerButton.setOnClickListener {
            if (binding.password.text.isEmpty() or binding.verifyPassword.text.isEmpty()
                or binding.username.text.isEmpty() or binding.emailAddress.text.isEmpty()) {
                val text = "Please fill all fields!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            if(!(binding.password.getText().toString().equals(binding.verifyPassword.getText().toString()))){
                val text = "Passwords aren't matching!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            val emailText=binding.emailAddress.getText().toString()
            if(!(Patterns.EMAIL_ADDRESS.matcher(emailText).matches())){
                val text = "Email invalid"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            //TODO authentication
             firebaseAuth.createUserWithEmailAndPassword(binding.emailAddress.text.toString(),binding.password.text.toString()).addOnCompleteListener {
                 if(it.isSuccessful){
                 val intent = Intent(this, MainActivity::class.java)
                 startActivity(intent)
                 finish()
                 }
                 else
                     Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()
             }
        }

    }

}
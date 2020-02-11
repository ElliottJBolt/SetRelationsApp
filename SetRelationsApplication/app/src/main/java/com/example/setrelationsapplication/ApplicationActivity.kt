package com.example.setrelationsapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.content_application.*

class ApplicationActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
     val profileName=intent.getStringExtra("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)
        setSupportActionBar(toolbar)


        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
            .addToBackStack(null)
            .commit()

    }

    fun getUser(){
        val profileName=intent.getStringExtra("user")

    }

    fun getDB(){
        val db = FirebaseFirestore.getInstance()

    }

}

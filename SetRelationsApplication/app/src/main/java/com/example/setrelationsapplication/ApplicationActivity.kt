package com.example.setrelationsapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.content_application.*

class ApplicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)
        setSupportActionBar(toolbar)

        val db = FirebaseFirestore.getInstance()


        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
            .addToBackStack(null)
            .commit()





    }

}

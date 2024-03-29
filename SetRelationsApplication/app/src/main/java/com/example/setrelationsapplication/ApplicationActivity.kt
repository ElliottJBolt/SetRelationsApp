package com.example.setrelationsapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.content_application.*

/**
 * Class to create the home activity
 *
 */
class ApplicationActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
            .commit()



    }

    /**
     * function to get the user
     *
     * @return The users email adress
     */
    fun getUser():String{
        val profileName=intent.getStringExtra("user")
        Log.d("user", "$profileName")
        return profileName

    }





}

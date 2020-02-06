package com.example.setrelationsapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()


        logInButton.setOnClickListener {
            signIn(emailText.text.toString(),passwordText.text.toString())
            startActivity(Intent(this, ApplicationActivity::class.java))

        }

        createAccountButton.setOnClickListener {
            createAccount(emailText.text.toString(),passwordText.text.toString())
        }





        /**val sizeOfSet = Set_Relation_Generation.setGenerator()
        var j = 0
        setText.text = "A = {"
        for (x in sizeOfSet){
        if (j == sizeOfSet.size - 1){
        setText.text = setText.text as String + "${sizeOfSet.elementAt(j)}"
        }else {
        setText.text = setText.text as String + "${sizeOfSet.elementAt(j)},"
        j++
        }
        }
        setText.text = setText.text as String + "}"


        val newRelation = Set_Relation_Generation.relationGenerator(sizeOfSet)
        var i = 0
        relationText.text = "R = {"

        for(x in newRelation){
        if(i == 0){
        relationText.text = relationText.text as String + "(${newRelation.elementAt(i)},"
        }
        else if ( i.rem(2) == 1  ) {
        relationText.text = relationText.text as String + "${newRelation.elementAt(i)})"
        }
        else{

        relationText.text = relationText.text as String + ",(${newRelation.elementAt(i)},"
        }
        i++
        }
        relationText.text = relationText.text as String + "}"


        var reflexiveRelation = Set_Relation_Generation.reflexive(sizeOfSet)
        reflexiveTest.text = "{"
        var l = 0
        for(x in reflexiveRelation){
        if(l == 0){
        reflexiveTest.text = reflexiveTest.text as String + "(${reflexiveRelation.elementAt(l)},"
        }
        else if ( l.rem(2) == 1  ) {
        reflexiveTest.text = reflexiveTest.text as String + "${reflexiveRelation.elementAt(l)})"
        }
        else{

        reflexiveTest.text = reflexiveTest.text as String + ",(${reflexiveRelation.elementAt(l)},"
        }
        l++
        }
        reflexiveTest.text = reflexiveTest.text as String + "}"

        var symmetricRelation = Set_Relation_Generation.symmetric(sizeOfSet)
        symmetricTest.text = "$symmetricRelation"

        var transitiveRelation = Set_Relation_Generation.transitive(sizeOfSet)
        transitiveTest.text = "$transitiveRelation" */


    }

    fun signIn(email: String, password: String) {
        d("Elliott","signIn: $email")

        if (!validateForm()) {
            return
        }

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // [START_EXCLUDE]
                if (!task.isSuccessful) {

                }

                // [END_EXCLUDE]
            }
        // [END sign_in_with_email]
    }

    fun createAccount(email: String, password: String) {
        if(!validateForm()) {
            return
        }
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    d("Elliott","SignInWithEmail: Success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    d("Elliott","signInWithEmail: failure",task.exception)

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

            }
        // [END create_user_with_email]
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = emailText.text.toString()
        if (TextUtils.isEmpty(email)) {
            emailText.error = "Required."
            valid = false
        } else {
            emailText.error = null
        }

        val password = passwordText.text.toString()
        if (TextUtils.isEmpty(password)) {
            passwordText.error = "Required."
            valid = false
        } else {
            passwordText.error = null
        }

        return valid

    }

    private fun updateUI(user: FirebaseUser?){

        if (user != null) {
            Toast.makeText(baseContext, "Logging in.",
                Toast.LENGTH_SHORT).show()
            //startActivity(Intent(this, ApplicationActivity::class.java))

        }else{
            Toast.makeText(baseContext, "Account does not exist.",
                Toast.LENGTH_SHORT).show()
        }

    }


}

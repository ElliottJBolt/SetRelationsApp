package com.example.setrelationsapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val sizeOfSet = Set_Relation_Generation.setGenerator()
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
        transitiveTest.text = "$transitiveRelation"


    }


}

package com.example.setrelationsapplication

import android.util.Log.d
import kotlin.random.Random

object Set_Relation_Generation {
    fun setGenerator() : MutableList<Int>  {
        var setLength = Random.nextInt(2, 6)
        d("Elliott","${setLength}")
        var setValues: MutableList<Int> = mutableListOf<Int>()
        var x = 2
        do{
            val nextValue = Random.nextInt(0, 9)
            setValues.add(nextValue)
            x++

        }while (x <= setLength + 1)

        return setValues

    }

    fun reflexive() {

    }

    fun symmetric() {

    }

    fun transitive() {

    }
}
package com.example.setrelationsapplication

import kotlin.random.Random

class Set_Relation_Generator {
    fun setGenerator(){
        val setLength = Random.nextInt(2,6)
        val setValues: MutableList<Int> = mutableListOf<Int>()

        for(x in 2 until setLength ){
            val nextValue = Random.nextInt(0,9)
            setValues.add(nextValue)

        }

    }

    fun reflexive(){

    }
    fun symmetric(){

    }

    fun transitive() {

    }
}
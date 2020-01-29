package com.example.setrelationsapplication

import android.util.Log.d
import kotlin.random.Random

object Set_Relation_Generation {
    fun setGenerator() : MutableList<Int>  {
        val setLength = Random.nextInt(4, 8)
        val setValues: MutableList<Int> = mutableListOf<Int>()
        var x = 2
        do{
            var nextValue = Random.nextInt(0, 9)
            d("Elliott"," Original value $nextValue")
            var uniqueValue = checkRepeated(setValues,nextValue)
            setValues.add(uniqueValue)
            x++

        }while (x <= setLength + 1)

        return setValues

    }

    fun checkRepeated(values: MutableList<Int>,nextValue: Int): Int {
        var nextValue = nextValue
            if (values.contains(nextValue)){
                do{
                    nextValue = Random.nextInt(0, 9)
                    d("Elliott"," New value $nextValue")
                    checkRepeated(values,nextValue)

                }while (values.contains(nextValue))
            }else{
                return nextValue
            }
        return nextValue

    }

    fun relationGenerator(values: MutableList<Int>): MutableList<Int> {
        var lengthOfSet = values.size
        var createdRelation: MutableList<Int> = mutableListOf<Int>()
        var noOfRelationPairs = Random.nextInt(2,6)
        d("Elliott","noOfRelationPairs = ${noOfRelationPairs}")
        var y = 0

        do {
            var number = Random.nextInt(0,lengthOfSet)
            d("Elliott","number = ${number}")
            var orderedPairValue = values.elementAt(number)
            d("Elliott","orderedPairValues = ${orderedPairValue}")
            createdRelation.add(orderedPairValue)
            y++

        }while (y < noOfRelationPairs * 2)

        return  createdRelation
    }

    fun reflexive() {

    }

    fun symmetric() {

    }

    fun transitive() {

    }
}
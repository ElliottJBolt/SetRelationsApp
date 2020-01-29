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
        var noOfRelationPairs = Random.nextInt(3,7)
        var y = 0

        do {
            var number = Random.nextInt(0,lengthOfSet)
            var orderedPairValue = values.elementAt(number)
            createdRelation.add(orderedPairValue)
            y++

        }while (y < noOfRelationPairs * 2)

        return  createdRelation
    }

    fun reflexive(values: MutableList<Int>): MutableList<Int> {
        var lengthOfSet = values.size
        var refelexiveRelation: MutableList<Int> = mutableListOf<Int>()

        var noOfRelationPairs = Random.nextInt(3,7)

        var noOfNumbers = 2*noOfRelationPairs


        var y = 0

        var reflexiveNumber = Random.nextInt(0,lengthOfSet)


        do {
            var number = Random.nextInt(0,lengthOfSet)
            var orderedPairValue: Int

            if (reflexiveNumber.rem(2) == 1 && y == reflexiveNumber || reflexiveNumber.rem(2) == 1 && y == reflexiveNumber - 1 ){
                orderedPairValue = values.elementAt(reflexiveNumber)
            }else if(reflexiveNumber.rem(2) == 0 && y == reflexiveNumber || reflexiveNumber.rem(2) == 0  && y == reflexiveNumber + 1){
                orderedPairValue = values.elementAt(reflexiveNumber)
            }else{
                orderedPairValue = values.elementAt(number)
            }
            refelexiveRelation.add(orderedPairValue)
            
            y++

        }while (y < noOfNumbers)

        return refelexiveRelation

    }

    fun symmetric() {

    }

    fun transitive() {

    }
}
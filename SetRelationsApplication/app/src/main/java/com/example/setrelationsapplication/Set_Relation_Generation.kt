package com.example.setrelationsapplication

import android.util.Log.d
import kotlin.random.Random

object Set_Relation_Generation {
    fun setGenerator(): MutableList<Int> {
        val setLength = Random.nextInt(4, 6)
        val setValues: MutableList<Int> = mutableListOf<Int>()
        var x = 2
        do {
            var nextValue = Random.nextInt(0, 9)
            var uniqueValue = checkRepeated(setValues, nextValue)
            setValues.add(uniqueValue)
            x++

        } while (x <= setLength + 1)

        return setValues

    }

    fun checkRepeated(values: MutableList<Int>, nextValue: Int): Int {
        var nextValue = nextValue
        if (values.contains(nextValue)) {
            do {
                nextValue = Random.nextInt(0, 9)
                checkRepeated(values, nextValue)

            } while (values.contains(nextValue))
        } else {
            return nextValue
        }
        return nextValue

    }

    fun relationGenerator(values: MutableList<Int>): MutableList<Int> {
        var lengthOfSet = values.size
        var createdRelation: MutableList<Int> = mutableListOf<Int>()
        var noOfRelationPairs = Random.nextInt(3, 7)
        var y = 0

        do {
            var number = Random.nextInt(0, lengthOfSet)
            var orderedPairValue = values.elementAt(number)
            createdRelation.add(orderedPairValue)
            y++

        } while (y < noOfRelationPairs * 2)

        return createdRelation
    }

    fun reflexive(values: MutableList<Int>): MutableList<Int> {
        var lengthOfSet = values.size
        var reflexiveRelation: MutableList<Int> = mutableListOf<Int>()

        var noOfRelationPairs = Random.nextInt(lengthOfSet, 8)
        d("Elliott", "no of relation pairs: $noOfRelationPairs")

        var x = 0
        var y = 0
        var number: Int
        var actualValue: Int

        var allReflexiveNum: MutableList<Int> = mutableListOf<Int>()

        do {
            allReflexiveNum.add(values.elementAt(x))
            d("Elliott", "$allReflexiveNum")
            x++
        } while (x < values.size)

        var allReflexNumLength = allReflexiveNum.size

        allReflexiveNum.shuffle()

        val randomNum = Random.nextInt(1,2)

        if (randomNum.rem(2) == 1){
            number = Random.nextInt(0, lengthOfSet)
            actualValue = values.elementAt(number)
            reflexiveRelation.add(actualValue)
            number = Random.nextInt(0, lengthOfSet)
            actualValue = values.elementAt(number)
            reflexiveRelation.add(actualValue)
        }

        do {
            var reflexiveNumber = allReflexiveNum.first()
            var orderedPairValue: Int

            orderedPairValue = reflexiveNumber
            reflexiveRelation.add(orderedPairValue)
            reflexiveRelation.add(orderedPairValue)

            allReflexiveNum.removeAt(0)
            y++



        } while (y < allReflexNumLength)

        if (allReflexNumLength < noOfRelationPairs) {

            number = Random.nextInt(0, lengthOfSet)
            actualValue = values.elementAt(number)
            reflexiveRelation.add(actualValue)

            number = Random.nextInt(0, lengthOfSet)
            actualValue = values.elementAt(number)
            reflexiveRelation.add(actualValue)

        }

        return reflexiveRelation

    }

    fun symmetric() {

    }

    fun transitive() {

    }
}
package com.example.setrelationsapplication

import android.util.Log
import kotlin.random.Random

object InputQuestion {

    fun reflexive(relation:MutableList<Int>):MutableList<Int>{
        var sizeOfSet = relation.size
        var value = Random.nextInt(0,sizeOfSet)
        var firstNum: Int
        var secondNum: Int
        var listOfValuesAndPos = mutableListOf<Int>()
        var reflexivePair = false

        do {


            if (value.rem(2) == 0) {
                firstNum = relation.elementAt(value)
                secondNum = relation.elementAt(value + 1)
                if (firstNum == secondNum) {
                    listOfValuesAndPos.add(firstNum)
                    listOfValuesAndPos.add(secondNum)
                    listOfValuesAndPos.add(value)
                    listOfValuesAndPos.add(value + 1)
                    reflexivePair = true
                }

            } else {
                firstNum = relation.elementAt(value - 1)
                secondNum = relation.elementAt(value)
                if (firstNum == secondNum) {
                    listOfValuesAndPos.add(firstNum)
                    listOfValuesAndPos.add(secondNum)
                    listOfValuesAndPos.add(value - 1)
                    listOfValuesAndPos.add(value)
                    reflexivePair = true

                }
            }
        }while (reflexivePair == false)

        return listOfValuesAndPos
    }

    fun symmetric(relation: MutableList<Int>):MutableList<Int>{
        var sizeOfSet = relation.size
        var i = 0
        var j = i + 3
        var firstNumFirstPair:Int
        var secondNumFirstPair:Int
        var firstNumSecPair:Int
        var secondNumSecPair:Int
        var listOfValuesAndPos = mutableListOf<Int>()

        do {
            j = i + 3

            firstNumFirstPair = relation.elementAt(i)
            secondNumFirstPair = relation.elementAt(i+1)

            do{
                Log.d("Test", "$j")
                firstNumSecPair = relation.elementAt(j-1)
                secondNumSecPair = relation.elementAt(j)



                if (firstNumFirstPair == secondNumSecPair && secondNumFirstPair == firstNumSecPair){

                    listOfValuesAndPos.add(firstNumFirstPair)
                    listOfValuesAndPos.add(secondNumSecPair)
                    listOfValuesAndPos.add(i)
                    listOfValuesAndPos.add(j - 1)
                    listOfValuesAndPos.add(secondNumFirstPair)
                    listOfValuesAndPos.add(firstNumSecPair)
                }
                j = j + 2

            }while (j < sizeOfSet )

            i = i + 2
        }while (i<sizeOfSet - 2)

        return listOfValuesAndPos
    }




}
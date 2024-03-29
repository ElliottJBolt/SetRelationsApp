package com.example.setrelationsapplication

import android.util.Log
import kotlin.random.Random

/**
 * Object to hold functions to choose hidden values
 */

object InputQuestion {


    /**
     * function to choose reflexive hidden value
     *
     * @param relation the relation which the value can be chosen from
     * @return A list of possible hidden values and their location
     */
    fun reflexive(relation: MutableList<Int>): MutableList<Int> {
        var sizeOfSet = relation.size
        var value = Random.nextInt(0, sizeOfSet)
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
            value = Random.nextInt(0, sizeOfSet)
        } while (reflexivePair == false)

        return listOfValuesAndPos
    }


    /**
     * Function to choose symmetric hidden values
     *
     * @param relation the relation that the values can be chosen from
     * @return the possible hidden values and their position
     */
    fun symmetric(relation: MutableList<Int>): MutableList<Int> {
        var sizeOfRelation = relation.size
        var i = 0
        var j:Int
        var firstNumFirstPair: Int
        var secondNumFirstPair: Int
        var firstNumSecPair: Int
        var secondNumSecPair: Int
        var listOfValuesAndPos = mutableListOf<Int>()

        do {
            j = i + 3

            firstNumFirstPair = relation.elementAt(i)
            secondNumFirstPair = relation.elementAt(i + 1)

            do {

                firstNumSecPair = relation.elementAt(j - 1)
                secondNumSecPair = relation.elementAt(j)



                if (firstNumFirstPair == secondNumSecPair && secondNumFirstPair == firstNumSecPair) {

                    listOfValuesAndPos.add(firstNumFirstPair)
                    listOfValuesAndPos.add(secondNumSecPair)
                    listOfValuesAndPos.add(i)
                    listOfValuesAndPos.add(j - 1)
                    listOfValuesAndPos.add(secondNumFirstPair)
                    listOfValuesAndPos.add(firstNumSecPair)
                    break
                }
                j = j + 2

            } while (j < sizeOfRelation)

            i = i + 2
        } while (i < sizeOfRelation - 2)

        return listOfValuesAndPos
    }

    /**
     * Function to choose transitive hidden values
     *
     * @param relation the relation the values can be chosen from
     * @return the possible hidden values and their position
     */

    fun transitive(relation: MutableList<Int>): MutableList<Int> {
        var whichNum = Random.nextInt(1, 4)
        var listOfValuesAndPos = mutableListOf<Int>()
        var num1: Int
        var pos1: Int
        var num2:Int
        var pos2:Int



        if (whichNum == 1){
            num1 = relation.elementAt(relation.size - 6)
            pos1 = relation.elementAt(relation.size - 3)
            num2 = relation.elementAt(relation.size -5)
            pos2 = relation.elementAt(relation.size - 3) +1


        }else if (whichNum ==2){
            num1 = relation.elementAt(relation.size - 5)
            pos1 = relation.elementAt(relation.size-2)
            num2 = relation.elementAt(relation.size - 4)
            pos2 = relation.elementAt(relation.size-2)+1

        }else{
            num1 = relation.elementAt(relation.size-6)
            pos1 = relation.elementAt(relation.size-1)
            num2 = relation.elementAt(relation.size - 4)
            pos2 = relation.elementAt(relation.size-1)+1

        }
        listOfValuesAndPos.add(num1)
        listOfValuesAndPos.add(num2)
        listOfValuesAndPos.add(pos1)
        listOfValuesAndPos.add(pos2)
        return listOfValuesAndPos
    }


}










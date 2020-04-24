package com.example.setrelationsapplication

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
        var j:Int
        var firstNum:Int
        var secondNum:Int
        var listOfValuesAndPos = mutableListOf<Int>()
        do {
            j = i +3
            firstNum = relation.elementAt(i)

            secondNum = relation.elementAt(j)

            if (firstNum == secondNum){
                listOfValuesAndPos.add(firstNum)
                listOfValuesAndPos.add(secondNum)
                listOfValuesAndPos.add(i)
                listOfValuesAndPos.add(j)

            }


            i++
        }while (i<sizeOfSet)

        return listOfValuesAndPos
    }






}
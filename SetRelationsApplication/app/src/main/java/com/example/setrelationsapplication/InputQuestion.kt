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
        var sizeOfRelation = relation.size
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
                    break
                }
                j = j + 2

            }while (j < sizeOfRelation )

            i = i + 2
        }while (i<sizeOfRelation - 2)

        return listOfValuesAndPos
    }

    fun transitive(relation: MutableList<Int>,a:Int,b:Int,c:Int):MutableList<Int>{
        var whichNum = Random.nextInt(1,4)
        var listOfValuesAndPos = mutableListOf<Int>()
        var i = 0
        var num1:Int
        var num2: Int
        Log.d("Toot", "$whichNum")
        Log.d("Toot", "$relation")


        if (whichNum == 1){
            whichNum = a
            do {
                num1 = relation.elementAt(i)

                if (num1 == a && i.rem(2)==0){
                    Log.d("Toot1", "$num1")
                        num2 = relation.elementAt(i+1)
                        if (num2 == b || num2 == c){
                            Log.d("Toot1", "$num2")
                            listOfValuesAndPos.add(num1)
                            listOfValuesAndPos.add(num2)
                            listOfValuesAndPos.add(i)
                            listOfValuesAndPos.add(i+1)
                            break

                        }

                }
                i++

            }while ( i < relation.size)

        }else if(whichNum == 2){
            whichNum = b
            do {
                num1 = relation.elementAt(i)
                if (num1 == b&& i.rem(2)==0){
                    Log.d("Toot2", "$num1")
                    num2 = relation.elementAt(i+1)
                    if (num2 == c){
                        Log.d("Toot2", "$num2")
                        listOfValuesAndPos.add(num1)
                        listOfValuesAndPos.add(num2)
                        listOfValuesAndPos.add(i)
                        listOfValuesAndPos.add(i+1)
                        break
                    }

                }else if (num1 == b && i.rem(2) ==1){
                    Log.d("Toot3", "$num1")
                    num2 = relation.elementAt(i-1)

                    if (num2 == a){
                        Log.d("Toot3", "$num2")
                        listOfValuesAndPos.add(num1)
                        listOfValuesAndPos.add(num2)
                        listOfValuesAndPos.add(i)
                        listOfValuesAndPos.add(i-1)
                        break
                    }
                }
                i++
            }while (i < relation.size)

        }else{
            whichNum = c
            do {
                num1 = relation.elementAt(i)
                if (num1 == c && i.rem(2)==1){
                    Log.d("Toot4", "$num1")
                    num2 = relation.elementAt(i-1)
                    if (num2 == a){
                        Log.d("Toot4", "$num2")
                        listOfValuesAndPos.add(num1)
                        listOfValuesAndPos.add(num2)
                        listOfValuesAndPos.add(i)
                        listOfValuesAndPos.add(i-1)
                        break


                    }else if(num2 == b){
                        Log.d("Toot5", "$num2")
                        listOfValuesAndPos.add(num1)
                        listOfValuesAndPos.add(num2)
                        listOfValuesAndPos.add(i)
                        listOfValuesAndPos.add(i-1)
                        break

                    }
                }
                i++

            }while (i<relation.size)

        }
        Log.d("Toot6", "$listOfValuesAndPos")

        return listOfValuesAndPos

    }




}
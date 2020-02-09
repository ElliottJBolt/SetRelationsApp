package com.example.setrelationsapplication

import android.util.Log.d
import kotlin.random.Random

object Set_Relation_Generation {
    /**
     * Creates a set to be used to form the relations
     * @return a list of numbers
     */
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

        var x = 0
        var y = 0
        var number: Int
        var actualValue: Int

        var allReflexiveNum: MutableList<Int> = mutableListOf<Int>()

        do {
            allReflexiveNum.add(values.elementAt(x))
            x++
        } while (x < values.size)

        var allReflexNumLength = allReflexiveNum.size

        allReflexiveNum.shuffle()

        val randomNum = Random.nextInt(1, 2)

        if (randomNum.rem(2) == 1) {
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

    fun symmetric(values: MutableList<Int>): MutableList<Int> {
        var lengthOfSet = values.size
        var symmetricRelation: MutableList<Int> = mutableListOf<Int>()

        var noOfRelationPairs = Random.nextInt(3, 7)
        var amountOfNumbers = noOfRelationPairs * 2
        var number = Random.nextInt(0, 9)

        do {
            symmetricRelation.add(number)
            number = Random.nextInt(0, 9)
        } while (symmetricRelation.size < amountOfNumbers)


        var selectNumber = Random.nextInt(0, lengthOfSet)
        var num1 = values.elementAt(selectNumber)
        selectNumber = Random.nextInt(0, lengthOfSet)
        var num2 = values.elementAt(selectNumber)

        if (num2 == num1) {
            do {
                selectNumber = Random.nextInt(0, lengthOfSet)
                num2 = selectNumber

            } while (num2 == num1)
        }
        d("Elliott", "num1: $num1")
        d("Elliott", "num2: $num2")

        var positionPair1 = Random.nextInt(0, amountOfNumbers / 2 - 1)
        symmetricRelation.set(positionPair1, num1)

        var numberOneFirst: Boolean
        d("Elliott", "position1: $positionPair1")


        if (positionPair1.rem(2) == 1) {
            symmetricRelation.set(positionPair1 - 1, num2)
            numberOneFirst = false
        } else {
            symmetricRelation.set(positionPair1 + 1, num2)
            numberOneFirst = true
        }

        var positionPair2 = Random.nextInt(amountOfNumbers / 2, amountOfNumbers - 1)

        if (positionPair2 == (positionPair1 + 1)) {
            do {
                positionPair2 = Random.nextInt(amountOfNumbers / 2, amountOfNumbers - 1)
            } while (positionPair2 == positionPair1 || positionPair2 == positionPair1 - 1 || positionPair2 == positionPair1 + 1)
        }

        if (numberOneFirst == false && positionPair2.rem(2) == 1) {
            do {
                positionPair2 = Random.nextInt(amountOfNumbers / 2, amountOfNumbers - 1)
            } while (positionPair2.rem(2) == 1)
        } else if (numberOneFirst == true && positionPair2.rem(2) == 0) {
            do {
                positionPair2 = Random.nextInt(amountOfNumbers / 2, amountOfNumbers - 1)
            } while (positionPair2.rem(2) == 0)
        }
        d("Elliott", "position2: $positionPair2")

        symmetricRelation.set(positionPair2, num2)

        if (positionPair2.rem(2) == 0 && numberOneFirst == false) {
            symmetricRelation.set(positionPair2, num1)
            symmetricRelation.set(positionPair2 + 1, num2)
            d("Elliott", "First if active")


        } else if (positionPair2.rem(2) == 1 && numberOneFirst == true) {
            symmetricRelation.set(positionPair2, num1)
            symmetricRelation.set(positionPair2 - 1, num2)
            d("Elliott", "Second if active")
        }

        return symmetricRelation

    }

    fun transitive(relation: MutableList<Int>): Boolean {
        //var lengthOfSet = values.size
        var transitiveRelation = relation

        var noOfRelationPairs = Random.nextInt(3, 5)

        var amountOfNumbers = transitiveRelation.size



        var number = Random.nextInt(0, 9)

        /**do {
            transitiveRelation.add(number)
            number = Random.nextInt(0, 9)
        } while (transitiveRelation.size < amountOfNumbers)*/

        var i = 0 //Position of b in the relation list
        var k: Int //Position of other b
        var j: MutableList<Int> = mutableListOf<Int>() //Variable to see if b's are the same

        var a: Int // a in relation
        var b: Int // b in relation
        var c: Int // c in relation

        var pos1 = 0 //position of first a in (a,c)
        var pos2 = 1 //position of first c in (a,c)
        var isTransitive = true //whether the relation is transitive or not

        var positionInJ = 0

        do {
            b = transitiveRelation.elementAt(i)
            d("Elliott", "b:$b")
            k = 1
            positionInJ = 0
            j.clear()

            pos1 = 0 
            pos2 = 1


            if (i.rem(2) == 0) {
                //Check through odd positions
                do {
                    j.add(transitiveRelation.elementAt(k))
                    k = k + 2

                } while (k < amountOfNumbers)
                d("Elliott", "odd j:$j")

                k = 1


                do {
                    if (j.elementAt(positionInJ) == b) {
                        a = transitiveRelation.elementAt(k - 1)
                        d("Elliott", "a:$a")
                        c = transitiveRelation.elementAt(i + 1)
                        d("Elliott", "a:$a")
                        do {
                            if (a != transitiveRelation.elementAt(pos1) && c != transitiveRelation.elementAt(
                                    pos2
                                )
                            ) {
                                isTransitive = false
                                break
                            }else if (a == c){
                                break
                            }
                            pos1 = pos1 + 2
                            pos2 = pos2 + 2
                            d("Elliott", "isTrans: $isTransitive")
                        } while (pos2 < amountOfNumbers - 1)

                    }
                    k = k + 2
                    positionInJ++
                } while (k <= amountOfNumbers - 2)

            } else {
                k = 0
                positionInJ = 0
                //Check through even positions
                d("Elliott", "Wublub")

                do {
                    j.add(transitiveRelation.elementAt(k))
                    k = k + 2
                } while (k <= amountOfNumbers - 2)
                d("Elliott", "even j:$j")

                k = 0

                do {
                    if (j.elementAt(positionInJ) == b) {
                        a = transitiveRelation.elementAt(k + 1)
                        d("Elliott", "a:$a")
                        c = transitiveRelation.elementAt(i - 1)
                        d("Elliott", "a:$a")
                        do {
                            if (a != transitiveRelation.elementAt(pos1) && c != transitiveRelation.elementAt(
                                    pos2
                                )
                            ) {
                                isTransitive = false
                                break
                            }
                            else if (a == c){
                                break
                            }
                            pos1 = pos1 + 2
                            pos2 = pos2 + 2
                            d("Elliott", "pos1: $pos1")
                            d("Elliott", "pos2: $pos2")
                        } while (pos2 < amountOfNumbers)

                    }
                    k = k + 2
                    positionInJ++
                } while (k <= amountOfNumbers - 2)
            }
            i++
            d("Elliott", " i $i")

        } while (i < amountOfNumbers)
        d("Elliott","$isTransitive")

        return isTransitive //returns whether the relation is transtive or not

    }
}
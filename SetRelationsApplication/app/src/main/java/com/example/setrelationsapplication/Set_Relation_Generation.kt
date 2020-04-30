package com.example.setrelationsapplication

import android.util.Log.d
import kotlin.random.Random

object Set_Relation_Generation {
    private var globalA: Int = 0
    private var globalB: Int = 0
    private var globalC: Int = 0

    /**
     * Creates a set to be used to form the relations
     * @return a list of numbers
     */
    fun setGenerator(): MutableList<Int> {
        val setLength = Random.nextInt(3, 5)
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
        var noOfRelationPairs = Random.nextInt(4, 6)
        var y = 0
        var i = 0
        var num1: Int
        var num2: Int
        var num3: Int
        var num4: Int

        do {
            var number = Random.nextInt(0, lengthOfSet)
            var orderedPairValue = values.elementAt(number)

            createdRelation.add(orderedPairValue)

            y++

        } while (y < noOfRelationPairs * 2)

        do {
            num1 = createdRelation.elementAt(i)
            num2 = createdRelation.elementAt(i + 2)
            if (num1 == num2) {
                num3 = createdRelation.elementAt(i + 1)
                num4 = createdRelation.elementAt(i + 3)
                if (num3 == num4) {
                    num1 = Random.nextInt(0, lengthOfSet)
                    num1 = values.elementAt(num1)
                    createdRelation.set(i, num1)
                }
            }
            i++
        } while (i < createdRelation.size - 3)

        return createdRelation
    }

    fun reflexive(values: MutableList<Int>, allReflexiveNum: MutableList<Int>): MutableList<Int> {
        var lengthOfSet = values.size
        var listOfPositions = mutableListOf<Int>()
        var i = 0




        var set = values
        var number:Int

        //Ensures there is enough pairs to fit all reflexive pairs
        if (allReflexiveNum.size/2 < values.size){
            do {
                number = Random.nextInt(0,9)
                allReflexiveNum.add(number)
            }while (allReflexiveNum.size/2 < values.size + 1)

        }

        do {
            do {
                number = Random.nextInt(0,allReflexiveNum.size - 1) * 2
            }while (number > allReflexiveNum.size-1)


            if(listOfPositions.contains(number)){
                do {
                    number = Random.nextInt(0,allReflexiveNum.size - 1) * 2

                }while (listOfPositions.contains(number) || number > allReflexiveNum.size - 1)
                listOfPositions.add(number)
            }else{
                listOfPositions.add(number)
            }


            i++
        }while (i<lengthOfSet )

        var x  = 0

        do {

            allReflexiveNum.set(listOfPositions.elementAt(x),set.elementAt(x))
            allReflexiveNum.set(listOfPositions.elementAt(x) + 1,set.elementAt(x))

            x++


        } while (x<set.size)


        return allReflexiveNum






    }

    fun symmetric(values: MutableList<Int>, symmetricRelation: MutableList<Int>): MutableList<Int> {
        var lengthOfSet = values.size

        var amountOfNumbers = symmetricRelation.size //Might need to remove + 1



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


        var positionPair1 = Random.nextInt(0, amountOfNumbers / 2 - 1)
        symmetricRelation.set(positionPair1, num1)

        var numberOneFirst: Boolean



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


        symmetricRelation.set(positionPair2, num2)

        if (positionPair2.rem(2) == 0 && numberOneFirst == false) {
            symmetricRelation.set(positionPair2, num1)
            symmetricRelation.set(positionPair2 + 1, num2)



        } else if (positionPair2.rem(2) == 1 && numberOneFirst == true) {
            symmetricRelation.set(positionPair2, num1)
            symmetricRelation.set(positionPair2 - 1, num2)

        }

        return symmetricRelation

    }

    fun transitive(relation: MutableList<Int>): Boolean {
        //var lengthOfSet = values.size
        var transitiveRelation = relation

        // var noOfRelationPairs = Random.nextInt(3, 5)

        var amountOfNumbers = transitiveRelation.size


        var i = 0 //Position of b in the relation list
        var k: Int //Position of other b
        var j: MutableList<Int> = mutableListOf<Int>() //Variable to see if b's are the same

        var a: Int // a in relation
        var b: Int // b in relation
        var c: Int // c in relation

        var pos1: Int //position of first a in (a,c)
        var pos2: Int //position of first c in (a,c)
        var isTransitive = true //whether the relation is transitive or not

        var positionInJ: Int


        do {
            b = transitiveRelation.elementAt(i)
            globalB = b

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


                k = 1


                do {
                    if (j.elementAt(positionInJ) == b) {
                        a = transitiveRelation.elementAt(k - 1)
                        globalA = a


                        c = transitiveRelation.elementAt(i + 1)
                        globalC = c

                        do {
                            if (a != c && c != b) {
                                if (a != transitiveRelation.elementAt(pos1) && c != transitiveRelation.elementAt(
                                        pos2
                                    )
                                ) {
                                    isTransitive = false
                                    break
                                } else if (a == c) {
                                    break
                                }
                            }
                            pos1 = pos1 + 2
                            pos2 = pos2 + 2


                        } while (pos2 < amountOfNumbers - 2)

                    }
                    k = k + 2
                    positionInJ++
                } while (k <= amountOfNumbers - 2)

            } else {
                k = 0
                positionInJ = 0
                //Check through even positions


                do {
                    j.add(transitiveRelation.elementAt(k))
                    k = k + 2
                } while (k <= amountOfNumbers - 2)


                k = 0

                do {
                    if (j.elementAt(positionInJ) == b) {
                        a = transitiveRelation.elementAt(k + 1)
                        globalA = a

                        c = transitiveRelation.elementAt(i - 1)
                        globalC = c

                        do {
                            if (a != c && c != b) {
                                if (a != transitiveRelation.elementAt(pos1) && c != transitiveRelation.elementAt(
                                        pos2
                                    )
                                ) {

                                    isTransitive = false
                                    break
                                } else if (a == c) {
                                    break
                                }
                            }
                            pos1 = pos1 + 2
                            pos2 = pos2 + 2


                        } while (pos1 < amountOfNumbers-2)

                    }
                    k = k + 2
                    positionInJ++
                } while (k <= amountOfNumbers - 2)
            }
            i++


        } while (i < amountOfNumbers)


        return isTransitive //returns whether the relation is transtive or not**and other values

    }

    fun abcTransitive(set: MutableList<Int>, relation: MutableList<Int>):MutableList<Int> {

        var number = Random.nextInt(0, set.size)
        var a = set.elementAt(number)

        number = Random.nextInt(0, set.size)
        var b = set.elementAt(number)

        if (b == a) {
            do {
                number = Random.nextInt(0, set.size)
                b = set.elementAt(number)
            } while (b == a)
        }

        number = Random.nextInt(0, set.size)
        var c = set.elementAt(number)

        if (c == a || c == b) {
            do {
                number = Random.nextInt(0, set.size)
                c = set.elementAt(number)
            } while (c == a || c == b)
        }

        var positionOne = Random.nextInt(0, relation.size - 1)

        if (positionOne.rem(2) == 1) {
            do {
                positionOne = Random.nextInt(0, relation.size - 1)
            } while (positionOne.rem(2) == 1)
        }


        relation.set(positionOne,a)
        relation.set(positionOne + 1,b)

        var positionTwo = Random.nextInt(0, relation.size - 1)
        if (positionTwo == positionOne || positionTwo.rem(2)==1) {
            do {
                positionTwo = Random.nextInt(0, relation.size - 1)
            } while (positionTwo == positionOne || positionTwo.rem(2)==1)
        }


        relation.set(positionTwo,b)
        relation.set(positionTwo + 1,c)




        var positionThree = Random.nextInt(0,relation.size-1)
        if (positionThree == positionOne || positionThree == positionTwo || positionThree.rem(2)==1)
        {
            do {
                positionThree = Random.nextInt(0,relation.size-1)
            }while (positionThree == positionOne || positionThree == positionTwo || positionThree.rem(2)==1)
        }


        relation.set(positionThree,a)
        relation.set(positionThree+1,c)


        //The values and positions are appended to be used with the hidden value questions
        relation.add(a)
        relation.add(b)
        relation.add(c)
        relation.add(positionOne)
        relation.add(positionTwo)
        relation.add(positionThree)

        return relation

    }


}
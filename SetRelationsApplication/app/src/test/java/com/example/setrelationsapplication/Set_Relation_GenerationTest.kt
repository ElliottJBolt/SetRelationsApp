package com.example.setrelationsapplication

import org.junit.Test

import org.junit.Assert.*

class Set_Relation_GenerationTest {

    @Test
    fun setGenerator() {
        Set_Relation_Generation.setGenerator()
    }

    @Test
    fun checkRepeated() {
        var list = mutableListOf<Int>(1,2,3,4)
        Set_Relation_Generation.checkRepeated(list,1)
    }

    @Test
    fun relationGenerator() {
        var list = mutableListOf<Int>(1,2,3,4)
        Set_Relation_Generation.relationGenerator(list)

    }

    @Test
    fun reflexive() {
        var list = mutableListOf<Int>(1,2,3)
        var list2 = mutableListOf<Int>(1,2,3,4,5,6,7,8)
        Set_Relation_Generation.reflexive(list,list2)

    }

    @Test
    fun symmetric() {
    }

    @Test
    fun transitive() {
    }

    @Test
    fun abcTransitive() {
    }
}
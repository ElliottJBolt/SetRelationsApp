package com.example.setrelationsapplication

import org.junit.Test

import org.junit.Assert.*

class InputQuestionTest {

    @Test
    fun reflexive() {
        var list = mutableListOf<Int>(1,2,2,2,5,5)
        InputQuestion.reflexive(list)

    }

    @Test
    fun symmetric() {
        var list = mutableListOf<Int>(1,2,2,1,5,5)
        InputQuestion.symmetric(list)
    }

    @Test
    fun transitive() {
        var list = mutableListOf<Int>(1,2,2,5,1,5,1,2,5,1,2,3)
        InputQuestion.transitive(list)
    }
}
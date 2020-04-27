package com.example.setrelationsapplication


import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_question.*
import kotlin.random.Random


/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {
    private var root: View? = null
    private var numCorrectAnswers = 0
    private var hiddenNums: MutableList<Int> = mutableListOf()
    private var positionOfNum: Int = 0
    private var relationVals: MutableList<Int> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_question, container, false)
        numCorrectAnswers = 0


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = (activity as ApplicationActivity).getUser()
        closeFeedback.isVisible = false

        val choice = arguments?.getString(Choice)
        var numAttempts = arguments?.getInt(Attempts)

        val dataBase = FirebaseFirestore.getInstance()


        var yes: Boolean
        d("Elliott", "$choice")
        var set = generateSet()
        var matchingType = generateQuestion(choice.toString(), set)


        var count = 0
        val userToString = user.toString()


        var result: String


        yesButton.setOnClickListener {
            yes = true

            count++
            result = checkAnswer(matchingType, yes)


            if (count < 10) {

                childFragmentManager.beginTransaction().replace(
                    R.id.feedbackFrame,
                    FeedbackFragment.newInstance(result, set, choice.toString(), yes)
                ).disallowAddToBackStack().commit()
                closeFeedback.isVisible = true


                set = generateSet()
                matchingType = generateQuestion(choice.toString(), set)


            } else {
                val score = mapOf(
                    "score " + numAttempts to numCorrectAnswers
                )
                d("Elliott", "$user")
                dataBase.collection("users").document(userToString).collection("questions")
                    .document(choice.toString()).update(score)
                fragmentManager?.popBackStackImmediate()

            }

        }

        noButton.setOnClickListener {
            yes = false
            count++

            result = checkAnswer(matchingType, yes)


            if (count < 10) {


                childFragmentManager.beginTransaction().replace(
                    R.id.feedbackFrame,
                    FeedbackFragment.newInstance(result, set, choice.toString(), yes)
                ).disallowAddToBackStack().commit()
                closeFeedback.isVisible = true


                set = generateSet()
                matchingType = generateQuestion(choice.toString(), set)


            } else {
                val score = mapOf(
                    "score " + numAttempts to numCorrectAnswers
                )
                d("Elliott", "$user")
                dataBase.collection("users").document(userToString).collection("questions")
                    .document(choice.toString()).update(score)
                fragmentManager?.popBackStackImmediate()
            }
        }

        submitButton.setOnClickListener {
            var userAnswer = answerInput.getText().toString().toInt()

            Log.d("Answer", "$userAnswer")

            count++
            if (count < 10) {


                childFragmentManager.beginTransaction().replace(
                    R.id.testName,
                    SecondStyleFeedback.newInstance(
                        hiddenNums,
                        positionOfNum,
                        userAnswer,
                        relationVals
                    )
                ).disallowAddToBackStack().commit()
                closeFeedback.isVisible = true

                set = generateSet()
                matchingType = generateQuestion(choice.toString(), set)
                //answerInput.text.clear()

            } else {
                val score = mapOf(
                    "score" + numAttempts to numCorrectAnswers
                )
                dataBase.collection("users").document(userToString).collection("questions")
                    .document(choice.toString()).update(score)
                fragmentManager?.popBackStackImmediate()

            }


        }

        closeFeedback.setOnClickListener {
            closeFeedback.isVisible = false
            answerInput.setText("")
            feedbackFrame.removeAllViews()
            testName.removeAllViews()


        }
    }


    companion object {
        private val Choice = "choice"
        private val Attempts = "attempts"

        fun newInstance(choice: String, attempts: Int?): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putString(Choice, choice)
            attempts?.let { args.putInt(Attempts, it) }
            fragment.arguments = args
            return fragment
        }


    }

    fun generateSet(): MutableList<Int> {
        var set = Set_Relation_Generation.setGenerator()
        return set
    }

    fun generateQuestion(type: String, set: MutableList<Int>): Boolean {

        val style: Int
        var typeOrNot: Int
        var choice = arguments?.getString(Choice)

        var matchingType = true
        var transReturn: MutableList<Any>
        var relation: MutableList<Int>
        var hiddenValues: MutableList<Int>

        formatSet(set) //formats the set text

        style = 2//Random.nextInt(1, 3)
        d("Style", "$style")
        relation = Set_Relation_Generation.relationGenerator(set)
        if (style == 1) {
            hiddenValues = mutableListOf()
            submitButton.isVisible = false
            answerInput.isVisible = false
            noButton.isVisible = true
            yesButton.isVisible = true

            questionText.text = "Is the following Relation (R) on Set (A) " + choice
            if (type == "transitive") {
                //relation = Set_Relation_Generation.relationGenerator(set)
                formatRelation(relation)
                transReturn = Set_Relation_Generation.transitive(relation)
                var trans = transReturn.elementAt(0).toString().toBoolean()

                if (trans == true) {
                    matchingType = true
                    return matchingType

                } else {
                    matchingType = false
                    return matchingType

                }

            } else if (type == "reflexive") {
                typeOrNot = Random.nextInt(1, 3)


                if (typeOrNot == 1) {
                    relation = Set_Relation_Generation.reflexive(set, relation)
                    formatRelation(relation)
                    matchingType = true

                } else {
                    relation = Set_Relation_Generation.relationGenerator(set)
                    formatRelation(relation)
                    matchingType = false

                }

            } else if (type == "symmetric") {
                typeOrNot = Random.nextInt(0, 2)

                if (typeOrNot == 1) {
                    relation = Set_Relation_Generation.symmetric(set, relation)
                    formatRelation(relation)
                    matchingType = true

                } else {
                    relation = Set_Relation_Generation.relationGenerator(set)
                    formatRelation(relation)
                    matchingType = false

                }


            } else {
                //for mixed questions
                typeOrNot = Random.nextInt(1, 2)

            }

        } else {
            submitButton.isVisible = true
            answerInput.isVisible = true
            yesButton.isVisible = false
            noButton.isVisible = false

            questionText.text = "Fill in the missing to make the relation " + choice

            if (type == "transitive") {

                var toTrans = false

                do {
                    relation = Set_Relation_Generation.relationGenerator(set)
                    transReturn = Set_Relation_Generation.transitive(relation)
                    toTrans = transReturn.elementAt(0).toString().toBoolean()
                    relationVals = relation
                } while (toTrans == false)
                
                Log.d("toTrans","$toTrans")

                var a = transReturn.elementAt(1).toString().toInt()
                var b = transReturn.elementAt(2).toString().toInt()
                var c = transReturn.elementAt(3).toString().toInt()
                hiddenValues = InputQuestion.transitive(relation, a, b, c)
                hiddenNums = hiddenValues
                formatRelationSecondQuestion(relation, hiddenValues)


            } else if (type == "reflexive") {
                relation = Set_Relation_Generation.reflexive(set, relation)

            } else if (type == "symmetric") {
                relation = Set_Relation_Generation.symmetric(set, relation)
                relationVals = relation
                hiddenValues = InputQuestion.symmetric(relation)
                hiddenNums = hiddenValues
                formatRelationSecondQuestion(relation, hiddenValues)


            }
        }
        return matchingType
    }

    fun checkAnswer(matchingType: Boolean, choice: Boolean): String {
        var result: String

        if (matchingType == choice) {
            result = "Correct"

            numCorrectAnswers++

        } else {
            result = "Incorrect"

        }
        return result
    }

    fun formatSet(set: MutableList<Int>) {
        val set = set
        var j = 0
        setText.text = "A = {"
        for (x in set) {
            if (j == set.size - 1) {
                setText.text = setText.text as String + "${set.elementAt(j)}"
            } else {
                setText.text = setText.text as String + "${set.elementAt(j)},"
                j++
            }
        }
        setText.text = setText.text as String + "}"

    }

    fun formatRelation(relation: MutableList<Int>) {
        val relation = relation
        var i = 0
        relationText.text = "R = {"

        for (x in relation) {
            if (i == 0) {
                relationText.text = relationText.text as String + "(${relation.elementAt(i)},"
            } else if (i.rem(2) == 1) {
                relationText.text = relationText.text as String + "${relation.elementAt(i)})"
            } else {

                relationText.text = relationText.text as String + ",(${relation.elementAt(i)},"
            }
            i++
        }
        relationText.text = relationText.text as String + "}"

    }

    fun formatRelationSecondQuestion(relation: MutableList<Int>, hiddenValues: MutableList<Int>) {
        var valueOne: Int
        var position = Random.nextInt(3, 4)
        positionOfNum = position

        valueOne = hiddenValues.elementAt(position)
        var i = 0
        relationText.text = "R = {"

        for (x in relation) {
            if (i == 0) {
                relationText.text = relationText.text as String + "(${relation.elementAt(i)},"
            } else if (i == valueOne) {

                if (valueOne.rem(2) == 0) {
                    relationText.text = relationText.text as String + "(X,"

                } else {
                    relationText.text = relationText.text as String + "X"
                }


            } else if (i.rem(2) == 1) {
                relationText.text = relationText.text as String + "${relation.elementAt(i)})"
            } else {

                relationText.text = relationText.text as String + ",(${relation.elementAt(i)},"
            }
            i++
        }
        relationText.text = relationText.text as String + "}"


    }


}

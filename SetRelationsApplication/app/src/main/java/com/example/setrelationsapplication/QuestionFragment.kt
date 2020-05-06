package com.example.setrelationsapplication


import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.android.synthetic.main.fragment_results.*
import kotlin.random.Random


/**
 * Fragment for generating questions
 */
class QuestionFragment : Fragment() {
    private var root: View? = null
    private var numCorrectAnswers = 0
    private var hiddenNums: MutableList<Int> = mutableListOf()
    private var positionOfNum: Int = 0
    private var relationVals: MutableList<Int> = mutableListOf()
    private var relationType:String = ""


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

        var choice = arguments!!.getString(Choice)!!
        var numAttempts = arguments!!.getInt(Attempts) + 1


        val dataBase = FirebaseFirestore.getInstance()


        var yes: Boolean

        var set = generateSet()
        var matchingType = generateQuestion(choice.toString(), set)


        var count = 0
        val userToString = user.toString()
        var result: String

        yesButton.setOnClickListener {
            yes = true

            count++
            result = checkAnswer(matchingType, yes)

            childFragmentManager.beginTransaction().replace(
                R.id.feedbackFrame,
                FeedbackFragment.newInstance(result, set, relationType, yes)
            ).disallowAddToBackStack().commit()
            closeFeedback.isVisible = true


        }

        noButton.setOnClickListener {
            yes = false
            count++

            result = checkAnswer(matchingType, yes)

            childFragmentManager.beginTransaction().replace(
                R.id.feedbackFrame,
                FeedbackFragment.newInstance(result, set, relationType, yes)
            ).disallowAddToBackStack().commit()
            closeFeedback.isVisible = true

        }

        submitButton.setOnClickListener {

            if (answerInput.text.toString().isNotBlank() || answerInput.text.toString().isNotEmpty()) {
                var userAnswer = answerInput.getText().toString().toInt()
                count++
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


            } else {
                Toast.makeText(activity, "Please enter an answer", Toast.LENGTH_SHORT).show()
            }


        }

        closeFeedback.setOnClickListener {
            closeFeedback.isVisible = false
            answerInput.setText("")
            feedbackFrame.removeAllViews()
            testName.removeAllViews()

            if (count < 10) {

                set = generateSet()
                matchingType = generateQuestion(choice.toString(), set)


            } else {
                val document = dataBase.collection("users").document(user)
                document.get().addOnSuccessListener { document ->
                    var stringAttempt: String
                    var intVal: Int

                    if (choice == "symmetric") {
                        stringAttempt = document.getString("symmAttempts").toString()
                        intVal = stringAttempt.toInt()
                        intVal = increaseAttempts(intVal)
                        stringAttempt = intVal.toString()

                        val attempts = mapOf(
                            "symmAttempts" to stringAttempt
                        )

                        dataBase.collection("users").document(user).update(attempts)


                    } else if (choice == "reflexive") {
                        stringAttempt = document.getString("refAttempts").toString()
                        intVal = stringAttempt.toInt()
                        intVal = increaseAttempts(intVal)

                        stringAttempt = intVal.toString()

                        val attempts = mapOf(
                            "refAttempts" to stringAttempt
                        )

                        dataBase.collection("users").document(user).update(attempts)


                    } else if (choice == "transitive") {
                        stringAttempt = document.getString("transAttempts").toString()
                        intVal = stringAttempt.toInt()
                        intVal = increaseAttempts(intVal)

                        stringAttempt = intVal.toString()

                        val attempts = mapOf(
                            "transAttempts" to stringAttempt
                        )

                        dataBase.collection("users").document(user).update(attempts)


                    } else {
                        stringAttempt = document.getString("mixedAttempts").toString()
                        intVal = stringAttempt.toInt()
                        intVal = increaseAttempts(intVal)

                        stringAttempt = intVal.toString()

                        val attempts = mapOf(
                            "mixedAttempts" to stringAttempt
                        )

                        dataBase.collection("users").document(user).update(attempts)

                    }

                }

                val score = mapOf(
                    "score " + numAttempts to numCorrectAnswers
                )
                dataBase.collection("users").document(userToString).collection("questions")
                    .document(choice.toString()).update(score)
                fragmentManager?.popBackStackImmediate()

            }


        }
    }

    /**
     * function to keep track of the amount of questions completed
     * @param attempts the current number of times questions have been answered
     * @return the new number of attempts
     */
    private fun increaseAttempts(attempts: Int): Int {
        var attempts = attempts
        if (attempts != null) {
            attempts = attempts + 1
        }
        return attempts


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

    /**
     * Function to generate set
     * @return the generated set
     */
    fun generateSet(): MutableList<Int> {
        var set = Set_Relation_Generation.setGenerator()
        return set
    }

    /**
     * Function to generate question
     * @param type the type of relation the user chose
     * @param set the set that was created for the question
     * @return whether the relation generated matches the type of relation chosen
     */
    fun generateQuestion(type: String, set: MutableList<Int>): Boolean {

        val style: Int
        var typeOrNot: Int
        relationType = arguments?.getString(Choice).toString()

        var matchingType = true

        var relation: MutableList<Int>
        var hiddenValues: MutableList<Int>

        formatSet(set) //formats the set text

        style = Random.nextInt(1, 3)

        relation = Set_Relation_Generation.relationGenerator(set)
        if (style == 1) {

            submitButton.isVisible = false
            answerInput.isVisible = false
            noButton.isVisible = true
            yesButton.isVisible = true

            if (type == "transitive") {

                formatRelation(relation)
                var trans = Set_Relation_Generation.transitive(relation)


                if (trans == true) {
                    matchingType = true
                    //return matchingType

                } else {
                    matchingType = false
                    //return matchingType

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
                    relation = Set_Relation_Generation.symmetric(relation)
                    formatRelation(relation)
                    matchingType = true

                } else {
                    relation = Set_Relation_Generation.relationGenerator(set)
                    formatRelation(relation)
                    matchingType = false

                }



            } else {
                //for mixed questions
                typeOrNot = Random.nextInt(0, 2)
                var quesType = Random.nextInt(1, 3)

                if (typeOrNot == 1 && quesType == 1) {
                    relationType = "symmetric"
                    relation = Set_Relation_Generation.symmetric(relation)
                    formatRelation(relation)
                    matchingType = true

                } else if (typeOrNot == 1 && quesType == 2) {
                    relationType = "reflexive"
                    relation = Set_Relation_Generation.reflexive(set,relation)
                    formatRelation(relation)
                    matchingType = true

                } else if (typeOrNot == 1 && quesType == 3 ) {
                    relationType = "transitive"
                    formatRelation(relation)
                    var trans = Set_Relation_Generation.transitive(relation)

                    if (trans == true) {
                        matchingType = true
                        return matchingType

                    } else {
                        matchingType = false
                        return matchingType
                    }

                } else {
                    var titleRelation = Random.nextInt(1,3)
                    if (titleRelation == 1){
                        relationType = "transitive"
                    }else if (titleRelation == 2){
                        relationType = "reflexive"
                    }else{
                        relationType = "symmetric"
                    }
                    relation = Set_Relation_Generation.relationGenerator(set)
                    formatRelation(relation)
                    matchingType = false
                }

            }

            questionText.text = "Is the following Relation (R) on Set (A) " + relationType
        } else {

            submitButton.isVisible = true
            answerInput.isVisible = true
            yesButton.isVisible = false
            noButton.isVisible = false



            if (type == "transitive") {

                relation = Set_Relation_Generation.relationGenerator(set)

                var transRelation = Set_Relation_Generation.abcTransitive(set, relation)

                hiddenValues = InputQuestion.transitive(transRelation)
                hiddenNums = hiddenValues

                var size = transRelation.size - 1
                transRelation.removeAt(size)
                transRelation.removeAt(size - 1)
                transRelation.removeAt(size - 2)
                transRelation.removeAt(size - 3)
                transRelation.removeAt(size - 4)
                transRelation.removeAt(size - 5)

                relationVals = transRelation


                formatRelationSecondQuestion(transRelation, hiddenValues)


            } else if (type == "reflexive") {
                relation = Set_Relation_Generation.reflexive(set, relation)
                relationVals = relation
                hiddenValues = InputQuestion.reflexive(relation)
                hiddenNums = hiddenValues
                formatRelationSecondQuestion(relation, hiddenValues)

            } else if (type == "symmetric") {
                relation = Set_Relation_Generation.symmetric(relation)
                relationVals = relation
                hiddenValues = InputQuestion.symmetric(relation)
                hiddenNums = hiddenValues
                formatRelationSecondQuestion(relation, hiddenValues)


            }else{
                var quesType = Random.nextInt(0, 3)
                if (quesType == 1){
                    relationType = "symmetric"
                    relation = Set_Relation_Generation.symmetric(relation)
                    relationVals = relation
                    hiddenValues = InputQuestion.symmetric(relation)
                    hiddenNums = hiddenValues
                    formatRelationSecondQuestion(relation, hiddenValues)



                }else if (quesType == 2){
                    relationType = "reflexive"
                    relation = Set_Relation_Generation.reflexive(set, relation)
                    relationVals = relation
                    hiddenValues = InputQuestion.reflexive(relation)
                    hiddenNums = hiddenValues
                    formatRelationSecondQuestion(relation, hiddenValues)

                }else {
                    relationType = "transitive"
                    relation = Set_Relation_Generation.relationGenerator(set)

                    var transRelation = Set_Relation_Generation.abcTransitive(set, relation)

                    hiddenValues = InputQuestion.transitive(transRelation)
                    hiddenNums = hiddenValues

                    var size = transRelation.size - 1
                    transRelation.removeAt(size)
                    transRelation.removeAt(size - 1)
                    transRelation.removeAt(size - 2)
                    transRelation.removeAt(size - 3)
                    transRelation.removeAt(size - 4)
                    transRelation.removeAt(size - 5)
                    d("Thing", "$transRelation")

                    relationVals = transRelation


                    formatRelationSecondQuestion(transRelation, hiddenValues)

                }
            }
            questionText.text = "Enter the missing value to make the relation " + relationType

        }
        return matchingType
    }

    /**
     * Function to check whether the users answer for yes/no style questions
     * @param matchingType whether the relation generated matches the relation chosen
     * @param choice the users answer to the question
     * @return returns incorrect or correct
     */
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

    /**
     * Function to format set for yes/no questions
     * @param set the set that was generated
     */
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

    /**
     * Function to format the relation
     * @param relation the relation that was generated
     */
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

    /**
     * Function to format the hidden value questions
     * @param relation the relation generated
     * @param hiddenValues the list of possible hidden values that were chosen
     */
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
                    relationText.text = relationText.text as String + "X)"
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

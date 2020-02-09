package com.example.setrelationsapplication


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_question.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {
    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_question, container, false)



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val yeet = arguments?.getString(Choice)
        var yes: Boolean
        d("Elliott","$yeet")
        questionText.text = questionText.text as String + yeet

        var matchingType = generateQuestion(yeet.toString())
        var count = 0

        yesButton.setOnClickListener {
            yes = true
            count++
            checkAnswer(matchingType,yes)
            if (count < 10){

                matchingType = generateQuestion(yeet.toString())


            }else{
                fragmentManager?.popBackStackImmediate()

            }

        }
        noButton.setOnClickListener {
            count++
            yes = false
            checkAnswer(matchingType,yes)
        }
    }

    companion object{
        private val Choice = "choice"

        fun newInstance(choice: String): QuestionFragment{
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putString(Choice,choice)
            fragment.arguments = args
            return fragment
        }


    }

    fun generateQuestion(type:String): Boolean{
        val style: Int
        var typeOrNot : Int
        var set = Set_Relation_Generation.setGenerator()
        var matchingType = true
        var relation: MutableList<Int>

        formatSet(set) //formats the set text

        style = 1//Random.nextInt(1,1) //Needs to be changed to until 2 when other question type are implemented

        if(style == 1){
            if (type == "transitive"){
                    relation = Set_Relation_Generation.relationGenerator(set)
                    formatRelation(relation)
                    var trans = Set_Relation_Generation.transitive(relation)

                    if (trans == true){
                        matchingType = true
                        return matchingType

                    }else{
                        matchingType = false
                        return matchingType

                    }

            }else if (type == "reflexive"){
                typeOrNot = Random.nextInt(1,2)
                if(typeOrNot==1){
                    relation = Set_Relation_Generation.reflexive(set)
                    formatRelation(relation)

                }else{
                    relation = Set_Relation_Generation.relationGenerator(set)
                    formatRelation(relation)

                }

            }else if (type == "symmetric"){
                typeOrNot = Random.nextInt(1,2)
                if (typeOrNot==1){
                    relation = Set_Relation_Generation.symmetric(set)
                    formatRelation(relation)

                }else{
                    relation = Set_Relation_Generation.relationGenerator(set)
                    formatRelation(relation)

                }


            }else{
                //for mixed questions
                typeOrNot = Random.nextInt(1,2)

            }

        }

        return matchingType


    }

    fun checkAnswer(matchingType:Boolean,choice:Boolean){

        if (matchingType == choice){
            textView.text = "Correct"

        }else{
            textView.text = "false"

        }

    }

    fun formatSet(set:MutableList<Int>){
        val set = set
        var j = 0
        setText.text = "A = {"
        for (x in set){
            if (j == set.size - 1){
                setText.text = setText.text as String + "${set.elementAt(j)}"
            }else {
                setText.text = setText.text as String + "${set.elementAt(j)},"
                j++
            }
        }
        setText.text = setText.text as String + "}"

    }

    fun formatRelation(relation:MutableList<Int>){
        val relation = relation
        var i = 0
        relationText.text = "R = {"

        for(x in relation){
            if(i == 0){
                relationText.text = relationText.text as String + "(${relation.elementAt(i)},"
            }
            else if ( i.rem(2) == 1  ) {
                relationText.text = relationText.text as String + "${relation.elementAt(i)})"
            }
            else{

                relationText.text = relationText.text as String + ",(${relation.elementAt(i)},"
            }
            i++
        }
        relationText.text = relationText.text as String + "}"

    }

    private fun replaceFragment(fragment: Fragment){
        fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, fragment)
            ?.addToBackStack(null)
            ?.commit()

    }




}

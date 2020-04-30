package com.example.setrelationsapplication


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_feedback_fragment_two.*

/**
 * A simple [Fragment] subclass.
 */
class SecondStyleFeedback : Fragment() {
    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_second_style_feedback, container, false)
        return  root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var answer = arguments?.getInt(Answer)
        var hiddenNumbers = arguments?.getIntegerArrayList(HiddenNumbers)
        var position = arguments?.getInt(Position)
        var relation = arguments?.getIntegerArrayList(Relation)
        var actualPosition = hiddenNumbers?.elementAt(position!!)

        Log.d("Answer", "$answer")


        var correctAnswer = relation?.elementAt(actualPosition!!)
        Log.d("Answer", "$correctAnswer")


        if (answer!!.toInt() == correctAnswer ){
            correctText.setText("Correct")
            root!!.setBackgroundColor(Color.parseColor("#90EE90"))
            feedbackText.isVisible = false

        }else{
            correctText.setText("Incorrect")
            root!!.setBackgroundColor(Color.parseColor("#FFCCBB"))
            feedbackText.isVisible = true
            feedbackText.setText("The missing value was:" + correctAnswer)
        }

    }

    companion object{

        private val HiddenNumbers:String = "hiddenNumbers"
        private val Position:String = "position"
        private val Answer:String = "answer"
        private val Relation:String = "relation"


        fun newInstance(hiddenNumbers:MutableList<Int>,position:Int,answer:Int,relation:MutableList<Int>): SecondStyleFeedback{
            val fragment = SecondStyleFeedback()
            val args = Bundle()
            val hiddenNumArray = ArrayList(hiddenNumbers)
            val relationArray = ArrayList(relation)

            args.putIntegerArrayList(HiddenNumbers,hiddenNumArray)
            args.putInt(Position,position)
            args.putInt(Answer,answer)
            args.putIntegerArrayList(Relation,relationArray)

            fragment.arguments = args

            return fragment

        }

    }


}

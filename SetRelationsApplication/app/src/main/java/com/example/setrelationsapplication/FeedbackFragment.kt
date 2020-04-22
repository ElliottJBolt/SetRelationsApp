package com.example.setrelationsapplication


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_feedback.*
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * A simple [Fragment] subclass.
 */
class FeedbackFragment : Fragment() {

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_feedback, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Variable that were passed to the fragment
        val result = arguments?.getString(Result)
        val set = arguments?.getIntegerArrayList(SetValues)
        val relation = arguments?.getString(RelationType)
        val answer = arguments?.getBoolean(Answer)

        resultText.text = result
        textView2.setText(set.toString())

        Log.d("SetTest", "$set")
        var i = 0

        /**if (result == "Incorrect"){

            if (answer == false && relation == "Reflexive")
            textView2.setText("This was a " + relation + "relation because")
            if (set != null) {
                do {
                    textView2.text = textView2.text as String + set.elementAt(i)

                } while (i<= set.size)
            }


        }
        nextButton.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }**/


    }



    companion object{
        private val Result:String = "result"
        private val SetValues:String = "set"
        private val RelationType:String = "type"
        private val Answer:String = "answer"

        fun newInstance(result:String, set:MutableList<Int>,type:String,answer:Boolean): FeedbackFragment{
            val fragment = FeedbackFragment()
            val args = Bundle()
            val setList = ArrayList(set)


            args.putString(Result,result)
            args.putIntegerArrayList(SetValues,setList)
            args.putString(RelationType,type)
            args.putBoolean(Answer,answer)
            fragment.arguments = args

            return fragment


        }
    }


}

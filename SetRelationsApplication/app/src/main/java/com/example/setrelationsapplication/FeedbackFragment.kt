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
 * Fragment to generate feedback for the yes/no style questions
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

        //Variables that were passed to the fragment
        val result = arguments?.getString(Result)
        val set = arguments?.getIntegerArrayList(SetValues)
        val relation = arguments?.getString(RelationType)
        val answer = arguments?.getBoolean(Answer)

        resultText.text = result


        Log.d("SetTest", "$set")

        Log.d("textTest", "$relation")
        Log.d("textTest", "$answer")
        Log.d("textTest", "$result")

        //Sets the background colour of the feedback fragment
        if (result == "Incorrect"){
            root!!.setBackgroundColor(Color.parseColor("#FFCCBB"))
        }else{
            root!!.setBackgroundColor(Color.parseColor("#90EE90"))
            textView2.text = ""
            textView3.text = ""
        }

        if (result == "Incorrect" && answer == false && relation == "reflexive"){
            Log.d("textTest", "YYYY")
            if (set != null) {
                textView2.setText("This was a " + relation + "relation")
                textView3.text = "There exists the pairs: "
                formatFeedback(set)
            }


        }else if (result == "Incorrect" && answer == true && relation == "reflexive"){
            if (set != null) {
                textView2.setText("This was not a " + relation + "relation")
                textView3.text = "Not all the following pairs are present:  "
                formatFeedback(set)
            }


        }else if (result == "Incorrect" && answer == false && relation == "symmetric"){

            if (set != null) {
                textView2.setText("This was a " + relation + "relation")
                textView3.text = "There was both an (a,b) pair and a (b,a) pair "
                //formatFeedback(set)
            }


        }else if (result == "Incorrect" && answer == true && relation == "symmetric"){
            if (set != null) {
                textView2.setText("This was not a " + relation + "relation")
                textView3.text = "If a pair (a,b) exists in the relation then (b,a) must also exist in the relation "
                //formatFeedback(set)
            }

        }else if (result == "Incorrect" && answer == false && relation == "transitive"){
            if (set != null) {
                textView2.setText("This was a " + relation + "relation")
                textView3.text = "There exists an (a,b), (b,c) and a (a,c) pair"
                //formatFeedback(set)
            }

        }else if (result == "Incorrect" && answer == true && relation == "transitive"){
            if (set != null) {
                textView2.setText("This was not a " + relation + "relation")
                textView3.text = "If (a,b) and (b,c) exist then so must (a,c) to be transitive "
                //formatFeedback(set)
            }

        }


    }

    /**
     * Function to format the feedback
     *
     * @param set The set which was used for the question
     */
    private fun formatFeedback( set:ArrayList<Int>){
        var i = 0

        if (set != null) {

            do {

                textView3.text = textView3.text as String +"("  + set.elementAt(i) + "," +set.elementAt(i)+ ")"
                i++

            } while (i< set.size)
        }

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

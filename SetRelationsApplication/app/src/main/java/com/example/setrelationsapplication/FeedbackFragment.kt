package com.example.setrelationsapplication


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val result = arguments?.getString(Result)
        val set = arguments?.getIntegerArrayList(SetValues)

        resultText.text = result
        textView2.setText(set.toString())

        Log.d("SetTest", "$set")

        if (result == "Correct"){
            //root?.setBackgroundColor(Color.parseColor("#00ff00"))

        }
        nextButton.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
    }

    companion object{
        private val Result:String = "result"
        private val SetValues:String = "set"

        fun newInstance(result:String, set:MutableList<Int>): FeedbackFragment{
            val fragment = FeedbackFragment()
            val args = Bundle()
            val test = ArrayList(set)


            args.putString(Result,result)
            args.putIntegerArrayList(SetValues,test)
            fragment.arguments = args

            return fragment


        }
    }


}

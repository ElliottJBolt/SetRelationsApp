package com.example.setrelationsapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class FeedbackFragmentTwo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback_fragment_two, container, false)
    }

    companion object{
        private val Relation:String = "relation"
        private val HiddenNumbers:String = "hiddenNumbers"
        private val Position:String = "position"


        fun newInstance(relation:MutableList<Int>,hiddenNumbers:MutableList<Int>,position:Int): FeedbackFragment{
            val fragment = FeedbackFragment()
            val args = Bundle()
            val relationArray = ArrayList(relation)
            val hiddenNumArray = ArrayList(hiddenNumbers)

            args.putIntegerArrayList(Relation,relationArray)
            args.putIntegerArrayList(HiddenNumbers,hiddenNumArray)
            args.putInt(Position,position)

            fragment.arguments = args

            return fragment

        }
    }


}

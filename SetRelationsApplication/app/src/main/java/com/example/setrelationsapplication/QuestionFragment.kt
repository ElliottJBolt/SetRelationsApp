package com.example.setrelationsapplication


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {

    private var questionChoice: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_question, container, false)
        val yeet = arguments?.getString(Choice)
        d("Elliott","$yeet")
        return view
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




}

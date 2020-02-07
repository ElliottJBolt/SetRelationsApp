package com.example.setrelationsapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
       var choice : String

        view.transitiveButton.setOnClickListener {
            choice = "transitive"
            val questionFragment = QuestionFragment.newInstance(choice)
            replaceFragment(questionFragment)



        }

        view.symmetricButton.setOnClickListener {
            choice = "symmetric"
            val questionFragment = QuestionFragment.newInstance(choice)
            replaceFragment(questionFragment)
        }

        view.reflexiveButton.setOnClickListener {
            choice = "reflexive"
            val questionFragment = QuestionFragment.newInstance(choice)
            replaceFragment(questionFragment)
        }

        view.allButton.setOnClickListener {
            choice = "all"
            val questionFragment = QuestionFragment.newInstance(choice)
            replaceFragment(questionFragment)
        }


        // Inflate the layout for this fragment
        return view

    }

    private fun replaceFragment(fragment: Fragment){
        fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, fragment)
            ?.addToBackStack(null)
            ?.commit()

    }




    }



package com.example.setrelationsapplication


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * The homepage fragment
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_home, container, false)

        // Inflate the layout for this fragment
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = FirebaseFirestore.getInstance()
        val user = (activity as ApplicationActivity).getUser()
        d("user","$user")

        var  transAttempts:String
        var refAttempts:String
        var symmAttempts:String
        var mixedAttempts:String

        val document = db.collection("users").document(user)

        document.get().addOnSuccessListener { document ->
                transAttempts = document.getString("transAttempts").toString()
                refAttempts = document.getString("refAttempts").toString()
                symmAttempts = document.getString("symmAttempts").toString()
                mixedAttempts = document.getString("mixedAttempts").toString()

            var choice : String
            var intTrans:Int = transAttempts.toInt()
            var intSymm:Int = symmAttempts.toInt()
            var intRef:Int = refAttempts.toInt()
            var intMixed:Int = mixedAttempts.toInt()

            transitiveButton.setOnClickListener {
                choice = "transitive"


                val questionFragment = QuestionFragment.newInstance(choice,intTrans)
                replaceFragment(questionFragment)

            }

            symmetricButton.setOnClickListener {
                choice = "symmetric"


                val questionFragment = QuestionFragment.newInstance(choice,intSymm)
                replaceFragment(questionFragment)
            }

            reflexiveButton.setOnClickListener {
                choice = "reflexive"


                val questionFragment = QuestionFragment.newInstance(choice,intRef)
                replaceFragment(questionFragment)
            }

            allButton.setOnClickListener {
                choice = "all"


                val questionFragment = QuestionFragment.newInstance(choice,intMixed)
                replaceFragment(questionFragment)
            }

            resultsButton.setOnClickListener {
                transAttempts = document.getString("transAttempts").toString()
                refAttempts = document.getString("refAttempts").toString()
                symmAttempts = document.getString("symmAttempts").toString()
                mixedAttempts = document.getString("mixedAttempts").toString()
                intTrans = transAttempts.toInt()
                 intSymm = symmAttempts.toInt()
                 intRef = refAttempts.toInt()
                 intMixed = mixedAttempts.toInt()
                val resultFragment = ResultsFragment.newInstance(user,intTrans,intSymm,intRef,intMixed)
                replaceFragment(resultFragment)
            }
        }



    }

    /**
     * Function to replace fragment
     *
     * @param  fragment The fragment that will be swapped to
     */
    private fun replaceFragment(fragment: Fragment){
        fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, fragment)?.addToBackStack(null)
            ?.commit()

    }




    }



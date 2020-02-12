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
 * A simple [Fragment] subclass.
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
            var intTrans = transAttempts.toInt()
            var intSymm = symmAttempts.toInt()
            var intRef = refAttempts.toInt()
            var intMixed = mixedAttempts.toInt()

            transitiveButton.setOnClickListener {
                choice = "transitive"
                intTrans = numAttempts(intTrans)
                transAttempts = intTrans.toString()

                val attempts = mapOf(
                    "transAttempts" to transAttempts
                )

                db.collection("users").document(user).update(attempts)

                val questionFragment = QuestionFragment.newInstance(choice,intTrans)
                replaceFragment(questionFragment)

            }

            symmetricButton.setOnClickListener {
                choice = "symmetric"
                intSymm = numAttempts(intSymm)
                symmAttempts = intSymm.toString()

                val attempts = mapOf(
                    "symmAttempts" to symmAttempts
                )

                db.collection("users").document(user).update(attempts)

                val questionFragment = QuestionFragment.newInstance(choice,intSymm)
                replaceFragment(questionFragment)
            }

            reflexiveButton.setOnClickListener {
                choice = "reflexive"
                intRef = numAttempts(intRef)
                refAttempts = intRef.toString()

                val attempts = mapOf(
                    "refAttempts" to refAttempts
                )

                db.collection("users").document(user).update(attempts)

                val questionFragment = QuestionFragment.newInstance(choice,intRef)
                replaceFragment(questionFragment)
            }

            allButton.setOnClickListener {
                choice = "all"
                intMixed = numAttempts(intMixed)

                val questionFragment = QuestionFragment.newInstance(choice,intMixed)
                replaceFragment(questionFragment)
            }

            resultsButton.setOnClickListener {
                val resultFragment = ResultsFragment.newInstance(user,intTrans,intSymm,intRef,intMixed)
                replaceFragment(resultFragment)
            }
        }




    }

    private fun replaceFragment(fragment: Fragment){
        fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, fragment)
            ?.addToBackStack(null)
            ?.commit()

    }

    private fun numAttempts(attempts: Int): Int{
        var attempts = attempts
        if (attempts != null) {
            attempts = attempts + 1
        }
        return attempts


    }




    }



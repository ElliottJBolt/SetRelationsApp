package com.example.setrelationsapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.android.synthetic.main.fragment_results.*

/**
 * A simple [Fragment] subclass.
 */
class ResultsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_results, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments!!.getString(username).toString()
        val db =FirebaseFirestore.getInstance()
        var whichResult:String

        var document:DocumentReference



        transButton.setOnClickListener {
            whichResult = "transitive"
            document = db.collection("users").document(user).collection("questions").document("transitive")
            getResults(whichResult,document)

        }
        symmButton.setOnClickListener {
            whichResult = "symmetric"
            document = db.collection("users").document(user).collection("questions").document("symmetric")
            getResults(whichResult,document)
        }
        refButton.setOnClickListener {
            whichResult = "reflexive"
            document = db.collection("users").document(user).collection("questions").document("reflexive")
            getResults(whichResult,document)
        }
        mixedButton.setOnClickListener {
            whichResult = "mixed"
            document = db.collection("users").document(user).collection("questions").document("mixed")
            getResults(whichResult,document)
        }



    }

    fun getResults(result:String,document:DocumentReference){
        document.get().addOnSuccessListener { document ->
            transButton.setOnClickListener {
                val testData = document.getString("transitive")
                testView.setText(testData)
            }

        }

    }


    companion object {
        private val username = "user"


        fun newInstance(user: String): ResultsFragment {
            val fragment = ResultsFragment()
            val args = Bundle()
            args.putString(username, user)
            fragment.arguments = args
            return fragment
        }


    }
}

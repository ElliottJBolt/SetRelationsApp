package com.example.setrelationsapplication


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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
    private var globalScoreList = mutableListOf<Int>()

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
        val transCount = arguments!!.getInt(transCount)
        val symmCount = arguments!!.getInt(symmCount)
        val refCount = arguments!!.getInt(reflexiveCount)
        val mixedCount = arguments!!.getInt(mixedCount)
        val db =FirebaseFirestore.getInstance()
        var whichResult = "transitive"
        var document:DocumentReference

        //Users transitive results are loaded in by default before they make a choice
        /**document = db.collection("users").document(user).collection("questions").document("transitive")
        getResults(whichResult,document,transCount)
        d("ScoreList", "$globalScoreList")

        scoreRecylcer.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ScoresAdapter(globalScoreList)
        }**/



        transButton.setOnClickListener {
            whichResult = "transitive"
            document = db.collection("users").document(user).collection("questions").document("transitive")
            getResults(whichResult,document,transCount)

        }
        symmButton.setOnClickListener {
            whichResult = "symmetric"
            document = db.collection("users").document(user).collection("questions").document("symmetric")
            getResults(whichResult,document,symmCount)
        }
        refButton.setOnClickListener {
            whichResult = "reflexive"
            document = db.collection("users").document(user).collection("questions").document("reflexive")
            getResults(whichResult,document,refCount)
        }
        mixedButton.setOnClickListener {
            whichResult = "mixed"
            document = db.collection("users").document(user).collection("questions").document("mixed")
            getResults(whichResult,document,mixedCount)
        }



    }

    fun getResults(result:String,document:DocumentReference,maxCount:Int){
        document.get().addOnSuccessListener { document ->
                d("results","$result")
            var count:Int
            if (maxCount > 10){
                 count = maxCount - 9
            }else{
                count = 1
            }

            var scoreList:MutableList<Int>  = mutableListOf<Int>()
            do {
                val testData = document.getLong("score "+count)
                count++
                val testString = testData.toString()
                scoreList.add(testData!!.toInt())
                //testView.setText("score "+count+": " + testString)

            }while (count <= maxCount )

            scoreRecylcer.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ScoresAdapter(scoreList)
            }


            }

        }


    companion object {
        private val username = "user"
        private val transCount = "trans"
        private val symmCount = "symm"
        private val reflexiveCount = "ref"
        private val mixedCount = "mixed"


        fun newInstance(user: String,trans:Int,symm:Int,ref:Int,mixed:Int): ResultsFragment {
            val fragment = ResultsFragment()
            val args = Bundle()
            args.putString(username, user)
            args.putInt(transCount,trans)
            args.putInt(symmCount,symm)
            args.putInt(reflexiveCount,ref)
            args.putInt(mixedCount,mixed)
            fragment.arguments = args
            return fragment
        }


    }
}

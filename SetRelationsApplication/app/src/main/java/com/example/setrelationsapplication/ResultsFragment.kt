package com.example.setrelationsapplication


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.android.synthetic.main.fragment_results.*
import kotlinx.android.synthetic.main.row_score.*

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
        val transCount = arguments!!.getInt(transCount)
        val symmCount = arguments!!.getInt(symmCount)
        val refCount = arguments!!.getInt(reflexiveCount)
        val mixedCount = arguments!!.getInt(mixedCount)
        val db =FirebaseFirestore.getInstance()
        var whichResult = "transitive"
        var document:DocumentReference




        textRadio.isChecked = true
        scoreGraph.isVisible = false

        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{group, checkedId ->  
            if (checkedId == R.id.graphRadio){
                scoreGraph.isVisible = true
                scoreRecylcer.isVisible = false

            }else{
                scoreGraph.isVisible = false
                scoreRecylcer.isVisible = true

            }
        })





        transButton.setOnClickListener {
            scoreGraph.removeAllSeries()
            whichResult = "transitive"
            document = db.collection("users").document(user).collection("questions").document("transitive")
            getResults(whichResult,document,transCount)


        }
        symmButton.setOnClickListener {
            scoreGraph.removeAllSeries()
            whichResult = "symmetric"
            document = db.collection("users").document(user).collection("questions").document("symmetric")
            getResults(whichResult,document,symmCount)
        }
        refButton.setOnClickListener {
            scoreGraph.removeAllSeries()
            whichResult = "reflexive"
            document = db.collection("users").document(user).collection("questions").document("reflexive")
            getResults(whichResult,document,refCount)
        }
        mixedButton.setOnClickListener {
            scoreGraph.removeAllSeries()
            whichResult = "mixed"
            document = db.collection("users").document(user).collection("questions").document("mixed")
            getResults(whichResult,document,mixedCount)
        }



    }

    fun getResults(result:String,document:DocumentReference,maxCount:Int){
        document.get().addOnSuccessListener { document ->

            var count:Int
            var i = 0
            var series1: LineGraphSeries<DataPoint> = LineGraphSeries()

            if (maxCount > 10){
                 count = maxCount - 9
            }else{
                count = 1
            }

            var scoreList:MutableList<Int>  = mutableListOf<Int>()
            do {
                val testData = document.getLong("score "+count)
                count++

                scoreList.add(testData!!.toInt())

            }while (count <= maxCount )

            scoreRecylcer.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ScoresAdapter(scoreList)
            }
            var x:Double
            var y:Double
            x = 0.0

            do {
                y = scoreList.elementAt(i).toDouble()
                x = x + 1.0
                series1.appendData(DataPoint(x,y),true ,10)


                i++
            }while (i<scoreList.size)
            scoreGraph.viewport.isXAxisBoundsManual = true
            scoreGraph.viewport.setMaxX(11.0)
            scoreGraph.viewport.setMinX(1.0)
            scoreGraph.viewport.isYAxisBoundsManual = true
            scoreGraph.viewport.setMinY(0.0)
            scoreGraph.viewport.setMaxY(10.0)

            scoreGraph.addSeries(series1)
            
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

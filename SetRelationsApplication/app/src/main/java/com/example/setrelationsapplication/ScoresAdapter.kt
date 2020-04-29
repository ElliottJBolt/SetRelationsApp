package com.example.setrelationsapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_score.view.*

class ScoresAdapter(val scores:MutableList<Int>) : RecyclerView.Adapter<ScoresAdapter.ViewHolder>(){

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_score,parent,false)
        return ViewHolder(view)

    }


    override fun getItemCount() = scores.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var scoreNum = position + 1
        holder.view.scoreText.text = "Score $scoreNum: ${scores.elementAt(position)}"


    }




}

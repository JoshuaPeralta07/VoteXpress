package com.example.votexpress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CandidateAdapter(
    private val candidateList: List<Candidate>,
    private var selectedPosition: Int = -1 // Tracks the selected candidate
) : RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder>() {

    class CandidateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val candidateName: TextView = itemView.findViewById(R.id.tv_candidate_name)
        val candidateParty: TextView = itemView.findViewById(R.id.tv_candidate_party)
        val radioButton: RadioButton = itemView.findViewById(R.id.radio_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pm_candidate_item, parent, false)
        return CandidateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        val candidate = candidateList[position]

        holder.candidateName.text = candidate.name
        holder.candidateParty.text = candidate.party
        holder.radioButton.isChecked = position == selectedPosition

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged() // Update the selected item
        }

        holder.radioButton.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged() // Update the selected item
        }
    }

    override fun getItemCount(): Int {
        return candidateList.size
    }

    // Function to get the selected candidate
    fun getSelectedCandidate(): Candidate? {
        return if (selectedPosition != -1) candidateList[selectedPosition] else null
    }
}

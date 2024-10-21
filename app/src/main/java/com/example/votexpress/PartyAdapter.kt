package com.example.votexpress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PartyAdapter(
    private val partyList: List<Party>,
    private var selectedPosition: Int = -1 // Tracks the selected party
) : RecyclerView.Adapter<PartyAdapter.PartyViewHolder>() {

    class PartyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val partyName: TextView = itemView.findViewById(R.id.tv_party_name)
        val partyLeader: TextView = itemView.findViewById(R.id.tv_party_leader)
        val radioButton: RadioButton = itemView.findViewById(R.id.radio_button_party)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.party_item, parent, false)
        return PartyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartyViewHolder, position: Int) {
        val party = partyList[position]

        holder.partyName.text = party.name
        holder.partyLeader.text = party.leader
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
        return partyList.size
    }

    // Function to get the selected party
    fun getSelectedParty(): Party? {
        return if (selectedPosition != -1) partyList[selectedPosition] else null
    }
}

package com.example.acronimeserviceapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronimeserviceapp.databinding.ListItemAcronimeBinding
import com.example.acronimeserviceapp.model.AcronimeLF

class AcronimeAdapter(private val acronimeLFList: List<AcronimeLF>) :
    RecyclerView.Adapter<AcronimeAdapter.ViewHolder>() {

    class ViewHolder(private val itemBinding: ListItemAcronimeBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(acronimeLF: AcronimeLF) {
            with(itemBinding) {
                lfText.text = acronimeLF.lf
                lfSince.text = acronimeLF.since
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ListItemAcronimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(acronimeLFList[position])
    }

    override fun getItemCount() = acronimeLFList.size

}
package com.igorvd.chuckfacts.features.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.utils.extensions.content
import kotlinx.android.extensions.LayoutContainer

class SearchHistoricAdapter(
    val onItemClicked: (String) -> Unit
) : ListAdapter<String, SearchHistoricAdapter.MyViewHolder>(DIFF_CALLBACK) {

    inner class MyViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(historicItem: String) {
            (containerView as TextView).apply {
                content = historicItem
                setOnClickListener {
                    onItemClicked(historicItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoricAdapter.MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_historic, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: SearchHistoricAdapter.MyViewHolder, position: Int) {
        val historicItem = getItem(position)
        holder.bind(historicItem)
    }

    private object DIFF_CALLBACK : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

}
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
) : ListAdapter<String, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    inner class MyViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView =
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_historic, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val historicItem = getItem(position)
        with(holder.itemView) {
            (this as TextView).content = historicItem
            setOnClickListener {
                onItemClicked(historicItem)
            }
        }
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
package com.igorvd.chuckfacts.features.jokes

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.features.jokes.model.JokeView
import com.igorvd.chuckfacts.utils.extensions.addChip
import com.igorvd.chuckfacts.utils.extensions.content
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_jokes.view.*

class JokesAdapter(
    private val context: Context,
    private val onShareUrlClicked: (String) -> Unit
) : ListAdapter<JokeView, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    inner class MyViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView =
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jokes, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val jokeView = getItem(position)
        with(holder.itemView) {

            tvJokeValue.content = jokeView.value
            val textSize = this@JokesAdapter.context.resources.getDimension(jokeView.textSizeRes)
            tvJokeValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

            chipGroupCategory.removeAllViews()
            for (category in jokeView.categories) {
                chipGroupCategory.addChip(context, category)
            }

            btShare.setOnClickListener { onShareUrlClicked(jokeView.url) }

        }
    }

    private object DIFF_CALLBACK : DiffUtil.ItemCallback<JokeView>() {
        override fun areItemsTheSame(oldItem: JokeView, newItem: JokeView): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JokeView, newItem: JokeView): Boolean {
            return oldItem == newItem
        }
    }

}
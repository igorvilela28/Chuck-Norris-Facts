package com.igorvd.chuckfacts.utils.extensions

import android.content.Context
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author Igor Vilela
 * @since 11/01/2018
 */

fun<T: RecyclerView.ViewHolder> RecyclerView.setup(
        context: Context,
        adapter: RecyclerView.Adapter<T>,
        layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context),
        itemAnimator: RecyclerView.ItemAnimator = DefaultItemAnimator(),
        isNestedScrollingEnabled: Boolean = true) : RecyclerView {

    this.layoutManager = layoutManager
    this.itemAnimator = itemAnimator
    this.adapter = adapter
    this.isNestedScrollingEnabled = isNestedScrollingEnabled

    return this
}
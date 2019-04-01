package com.igorvd.chuckfacts.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SpinnerDropdownAdapter<T>(
        private val mContext: Context,
        resource: Int = android.R.layout.simple_spinner_dropdown_item,
        val items: MutableList<T> = mutableListOf(),
        private val getText: (Int) -> String) : ArrayAdapter<T>(mContext, resource) {

    override fun getItem(position: Int): T {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    fun clearList() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addAllToList(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private fun getCustomView(position: Int, parent: ViewGroup): View {

        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val rootView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item,
                parent, false)

        val tv = rootView.findViewById<TextView>(android.R.id.text1)

        tv.text = getText(position)

        return rootView

    }
}
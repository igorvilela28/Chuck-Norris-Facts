package com.igorvd.chuckfacts.utils.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.igorvd.chuckfacts.R
import com.igorvd.chuckfacts.utils.extensions.getColorCompat

@Suppress("UNCHECKED_CAST")
class SpinnerHintDropdownAdapter<T : Any>(
        context: Context,
        val hint: CharSequence? = null,
        resource: Int = android.R.layout.simple_spinner_dropdown_item,
        private val getText: (T) -> String) : ArrayAdapter<Any>(context, resource) {

    val items: MutableList<Any> = hint?.let { mutableListOf<Any>(it) } ?: mutableListOf()

    // region: base class

    override fun getItem(position: Int): Any {
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

    //endregion: base class

    //region: PUBLIC API

    inline fun <reified T> getItensWithoutHint(): List<T> {
        val list = mutableListOf<T>()
        items.forEach { item -> if ((item is T) && item != hint) list.add(item) }
        return list
    }

    fun hasHint() = hint != null

    fun clearList() {

        items.clear()
        hint?.let { items.add(it) }
        notifyDataSetChanged()
    }

    fun addAllToList(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * @throws ClassCastException Caso o tipo do objeto da posicao nao seja do declarado na construção
     * do adapter
     */
    fun getOriginalItem(position: Int) : T {
       return items[position] as T
    }

    fun getItemPosition(item: T): Int = items.indexOf(item)

    //endregion: PUBLIC API


    private fun getCustomView(position: Int, parent: ViewGroup): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val rootView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item,
                parent, false)

        val tv = rootView.findViewById<TextView>(android.R.id.text1)


        if (hasHint() && position == 0) {
            tv.text = hint
            tv.setTextColor(context.getColorCompat(R.color.hint))
        } else {

            try {
                val item = getOriginalItem(position)
                tv.text = getText(item)
            } catch (e: ClassCastException) {
                Log.e(SpinnerHintDropdownAdapter::class.java.canonicalName, e.message)
            }
        }

        return rootView

    }
}
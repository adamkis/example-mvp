package com.example.app.feature.weatherList

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.util.inflate

class WeatherListAdapter(private val callback: (Action) -> Unit) : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    private val itemList: ArrayList<String> = arrayListOf()

    fun setItems(itemList: List<String>) {
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = if (position < itemList.size) {
        VIEW_TYPE_ITEM
    } else {
        VIEW_TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            ViewHolder.ItemHolder(parent, callback)
        } else {
            ViewHolder.LoadingHolder(parent, callback)
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder.ItemHolder).onBind(itemList[position])
    }

    sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        class ItemHolder(rootView: ViewGroup, private val callback: (Action) -> Unit)
            : ViewHolder(rootView.inflate(R.layout.item_weather_list)) {
            private val tvText = itemView.findViewById<TextView>(R.id.tvText)

            fun onBind(text: String) {
                tvText.text = text
                itemView.setOnClickListener {
                    callback.invoke(Action.ItemClicked(text))
                }
            }
        }

        // TODO use different res
        class LoadingHolder(rootView: ViewGroup, callback: (Action) -> Unit)
            : ViewHolder(rootView.inflate(R.layout.item_weather_list))
    }

    sealed class Action {
        class ItemClicked(val text: String) : Action()
    }

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }
}

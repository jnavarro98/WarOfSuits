package com.janavarro.warofsuits.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.janavarro.warofsuits.R
import com.janavarro.warofsuits.model.IconButton

//We pass the onclick action in the constructor
class IconButtonAdapter(private val onClick: (IconButton) -> Unit) :
    ListAdapter<IconButton, IconButtonAdapter.IconButtonsViewHolder>(IconButtonDiffCallback) {

    class IconButtonsViewHolder(itemView: View, val onClick: (IconButton) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val iconButtonTitle: TextView = itemView.findViewById(R.id.tv_icon_title)
        private val iconButtonIcon: ImageView = itemView.findViewById(R.id.iv_icon)
        private var currentIconButton: IconButton? = null

        init {
            itemView.setOnClickListener {
                currentIconButton?.let {
                    onClick(it)
                }
            }
        }

        //Binds the view
        fun bind(iconButton: IconButton) {
            currentIconButton = iconButton

            iconButtonTitle.text = iconButton.title
            iconButtonIcon.setImageResource(iconButton.image)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconButtonsViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_icon_button, parent, false)

        return IconButtonsViewHolder(view, onClick)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: IconButtonsViewHolder, position: Int) {
        val iconButton = getItem(position)
        holder.bind(iconButton)
    }

}

//Needed for list adapter
object IconButtonDiffCallback : DiffUtil.ItemCallback<IconButton>() {
    override fun areItemsTheSame(oldItem: IconButton, newItem: IconButton): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: IconButton, newItem: IconButton): Boolean {
        return oldItem.title == newItem.title
    }
}
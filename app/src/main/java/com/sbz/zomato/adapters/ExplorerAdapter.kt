package com.sbz.zomato.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sbz.zomato.R
import com.sbz.zomato.models.ExplorerModel
import java.util.Random

class ExplorerAdapter(private val context: Context, private val explorerList: List<ExplorerModel>) :
    RecyclerView.Adapter<ExplorerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivExploreItemImage: ImageView = itemView.findViewById(R.id.iv_exploreItem)
        val tvExplorerTitle: TextView = itemView.findViewById(R.id.tv_exploreItemTitle)
        val tvExplorerItemType: TextView = itemView.findViewById(R.id.tv_exploreItemType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.explore_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return explorerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = explorerList[position]

        holder.tvExplorerTitle.text = currentItem.title
        holder.tvExplorerItemType.text = currentItem.type
        holder.tvExplorerItemType.setTextColor(generateRandomColor())
        Glide.with(context)
            .load(currentItem.imageUrl)
            .into(holder.ivExploreItemImage)
    }

    private fun generateRandomColor(): Int {
        val random = Random()
        val randomColor = random.nextInt(4)
        val colorId = when (randomColor) {
            0 -> R.color.light_blue
            1 -> R.color.light_green
            2 -> R.color.light_purple
            else -> R.color.light_yello

        }
        return context.resources.getColor(colorId, context.theme)
    }
}
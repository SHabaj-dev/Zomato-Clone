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
import com.sbz.zomato.models.WhatsOnMindModel

class WhatsOnMindAdapter(
    private val context: Context,
    private val itemList: List<WhatsOnMindModel>
) : RecyclerView.Adapter<WhatsOnMindAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImage: ImageView = itemView.findViewById(R.id.iv_offers)
        val tvOfferTitle: TextView = itemView.findViewById(R.id.tv_offerTitle)
        val tvOfferType: TextView = itemView.findViewById(R.id.tv_offerType)
        val tvOfferTime: TextView = itemView.findViewById(R.id.tv_offerTime)
        val tvOfferDeal: TextView = itemView.findViewById(R.id.tv_offerDeal)
        val tvOfferPrice: TextView = itemView.findViewById(R.id.tv_offerPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.whats_on_mind_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.tvOfferTitle.text = currentItem.restaurantName
        holder.tvOfferTime.text = currentItem.timeToDeliver
        holder.tvOfferDeal.text = currentItem.totalOffPercentage
        holder.tvOfferPrice.text = currentItem.price.toString().trim()
        Glide.with(context)
            .load(currentItem.imageUrl)
            .into(holder.ivItemImage)
        holder.tvOfferType.text = currentItem.itemName
    }
}
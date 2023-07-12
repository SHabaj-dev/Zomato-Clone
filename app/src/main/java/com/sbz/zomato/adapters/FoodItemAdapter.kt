package com.sbz.zomato.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.imageview.ShapeableImageView
import com.sbz.zomato.R
import com.sbz.zomato.models.FoodModel

class FoodItemAdapter(private val context: Context, private val itemList: List<FoodModel>) :
    RecyclerView.Adapter<FoodItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFoodImage = itemView.findViewById<ShapeableImageView>(R.id.iv_footItemImage)
        val tvFoodItemTitle = itemView.findViewById<TextView>(R.id.tv_foodItemTitle)
        val tvFoodItemTime = itemView.findViewById<TextView>(R.id.tv_foodItemTime)
        val tvFoodType = itemView.findViewById<TextView>(R.id.tv_foodType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.tvFoodItemTitle.text = item.foodItemName
        holder.tvFoodItemTime.text = item.deliveryTime
        holder.tvFoodType.text = item.cookingType
        Glide
            .with(context)
            .load(item.imageUrl)
            .error(R.drawable.baseline_error_outline_24)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.ivFoodImage)

    }
}
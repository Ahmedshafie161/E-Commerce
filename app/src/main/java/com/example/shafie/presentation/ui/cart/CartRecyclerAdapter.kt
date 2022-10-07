package com.example.shafie.presentation.ui.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shafie.data.dbhelper.DbEntity
import com.example.shafie.databinding.AdapterCartRecyclerBinding

class CartRecyclerAdapter(private val cartFragment: CartFragment) : RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {

    var list= mutableListOf<DbEntity>()

    inner class ViewHolder(val binding: AdapterCartRecyclerBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            AdapterCartRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(cartFragment)
            .load(list[position].image_url)
            .into(holder.binding.ivProductImage)
        holder.binding.tvProductName.text=list[position].name
        holder.binding.tvProductPrice.text=list[position].price

    }

    override fun getItemCount(): Int {
        Log.d("myTAG",list.size.toString())
        return list.size
    }


    fun productListInCart(list1:MutableList<DbEntity>){
        list.clear()
        list=list1
        Log.d("myTAGCartRecycler","list in adapter $list1")
        notifyDataSetChanged()

    }

}

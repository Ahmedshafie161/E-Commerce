package com.example.shafie.presentation.ui.home

import android.animation.Animator
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.shafie.databinding.AdapterHomeRecyclerBinding
import com.example.shafie.network.modelapi.OnlineEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeRecylerAdapter(private val homeActivity: HomeActivity) : RecyclerView.Adapter<HomeRecylerAdapter.ViewHolder>() {

    var list= mutableListOf<OnlineEntity>()
    lateinit var lottieButton:LottieAnimationView

    inner class ViewHolder(val binding: AdapterHomeRecyclerBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            AdapterHomeRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(homeActivity)
            .load(list[position].image_url)
            .into(holder.binding.ivProductImage)
        holder.binding.tvProductName.text=list[position].name
        holder.binding.tvProductPrice.text=list[position].price
        holder.binding.tvProductRating.rating= list[position].rating.toFloat()
        lottieButton=holder.binding.btnAddToCartAnim
        holder.binding.btnAddToCart.setOnClickListener {
            holder.binding.btnAddToCart.isInvisible = true
            holder.binding.btnAddToCartAnim.isVisible = true
            homeActivity.addItemToCart(list[position])
            lottieButton.playAnimation()
            lottieButton.addAnimatorListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                    Log.d("AnimStart","Started")
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(3000)
                        lottieButton.cancelAnimation()

                    }
                }

                override fun onAnimationEnd(p0: Animator?) {
                    Log.d("AnimEnd","Animation ended")
                   holder.binding.btnAddToCart.isVisible = true
                   holder.binding.btnAddToCartAnim.isInvisible = true

                }

                override fun onAnimationCancel(p0: Animator?) {

                }

                override fun onAnimationRepeat(p0: Animator?) {

                }

            })


            Toast.makeText(homeActivity, "Item Added to Cart", Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun productList(list1:MutableList<OnlineEntity>){
        list.clear()
        list=list1
        Log.d("myTAG","list in adapter $list1")
        notifyDataSetChanged()
    }

}

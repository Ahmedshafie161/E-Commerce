package com.example.shafie.presentation.ui.home

import android.animation.Animator
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.shafie.databinding.AdapterHomeRecyclerBinding
import com.example.shafie.domain.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeRecylerAdapter(
    private val onAddToCart: (Product) -> Unit
) :
    RecyclerView.Adapter<HomeRecylerAdapter.ViewHolder>() {

    var list = mutableListOf<Product>()
    lateinit var lottieButton: LottieAnimationView

    inner class ViewHolder(private val binding: AdapterHomeRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            with(binding) {
                Glide.with(binding.root.context)
                    .load(item.imageUrl)
                    .into(ivProductImage)

                tvProductName.text = item.name
                tvProductPrice.text = item.price
                tvProductRating.rating = item.rating.toFloat()
                lottieButton = btnAddToCartAnim

                btnAddToCart.setOnClickListener {
                    btnAddToCart.isInvisible = true
                    btnAddToCartAnim.isVisible = true
                    onAddToCart(item)
                    lottieButton.playAnimation()
                    lottieButton.addAnimatorListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(p0: Animator?) {
                            Log.d("AnimStart", "Started")
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(3000)
                                lottieButton.cancelAnimation()

                            }
                        }

                        override fun onAnimationEnd(p0: Animator?) {
                            Log.d("AnimEnd", "Animation ended")
                            btnAddToCart.isVisible = true
                            btnAddToCartAnim.isInvisible = true

                        }

                        override fun onAnimationCancel(p0: Animator?) {

                        }

                        override fun onAnimationRepeat(p0: Animator?) {

                        }

                    })


//                        Toast.makeText(homeActivity, "Item Added to Cart", Toast.LENGTH_SHORT).show()

                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            AdapterHomeRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun productList(list1: MutableList<Product>) {
        list.clear()
        list = list1
        Log.d("myTAG", "list in adapter $list1")
        notifyDataSetChanged()
    }

}
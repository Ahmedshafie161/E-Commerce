package com.example.shafie.presentation.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shafie.CustomApplication
import com.example.shafie.R
import com.example.shafie.data.remote.dtos.ProductDto
import com.example.shafie.data.repo.ProductRepoImpl
import com.example.shafie.databinding.ActivityHomeBinding
import com.example.shafie.domain.model.Product
import com.example.shafie.presentation.ui.cart.CartFragment
import com.example.shafie.presentation.viewmodel.home.HomeViewModel

class HomeActivity : AppCompatActivity(), AddCartListener {

    private lateinit var repository: ProductRepoImpl
    private lateinit var mViewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var myAdapter: HomeRecylerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        supportActionBar?.hide()

        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // initializing viewModel
        repository = (applicationContext as CustomApplication).productRepository

        //setting recycler view
        setRecyclerView()


        // observing product data from api through viewModel
        mViewModel.productLiveData.observe({ lifecycle }, {
            if (it.isNotEmpty())
                myAdapter.productList(it as MutableList<Product>)
            binding.rvGadgets.adapter?.notifyDataSetChanged()
        })

        //making fragment transaction to CartFragment on click of imageview shopping cart.
        binding.ivCart.setOnClickListener {
            supportFragmentManager.beginTransaction().addToBackStack(null)
                .add(R.id.homeActivity, CartFragment()).commit()
        }
    }

    private fun setRecyclerView() {
        binding.rvGadgets.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            myAdapter = HomeRecylerAdapter { productDetails ->
                val product = ProductDto(
                    imageUrl = productDetails.imageUrl,
                    name = productDetails.name, price = productDetails.price,
                    rating = productDetails.rating
                )
                addItemToCart(productDetails = product)

            }
            adapter = myAdapter
        }

    }

    //overridden method from interface clickListener
    // implemented to listen the click at the Add to Cart Button
    // and save clicked item to room database

    override fun addItemToCart(productDetails: ProductDto) {


        /*  CoroutineScope(Dispatchers.IO).launch {
              repository.addProductsToDatabase(product)

          }*/
    }
}
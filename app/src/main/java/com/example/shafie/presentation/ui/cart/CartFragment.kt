package com.example.shafie.presentation.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shafie.CustomApplication
import com.example.shafie.R
import com.example.shafie.data.dbhelper.DbEntity
import com.example.shafie.data.repo.ProductRepo
import com.example.shafie.databinding.FragmentCartBinding
import com.example.shafie.presentation.ui.load.LoadFragment
import com.example.shafie.presentation.viewmodel.cart.CartViewModel
import com.example.shafie.presentation.viewmodel.cart.MainViewModelFactoryCart

class CartFragment : Fragment() {

    private lateinit var binding:FragmentCartBinding
    private lateinit var mAdapter: CartRecyclerAdapter
    private lateinit var repository: ProductRepo
    private lateinit var mViewModel:CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCartBinding.inflate(inflater)

        //initializing viewModel
        repository  = ( requireContext().applicationContext as CustomApplication).productRepository
        mViewModel = ViewModelProvider(this,MainViewModelFactoryCart(repository))[CartViewModel::class.java]

        //setting recycler view
        binding.rvCart.apply {
            layoutManager=LinearLayoutManager(context)
            mAdapter= CartRecyclerAdapter(this@CartFragment)
            adapter=mAdapter
        }

        //observing live data
        mViewModel.productLiveData.observe(viewLifecycleOwner){

            if(it != null)
            {
                Log.d("TAG","CartFragment livedata $it")
                mAdapter.productListInCart(it as MutableList<DbEntity>)
                binding.btnCheckout.isVisible=true
                binding.tvNoItems.isInvisible=true
                binding.rvCart.isVisible=true
            }
            else{

                binding.btnCheckout.isInvisible=true
                binding.tvNoItems.isVisible=true
                binding.rvCart.isInvisible=true

                Log.d("TAG","CartFragment livedata is null $it")

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheckout.setOnClickListener {

            activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                ?.replace(R.id.cartFragment,LoadFragment())?.commit()
        }
    }


}
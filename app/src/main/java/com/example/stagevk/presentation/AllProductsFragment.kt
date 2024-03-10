package com.example.stagevk.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stagevk.R
import com.example.stagevk.data.retrofit.ApiFactory
import com.example.stagevk.databinding.FragmentAllProductsBinding
import com.example.stagevk.domain.entities.Category
import com.example.stagevk.domain.entities.ListItems
import com.example.stagevk.presentation.adapter.ProductAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AllProductsFragment : Fragment() {

    private var _binding: FragmentAllProductsBinding? = null
    private val binding: FragmentAllProductsBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private val myViwModel: MainViewModel by viewModels()
    private lateinit var myAdapter: ProductAdapter
    private lateinit var  myAdapterCategory: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        doListeners()
        doObservers()
        myViwModel.loadAllProducts()
        myViwModel.loadAllCategories()
    }

    private fun doObservers() {
        myViwModel.listProductsLD.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }

        myViwModel.listAllCategoriesLD.observe(viewLifecycleOwner){
            myAdapterCategory.submitList(it)
        }

        myViwModel.errorLD.observe(viewLifecycleOwner) {
            if (it) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container_main, ErrorNetFragment())
                    ?.commit()
                Toast.makeText(activity, "Error of network", Toast.LENGTH_SHORT).show()
            }
        }

        myViwModel.progressBarLD.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        myViwModel.pageLD.observe(viewLifecycleOwner) {
            binding.pageCounter.text = it.toString()
        }
    }

    private fun doListeners() {
        binding.btnNext.setOnClickListener {
            myViwModel.incrementCounter()
            myViwModel.incrementPage()
            myViwModel.loadAllProducts()

            if (myAdapter.currentList.isEmpty()){
                Toast.makeText(activity, "Products not found", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPrev.setOnClickListener {
            myViwModel.decrementCounter()
            myViwModel.decrementPage()
            myViwModel.loadAllProducts()
        }

        myAdapter.onClickProduct = {
            val fragment = DetailFragment.newInstance(
                it.thumbnail,
                it.title,
                it.description,
                it.rating,
                it.brand,
                it.category,
                it.price,
                it.images
            )
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container_main, fragment)
                ?.commit()
        }

        myAdapterCategory.onClickCategory = {
            val fragment = CategoryFragment.newInstance(it.categoryName)
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container_main, fragment)
                ?.commit()
        }
    }

    private fun initViews() {
        myAdapter = ProductAdapter()
        myAdapterCategory = ProductAdapter()
        binding.recycler.adapter = myAdapter
        binding.recyclerCategory.adapter =  myAdapterCategory
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
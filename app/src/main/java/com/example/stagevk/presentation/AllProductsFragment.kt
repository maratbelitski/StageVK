package com.example.stagevk.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.stagevk.R
import com.example.stagevk.databinding.FragmentAllProductsBinding
import com.example.stagevk.presentation.adapter.ProductAdapter

class AllProductsFragment : Fragment() {

    private var _binding: FragmentAllProductsBinding? = null
    private val binding: FragmentAllProductsBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private val myViwModel: MainViewModel by viewModels()
    private lateinit var myAdapter: ProductAdapter


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

        myViwModel.loadAllProducts()

        myViwModel.listProductsLD.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
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
    }

    private fun doListeners() {
        binding.btnNext.setOnClickListener {
            myViwModel.incrementCounter()
            myViwModel.loadAllProducts()
            binding.pageCounter.text = myViwModel.incrementPage()
        }

        binding.btnPrev.setOnClickListener {
            myViwModel.decrementCounter()
            myViwModel.loadAllProducts()
            binding.pageCounter.text = myViwModel.decrementPage()
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
                it.images)
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container_main, fragment)
                ?.commit()
        }
    }

    private fun initViews() {
        myAdapter = ProductAdapter()
        binding.recycler.adapter = myAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
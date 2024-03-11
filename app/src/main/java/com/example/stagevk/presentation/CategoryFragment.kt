package com.example.stagevk.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.stagevk.R
import com.example.stagevk.databinding.FragmentCategoryBinding
import com.example.stagevk.presentation.adapter.ProductAdapter

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding: FragmentCategoryBinding
        get() = _binding ?: throw RuntimeException(resources.getString(R.string.fragmentCategory_is_null))

    private val myViwModel: MainViewModel by viewModels()
    private lateinit var myAdapter: ProductAdapter

    companion object {
        private const val CATEGORY_NAME = "category_name"

        fun newInstance(categoryName: String?) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY_NAME, categoryName)
                }
            }
    }

    private var categoryName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryName = it.getString(CATEGORY_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        doListeners()

        myViwModel.loadOneCategoryProducts(categoryName ?: resources.getString(R.string.unknown))

        myViwModel.listOneCategoryLD.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }
    }

    private fun doListeners() {
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
    }

    private fun initViews() {
        myAdapter = ProductAdapter()
        binding.recycler.adapter = myAdapter

        binding.tvCategoryName.text = categoryName
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
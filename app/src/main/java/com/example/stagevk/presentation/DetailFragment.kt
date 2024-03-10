package com.example.stagevk.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.stagevk.R
import com.example.stagevk.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailBinding is null")

    companion object {
        private const val IMAGE = "image"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val RATING = "rating"
        private const val BRAND = "brand"
        private const val CATEGORY = "category"
        private const val PRICE = "price"
        private const val IMAGES = "images"

        @JvmStatic
        fun newInstance(
            image: String,
            title: String,
            description: String,
            rating: Double,
            brand: String,
            category: String,
            price: Int,
            images: ArrayList<String>
        ) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE, image)
                    putString(TITLE, title)
                    putString(DESCRIPTION, description)
                    putDouble(RATING, rating)
                    putString(BRAND, brand)
                    putString(CATEGORY, category)
                    putInt(PRICE, price)
                    putStringArrayList(IMAGES, images)
                }
            }
    }

    private var image: String? = null
    private var title: String? = null
    private var description: String? = null
    private var rating: Double? = null
    private var brand: String? = null
    private var category: String? = null
    private var price: Int? = null
    private var images: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString(IMAGE)
            title = it.getString(TITLE)
            description = it.getString(DESCRIPTION)
            rating = it.getDouble(RATING)
            brand = it.getString(BRAND)
            category = it.getString(CATEGORY)
            price = it.getInt(PRICE)
            images = it.getStringArrayList(IMAGES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTextView()
        initImage()
        doListeners()
    }

    private fun initImage() {
        Glide.with(binding.ivDetailImage)
            .load(image)
            .into(binding.ivDetailImage)

        if (images?.size!! == 1) {
            initImageDetail(binding.imageView2, 0)

        } else if (images?.size!! == 2) {
            with(binding) {
                initImageDetail(imageView2, 0)
                initImageDetail(imageView3, 1)
            }
        } else if (images?.size!! == 3) {
            with(binding) {
                initImageDetail(imageView2, 0)
                initImageDetail(imageView3, 1)
                initImageDetail(imageView4, 2)
            }
        } else if (images?.size!! == 4) {
            with(binding) {
                initImageDetail(imageView2, 0)
                initImageDetail(imageView3, 1)
                initImageDetail(imageView4, 2)
                initImageDetail(imageView5, 3)
            }
        }
    }

    private fun initImageDetail(view: ImageView, index: Int) {
        view.visibility = View.VISIBLE

        Glide.with(view.context)
            .load(images?.get(index))
            .into(view)
    }

    private fun doListeners() {
        with(binding) {
            imageView2.setOnClickListener {
                loadFragment(0)
            }

            imageView3.setOnClickListener {
                loadFragment(1)
            }

            imageView4.setOnClickListener {
                loadFragment(2)
            }

            imageView5.setOnClickListener {
                loadFragment(3)
            }
        }
    }

    private fun loadFragment(index: Int) {
        val fragment = ImageFragment.newInstance(images?.get(index))
        activity?.supportFragmentManager?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(R.id.container_main, fragment)
            ?.commit()
    }

    private fun initTextView() {
        with(binding) {
            ivDetailTitle.text = title.toString()
            tvDetailDescription.text = description.toString()
            tvDetailRating.text = rating.toString()
            tvDetailBrand.text = brand.toString()
            tvDetailCategory.text = category.toString()
            tvDetailPrice.text = price.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
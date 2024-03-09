package com.example.stagevk.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.ivDetailTitle.text = title.toString()
        binding.tvDetailDescription.text = description.toString()
        binding.tvDetailRating.text = rating.toString()
        binding.tvDetailBrand.text = brand.toString()
        binding.tvDetailCategory.text = category.toString()
        binding.tvDetailPrice.text = price.toString()

        binding.imageView2.setOnClickListener {
            val fragment = ImageFragment.newInstance(images?.get(0))
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container_main, fragment)
                ?.commit()
        }

        binding.imageView3.setOnClickListener {
            val fragment = ImageFragment.newInstance(images?.get(1))
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container_main, fragment)
                ?.commit()
        }

        binding.imageView4.setOnClickListener {
            val fragment = ImageFragment.newInstance(images?.get(2))
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container_main, fragment)
                ?.commit()
        }

        binding.imageView5.setOnClickListener {
            val fragment = ImageFragment.newInstance(images?.get(3))
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container_main, fragment)
                ?.commit()
        }

        Glide.with(view.context)
            .load(image)
            .into(binding.ivDetailImage)

        if (images?.size!! == 1) {
            binding.imageView2.visibility = View.VISIBLE

            Glide.with(view.context)
                .load(images?.get(0))
                .into(binding.imageView2)
        } else if (images?.size!! == 2) {
            binding.imageView2.visibility = View.VISIBLE
            binding.imageView3.visibility = View.VISIBLE

            Glide.with(view.context)
                .load(images?.get(0))
                .into(binding.imageView2)

            Glide.with(view.context)
                .load(images?.get(1))
                .into(binding.imageView3)

        } else if (images?.size!! == 3) {
            binding.imageView2.visibility = View.VISIBLE
            binding.imageView3.visibility = View.VISIBLE
            binding.imageView4.visibility = View.VISIBLE

            Glide.with(view.context)
                .load(images?.get(0))
                .into(binding.imageView2)

            Glide.with(view.context)
                .load(images?.get(1))
                .into(binding.imageView3)

            Glide.with(view.context)
                .load(images?.get(2))
                .into(binding.imageView4)

        } else if (images?.size!! == 4) {
            binding.imageView2.visibility = View.VISIBLE
            binding.imageView3.visibility = View.VISIBLE
            binding.imageView4.visibility = View.VISIBLE
            binding.imageView5.visibility = View.VISIBLE

            Glide.with(view.context)
                .load(images?.get(0))
                .into(binding.imageView2)

            Glide.with(view.context)
                .load(images?.get(1))
                .into(binding.imageView3)

            Glide.with(view.context)
                .load(images?.get(2))
                .into(binding.imageView4)

            Glide.with(view.context)
                .load(images?.get(3))
                .into(binding.imageView5)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
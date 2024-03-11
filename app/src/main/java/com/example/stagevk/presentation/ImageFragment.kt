package com.example.stagevk.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.stagevk.R
import com.example.stagevk.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding: FragmentImageBinding
        get() = _binding ?: throw RuntimeException(resources.getString(R.string.fragmentImage_is_null))


    companion object {
        private const val IMAGE_DETAIL = "image"

        fun newInstance(image: String?) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_DETAIL, image)
                }
            }
    }

    private var image: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString(IMAGE_DETAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view.context)
            .load(image)
            .into(binding.imageDetail)

        binding.imageDetail.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
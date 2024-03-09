package com.example.stagevk.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.stagevk.R
import com.example.stagevk.databinding.FragmentAllProductsBinding
import com.example.stagevk.databinding.FragmentErrorNetBinding

class ErrorNetFragment : Fragment() {

    private var _binding: FragmentErrorNetBinding? = null
    private val binding: FragmentErrorNetBinding
        get() = _binding ?: throw RuntimeException("FragmentErrorNetBinding is null")

    private val myViwModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorNetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReload.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container_main, AllProductsFragment())
                ?.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
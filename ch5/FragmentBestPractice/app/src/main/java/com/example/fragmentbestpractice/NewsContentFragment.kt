package com.example.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentbestpractice.databinding.NewsContentFragBinding

class NewsContentFragment : Fragment() {

    private var _binding: NewsContentFragBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsContentFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun refresh(title: String, content: String) {
        binding.apply {
            contentLayout.visibility = View.VISIBLE
            newsTitle.text = title
            newsContent.text = content
        }
    }

}
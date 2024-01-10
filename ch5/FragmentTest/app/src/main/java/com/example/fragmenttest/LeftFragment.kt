package com.example.fragmenttest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmenttest.databinding.LeftFragmentBinding

class LeftFragment : Fragment() {

    companion object {
        const val TAG = "LeftFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.left_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = LeftFragmentBinding.bind(view)
        binding.button.setOnClickListener {
            Log.d(TAG, "button clicked.")
            (activity as MainActivity).replaceFragment(AnotherRightFragment())
        }
    }

}
package com.makinul.bd.pass.ui.auth.number

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.makinul.bd.pass.R
import com.makinul.bd.pass.base.BaseFragment
import com.makinul.bd.pass.databinding.FragmentNumberBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NumberFragment : BaseFragment() {

    private var _binding: FragmentNumberBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberBinding.inflate(inflater, container, false)

        binding.number1.setOnClickListener {
            gotoOtpWithDelay()
        }
        binding.number2.setOnClickListener {
            gotoOtpWithDelay()
        }
        binding.number3.setOnClickListener {
            gotoOtpWithDelay()
        }

        return binding.root
    }

    private fun gotoOtpWithDelay() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            delay(2000)
            findNavController().navigate(R.id.action_number_to_otp)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
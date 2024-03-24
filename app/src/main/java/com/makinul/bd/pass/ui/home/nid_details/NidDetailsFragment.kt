package com.makinul.bd.pass.ui.home.nid_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.makinul.bd.pass.databinding.FragmentNidDetailsBinding
import com.makinul.bd.pass.databinding.FragmentProfileBinding

class NidDetailsFragment : Fragment() {

    private var _binding: FragmentNidDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[NidDetailsViewModel::class.java]

        _binding = FragmentNidDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
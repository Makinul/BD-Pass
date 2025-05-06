package com.makinul.bd.pass.ui.home.sim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makinul.bd.pass.R
import com.makinul.bd.pass.databinding.FragmentSimBinding

class SimFragment : Fragment() {

    private var _binding: FragmentSimBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSimBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.grameen.title.text = getString(R.string.number_grameen)
        binding.grameen.subtitle.text = getString(R.string.sim_info_grameen)
        binding.grameen.icon.setImageResource(R.drawable.ic_grameen)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
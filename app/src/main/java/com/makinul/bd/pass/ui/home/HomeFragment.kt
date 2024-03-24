package com.makinul.bd.pass.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.makinul.bd.pass.R
import com.makinul.bd.pass.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position ?: 0) {
                    1 -> {
                        binding.nidLay.root.visibility = View.GONE
                        binding.passportLay.root.visibility = View.VISIBLE
                        binding.drivingLicenseLay.root.visibility = View.GONE
                    }

                    2 -> {
                        binding.nidLay.root.visibility = View.GONE
                        binding.passportLay.root.visibility = View.GONE
                        binding.drivingLicenseLay.root.visibility = View.VISIBLE
                    }

                    else -> {
                        binding.nidLay.root.visibility = View.VISIBLE
                        binding.passportLay.root.visibility = View.GONE
                        binding.drivingLicenseLay.root.visibility = View.GONE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.nidLay.fullDetails.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_nid_details)
        }

        binding.nidWebsite.setOnClickListener {

        }
        binding.drivingLicenseWebsite.setOnClickListener {

        }
        binding.passportWebsite.setOnClickListener {

        }
        binding.policeWebsite.setOnClickListener {

        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HomeActivity?)?.setTitle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
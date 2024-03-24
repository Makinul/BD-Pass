package com.makinul.bd.pass.ui.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.makinul.bd.pass.R
import com.makinul.bd.pass.base.BaseFragment
import com.makinul.bd.pass.databinding.FragmentLoginBinding
import com.makinul.bd.pass.ui.home.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.doNotHaveAccount.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.loginBtn.setOnClickListener {
            if (binding.progressBar.visibility == View.VISIBLE)
                return@setOnClickListener

            val phoneNumber = binding.phoneNumberEdt.text.toString()
            val password = binding.passwordEdt.text.toString()
            if (phoneNumber.isEmpty() || password.isEmpty()) {
                showToast(getString(R.string.please_enter_phone_number_and_password))
                return@setOnClickListener
            }

            if (phoneNumber == "01779878433" && password == "123456")
                gotoOtpWithDelay()
            else
                showToast(getString(R.string.please_enter_correct_phone_number_and_password))
        }
    }

    private fun gotoOtpWithDelay() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            delay(2000)
            findNavController().navigate(R.id.action_login_to_number)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
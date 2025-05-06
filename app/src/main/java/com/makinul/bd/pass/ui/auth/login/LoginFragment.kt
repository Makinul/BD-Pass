package com.makinul.bd.pass.ui.auth.login

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.makinul.bd.pass.R
import com.makinul.bd.pass.base.BaseFragment
import com.makinul.bd.pass.databinding.FragmentLoginBinding
import com.makinul.bd.pass.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

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

        binding.doNotHaveAnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_registration)
        }

        binding.dob.setOnClickListener {
            showDatePickerDialog()
        }

        binding.smartRegistrationLay.setOnClickListener {
            gotoOtpWithDelay()
        }

        binding.loginBtn.setOnClickListener {
            if (binding.progressBar.visibility == View.VISIBLE)
                return@setOnClickListener

            if (binding.progressBar.visibility == View.VISIBLE)
                return@setOnClickListener

            val number = binding.phoneNumberEdt.text.toString()
            val dob = binding.dob.text.toString()
            if (number.isEmpty() || dob.isEmpty()) {
                showToast(getString(R.string.please_enter_phone_number_and_dob))
                return@setOnClickListener
            }
            gotoOtpWithDelay()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->
            val monthString = if ((monthOfYear + 1) < 10) {
                "0${monthOfYear + 1}"
            } else {
                (monthOfYear + 1).toString()
            }
            val dayOfMonthString = if (dayOfMonth < 10) {
                "0$dayOfMonth"
            } else {
                dayOfMonth.toString()
            }
            val dob = "$dayOfMonthString/$monthString/$year"
            binding.dob.text = dob
        }, currentYear, currentMonth, currentDayOfMonth)

        dpd.show()
    }

    private fun gotoOtpWithDelay() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            delay(2000)

            val args = Bundle()
            args.putBoolean(AppConstants.KEY_IS_IT_FROM_REGISTRATION, false)
            findNavController().navigate(R.id.action_login_to_otp, args)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
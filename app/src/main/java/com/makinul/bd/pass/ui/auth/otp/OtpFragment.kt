package com.makinul.bd.pass.ui.auth.otp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.makinul.bd.pass.R
import com.makinul.bd.pass.base.BaseFragment
import com.makinul.bd.pass.databinding.FragmentOtpBinding
import com.makinul.bd.pass.ui.home.HomeActivity
import com.makinul.bd.pass.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class OtpFragment : BaseFragment() {

    private var isItFromRegistration: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            isItFromRegistration = it.getBoolean(AppConstants.KEY_IS_IT_FROM_REGISTRATION, true)
        }
    }

    private var _binding: FragmentOtpBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.continueButton.setOnClickListener {
//            if (binding.progressBar.visibility == View.VISIBLE)
//                return@setOnClickListener
//
//            val otp = binding.otpView.otp ?: ""
//            if (otp == "123456")
//                gotoHomeWithDelay()
//            else
//                showToast(getString(R.string.please_enter_correct_otp_number))
            gotoVerificationWithDelay()
        }

        countDownTimer.start()
    }

    private val countDownTimer: CountDownTimer = object : CountDownTimer(120000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val sec = millisUntilFinished / 1000
            val min = sec / 60
            val remainingSec: String = if ((sec % 60) < 10) "0${(sec % 60)}" else "${(sec % 60)}"

            val message = "$min: $remainingSec"

            binding.otpMessage.text = getString(R.string.otp_verification_message, message)
        }

        override fun onFinish() {
            binding.otpMessage.text = getString(R.string.otp_verification_timeout)
        }
    }

    private fun gotoVerificationWithDelay() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            delay(2000)

            if (isItFromRegistration) {
                findNavController().navigate(R.id.action_otp_to_verification)
            } else {
                gotoHome()
            }
        }
    }

    private fun gotoHome() {
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
        _binding = null
    }
}
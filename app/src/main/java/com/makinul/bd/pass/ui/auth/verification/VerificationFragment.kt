package com.makinul.bd.pass.ui.auth.verification

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.makinul.bd.pass.R
import com.makinul.bd.pass.base.BaseFragment
import com.makinul.bd.pass.databinding.FragmentVerificationBinding
import com.makinul.bd.pass.ui.home.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class VerificationFragment : BaseFragment() {

    private var _binding: FragmentVerificationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerificationBinding.inflate(inflater, container, false)

        binding.continueBtn.setOnClickListener {
            if (binding.progressBar.visibility == View.VISIBLE)
                return@setOnClickListener
            when (step) {
                1 -> {
                    step = 2
                    updateStepsView()
                }

                2 -> {
                    step = 3
                    updateStepsView()
                }

                else -> {
                    step = 4
                    gotoHomeWithDelay()
                }
            }
        }
        binding.nidScanProgress.setOnClickListener {
            step = 1
            updateStepsView()
        }
        binding.confirmInfoProgress.setOnClickListener {
            step = 2
            updateStepsView()
        }
        binding.pictureProgress.setOnClickListener {
            step = 3
            updateStepsView()
        }
        return binding.root
    }

    private var step = 1 // 1 = nid copy pic, 2 = verification info, 3 = nid pic, 4 = finish

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.stepsView.setLabels(steps)
//            .setBarColorIndicator(context.getResources().getColor(R.color.material_blue_grey_800))
//            .setProgressColorIndicator(getContext().getResources().getColor(R.color.orange))
//            .setLabelColorIndicator(getContext().getResources().getColor(R.color.orange))
//            .setCompletedPosition(0)
//            .drawView()

        updateStepsView()

    }

    private fun updateStepsView() {
        when (step) {
            1 -> {
                binding.continueBtn.setText(R.string.take_a_picture)
                binding.nidScanProgress.setBackgroundResource(R.drawable.steps_progress_selected)
                binding.confirmInfoProgress.setBackgroundResource(R.drawable.steps_progress_normal)
                binding.pictureProgress.setBackgroundResource(R.drawable.steps_progress_normal)
                binding.finishProgress.setBackgroundResource(R.drawable.steps_progress_normal)

                binding.nidCopyPic.visibility = View.VISIBLE
                binding.nidInfoLay.visibility = View.GONE
                binding.nidPic.visibility = View.GONE
            }
            2 -> {
                binding.continueBtn.setText(R.string.continue_text)
                binding.nidScanProgress.setBackgroundResource(R.drawable.steps_progress_selected)
                binding.confirmInfoProgress.setBackgroundResource(R.drawable.steps_progress_selected)
                binding.pictureProgress.setBackgroundResource(R.drawable.steps_progress_normal)
                binding.finishProgress.setBackgroundResource(R.drawable.steps_progress_normal)

                binding.nidCopyPic.visibility = View.GONE
                binding.nidInfoLay.visibility = View.VISIBLE
                binding.nidPic.visibility = View.GONE
            }
            3 -> {
                binding.continueBtn.setText(R.string.continue_text)
                binding.nidScanProgress.setBackgroundResource(R.drawable.steps_progress_selected)
                binding.confirmInfoProgress.setBackgroundResource(R.drawable.steps_progress_selected)
                binding.pictureProgress.setBackgroundResource(R.drawable.steps_progress_selected)
                binding.finishProgress.setBackgroundResource(R.drawable.steps_progress_normal)

                binding.nidCopyPic.visibility = View.GONE
                binding.nidInfoLay.visibility = View.GONE
                binding.nidPic.visibility = View.VISIBLE
            }
            else -> {
                binding.continueBtn.setText(R.string.continue_text)
            }
        }
    }

    private fun gotoHomeWithDelay() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            delay(2000)
            gotoHome()
        }
    }

    private fun gotoHome() {
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
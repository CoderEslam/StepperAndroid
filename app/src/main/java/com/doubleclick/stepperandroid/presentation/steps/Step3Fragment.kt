package com.doubleclick.stepperandroid.presentation.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.doubleclick.stepperandroid.databinding.SizeChangeFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Fragment for holding and controlling views for the third step.
 */
@ExperimentalCoroutinesApi
class Step3Fragment : Fragment() {

    private lateinit var viewBinding: SizeChangeFragmentBinding

    private val viewModel: StepperViewModel by lazy { ViewModelProvider(requireActivity())[StepperViewModel::class.java] }

    /**
     * Setup view.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = SizeChangeFragmentBinding.inflate(inflater, container, false).apply {
            setupUI()
        }
        return viewBinding.root
    }

    private fun SizeChangeFragmentBinding.setupUI() {
        seekSize.max = 26
        seekSize.progress = viewModel.stepperSettings.value.textSize - 18
        seekSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                viewModel.updateStepper(viewModel.stepperSettings.value.copy(textSize = progress + 18))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}

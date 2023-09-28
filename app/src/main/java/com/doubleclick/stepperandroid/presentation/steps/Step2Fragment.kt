package com.doubleclick.stepperandroid.presentation.steps

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.doubleclick.stepperandroid.databinding.ColorChangeFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Fragment for holding and controlling views for the first step.
 */
@ExperimentalCoroutinesApi
class Step2Fragment : Fragment() {

    private lateinit var viewBinding: ColorChangeFragmentBinding

    private val viewModel: StepperViewModel by lazy { ViewModelProvider(requireActivity())[StepperViewModel::class.java] }

    private val onSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(
            seekBar: SeekBar?,
            progress: Int,
            fromUser: Boolean
        ) {
            viewBinding.run {
                viewModel.updateStepper(
                    viewModel.stepperSettings.value.copy(
                        iconColor = Color.rgb(
                            seekRed.progress,
                            seekGreen.progress,
                            seekBlue.progress
                        )
                    )
                )
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    /**
     * Setup view.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = ColorChangeFragmentBinding.inflate(inflater, container, false).apply {
            setupUI()
        }
        return viewBinding.root
    }

    private fun ColorChangeFragmentBinding.setupUI() {
        val iconColor = viewModel.stepperSettings.value.iconColor
        iconColor.run {
            seekRed.progress = red
            seekGreen.progress = green
            seekBlue.progress = blue
        }
        seekRed.setOnSeekBarChangeListener(onSeekBarChangeListener)
        seekGreen.setOnSeekBarChangeListener(onSeekBarChangeListener)
        seekBlue.setOnSeekBarChangeListener(onSeekBarChangeListener)
    }
}

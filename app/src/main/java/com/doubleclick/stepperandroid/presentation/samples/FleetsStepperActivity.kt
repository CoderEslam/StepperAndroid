package com.doubleclick.stepperandroid.presentation.samples

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.doubleclick.stepper.StepperNavigationView
import com.doubleclick.stepper.StepperNavListener
import com.doubleclick.stepper.menus.fleets.FleetsStepperMenu
import com.doubleclick.stepperandroid.R
import com.doubleclick.stepperandroid.databinding.FleetsStepperActivityBinding
import com.doubleclick.stepperandroid.models.StepperSettings
import com.doubleclick.stepperandroid.presentation.steps.StepperViewModel
import com.doubleclick.util.ui.findNavControllerFromFragmentContainer
import com.doubleclick.util.ui.showToast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Activity showing an sample usage of a fleets stepper.
 */
@ExperimentalCoroutinesApi
class FleetsStepperActivity : AppCompatActivity(), StepperNavListener {

    private val viewModel: StepperViewModel by lazy { ViewModelProvider(this)[StepperViewModel::class.java] }
    private lateinit var binding: FleetsStepperActivityBinding

    /**
     * Setup stepper and activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FleetsStepperActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stepper.initializeStepper()

        setSupportActionBar(binding.toolbar)

        // Setup Action bar for title with top-level destinations.
        setupActionBarWithNavController(
            findNavControllerFromFragmentContainer(R.id.frame_stepper),
            AppBarConfiguration.Builder(
                R.id.step_1_dest,
                R.id.step_2_dest,
                R.id.step_3_dest,
                R.id.step_4_dest
            ).build()
        )

        binding.buttonPrevious.setOnClickListener {
            binding.stepper.goToPreviousStep()
        }

        binding.buttonNext.setOnClickListener {
            binding.stepper.goToNextStep()
        }

        binding.frameStepper.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.performClick()
                    (binding.stepper.menu as FleetsStepperMenu).pause()
                }
                MotionEvent.ACTION_UP -> {
                    (binding.stepper.menu as FleetsStepperMenu).resume()
                }
            }
            true
        }

        collectStateFlow()
    }

    private fun StepperNavigationView.initializeStepper() {
        viewModel.updateStepper(
            StepperSettings(
                widgetColor,
                textColor,
                textSize,
                iconSize
            )
        )

        stepperNavListener = this@FleetsStepperActivity
        setupWithNavController(findNavControllerFromFragmentContainer(R.id.frame_stepper))
    }

    private fun collectStateFlow() {
        viewModel.stepperSettings.onEach {
            binding.stepper.widgetColor = it.iconColor
            binding.stepper.textColor = it.textColor
            binding.stepper.textSize = it.textSize
            binding.stepper.iconSize = it.iconSize
        }.launchIn(lifecycleScope)
    }

    override fun onStepChanged(step: Int) {
        showToast("Step changed to: $step")

        binding.buttonPrevious.isVisible = step != 0

        if (step == 3) {
            binding.buttonNext.setImageResource(R.drawable.ic_check)
        } else {
            binding.buttonNext.setImageResource(R.drawable.ic_right)
        }
    }

    override fun onCompleted() {
        showToast("Stepper completed")
    }
}

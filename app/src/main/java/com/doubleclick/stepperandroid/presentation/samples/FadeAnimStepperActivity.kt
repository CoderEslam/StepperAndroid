package com.doubleclick.stepperandroid.presentation.samples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.setupActionBarWithNavController
import com.doubleclick.stepper.StepperNavigationView
import com.doubleclick.stepper.StepperNavListener
import com.doubleclick.stepperandroid.R
import com.doubleclick.stepperandroid.databinding.TabStepperActivityBinding
import com.doubleclick.stepperandroid.models.StepperSettings
import com.doubleclick.stepperandroid.presentation.steps.StepperViewModel
import com.doubleclick.util.ui.findNavControllerFromFragmentContainer
import com.doubleclick.util.ui.showToast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Activity showing an sample usage of custom transition animations.
 */
@ExperimentalCoroutinesApi
class FadeAnimStepperActivity : AppCompatActivity(), StepperNavListener {

    private val viewModel: StepperViewModel by lazy { ViewModelProvider(this)[StepperViewModel::class.java] }
    private lateinit var binding: TabStepperActivityBinding

    /**
     * Setup stepper and activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TabStepperActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.tab_stepper_activity)
        binding.stepper.initializeStepper()

        setSupportActionBar(binding.toolbar)

        // Setup Action bar for title and up navigation.
        setupActionBarWithNavController(findNavControllerFromFragmentContainer(R.id.frame_stepper))

        binding.buttonNext.setOnClickListener {
            binding.stepper.goToNextStep()
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

        stepperNavListener = this@FadeAnimStepperActivity
        setupWithNavController(findNavControllerFromFragmentContainer(R.id.frame_stepper)) {
            enter = android.R.anim.fade_in
            exit = android.R.anim.fade_out
            popEnter = android.R.anim.fade_in
            popExit = android.R.anim.fade_out
        }
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
        showToast("Step changed to: ${step + 1}")
        if (step == 3) {
            binding.buttonNext.setImageResource(R.drawable.ic_check)
        } else {
            binding.buttonNext.setImageResource(R.drawable.ic_right)
        }
    }

    override fun onCompleted() {
        showToast("Stepper completed")
    }

    /**
     * Use navigation controller to navigate up.
     */
    override fun onSupportNavigateUp(): Boolean =
        findNavControllerFromFragmentContainer(R.id.frame_stepper).navigateUp()

    /**
     * Navigate up when the back button is pressed.
     */
    override fun onBackPressed() {
        if (binding.stepper.currentStep == 0) {
            super.onBackPressed()
        } else {
            findNavControllerFromFragmentContainer(R.id.frame_stepper).navigateUp()
        }
    }
}

package com.doubleclick.stepperandroid.presentation.samples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.doubleclick.stepper.StepperNavListener
import com.doubleclick.stepperandroid.R
import com.doubleclick.stepperandroid.databinding.TabStepperWithoutNavigationComponentsActivityBinding
import com.doubleclick.util.ui.showToast

/**
 * Activity showing an sample usage of a tab stepper without navigation components.
 */
class TabStepperWithoutNavigationComponentsActivity : AppCompatActivity(), StepperNavListener {

    private val menuTitles = arrayListOf<String>()

    private lateinit var binding: TabStepperWithoutNavigationComponentsActivityBinding

    /**
     * Setup stepper and activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TabStepperWithoutNavigationComponentsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeStepper()
        initializeToolbar()
        initializeButtons()
    }

    private fun initializeStepper() {
        binding.stepper.stepperNavListener = this@TabStepperWithoutNavigationComponentsActivity
    }

    private fun initializeToolbar() {
        for (i in 0 until binding.stepper.menu.size()) {
            menuTitles.add(binding.stepper.menu.getItem(i).title.toString())
        }

        binding.toolbar.title = menuTitles[0]
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initializeButtons() {
        binding.buttonNext.setOnClickListener {
            binding.stepper.goToNextStep()
        }

        binding.buttonPrevious.setOnClickListener {
            binding.stepper.goToPreviousStep()
        }
    }

    override fun onStepChanged(step: Int) {
        showToast("Step changed to: $step")
        setupToolbarTitle(step)
        setupPreviousButtonVisibility(step)
        setupNextButtonImage(step)
        setupViewVisibility(step)
    }

    private fun setupToolbarTitle(step: Int) {
        binding.toolbar.title = menuTitles[step]
    }

    private fun setupPreviousButtonVisibility(step: Int) {
        binding.buttonPrevious.isVisible = step > 0
    }

    private fun setupNextButtonImage(step: Int) {
        if (step == 3) {
            binding.buttonNext.setImageResource(R.drawable.ic_check)
        } else {
            binding.buttonNext.setImageResource(R.drawable.ic_right)
        }
    }

    private fun setupViewVisibility(step: Int) {
        val isVisible = step % 2 == 0

        binding.layoutSizeChange.root.isVisible = isVisible
        binding.layoutColorChange.root.isVisible = !isVisible
    }

    override fun onCompleted() {
        showToast("Stepper completed")
    }
}

package com.doubleclick.stepperandroid.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.doubleclick.stepperandroid.models.ActivityItem
import com.doubleclick.stepperandroid.presentation.samples.FadeAnimStepperActivity
import com.doubleclick.stepperandroid.presentation.samples.FleetsStepperActivity
import com.doubleclick.stepperandroid.presentation.samples.NumberedTabStepperActivity
import com.doubleclick.stepperandroid.presentation.samples.ProgressStepperActivity
import com.doubleclick.stepperandroid.presentation.samples.StepperNoUpNavActivity
import com.doubleclick.stepperandroid.presentation.samples.TabStepperActivity
import com.doubleclick.stepperandroid.presentation.samples.TabStepperWithOverflowActivity
import com.doubleclick.stepperandroid.presentation.samples.TabStepperWithoutNavigationComponentsActivity
import com.doubleclick.stepperandroid.R
import com.doubleclick.stepperandroid.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Main activity that lists the samples.
 */
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /**
     * Setup activity and UI.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.recyclerActivities.layoutManager = LinearLayoutManager(this)
        binding.recyclerActivities.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.recyclerActivities.adapter = ActivityListAdapter().apply {
            submitList(
                listOf(
                    ActivityItem(
                        getString(R.string.title_no_up_nav_stepper),
                        StepperNoUpNavActivity::class.java
                    ),
                    ActivityItem(
                        getString(R.string.title_tab_stepper),
                        TabStepperActivity::class.java
                    ),
                    ActivityItem(
                        getString(R.string.title_tab_numbered_stepper),
                        NumberedTabStepperActivity::class.java
                    ),
                    ActivityItem(
                        getString(R.string.title_progress_stepper),
                        ProgressStepperActivity::class.java
                    ),
                    ActivityItem(
                        getString(R.string.title_fleets_stepper),
                        FleetsStepperActivity::class.java
                    ),
                    ActivityItem(
                        getString(R.string.title_fade_anim_stepper),
                        FadeAnimStepperActivity::class.java
                    ),
                    ActivityItem(
                        getString(R.string.title_without_navigation_components),
                        TabStepperWithoutNavigationComponentsActivity::class.java
                    ),
                    ActivityItem(
                        getString(R.string.title_with_overflow),
                        TabStepperWithOverflowActivity::class.java
                    )
                )
            )
        }
    }
}

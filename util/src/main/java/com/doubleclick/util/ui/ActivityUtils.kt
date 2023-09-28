package com.doubleclick.util.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

/**
 * Get a view model from the view model factory.
 *
 * @param T The view model class.
 * @param viewModelFactory The view model factory.
 *
 * @return The view model.
 */
inline fun <reified T : ViewModel> FragmentActivity.obtainViewModel(viewModelFactory: ViewModelProvider.Factory) =
    ViewModelProvider(this, viewModelFactory)[T::class.java]

/**
 * Find and return the nav controller from a fragment container.
 *
 * @param id the id of the fragment to fetch the nav controller from.
 *
 * @return the found nav controller or throw an error if it can't be found.
 */
fun AppCompatActivity.findNavControllerFromFragmentContainer(id: Int): NavController {
    val fragment = supportFragmentManager.findFragmentById(id)
    check(fragment is NavHostFragment) { ("Activity $this does not have a NavHostFragment") }
    return fragment.navController
}
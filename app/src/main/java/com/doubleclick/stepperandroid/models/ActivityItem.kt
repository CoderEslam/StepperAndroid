package com.doubleclick.stepperandroid.models

/**
 * Data class representing an activity in a list.
 *
 * @param T the activity class type.
 *
 * @property name the name of the activity.
 * @property activityClass the activity class.
 */
data class ActivityItem<T>(
    val name: String,
    val activityClass: Class<T>
)

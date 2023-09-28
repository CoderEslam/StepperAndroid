
package com.doubleclick.stepper.menus.progress

import android.view.MenuItem
import com.doubleclick.stepper.menus.base.StepperMenuItem

/**
 * Menu item for [ProgressStepperMenu].
 */
class ProgressStepperMenuItem(
    id: Int,
    groupId: Int = 0,
    order: Int = 0
) : StepperMenuItem(id, groupId, order) {

    /**
     * Do nothing since items don't have views.
     */
    override fun setTitle(title: CharSequence?): MenuItem = this

    /**
     * Do nothing since items don't have views.
     */
    override fun setTitle(title: Int): MenuItem = this

    /**
     * Returns an empty string since items don't have views.
     */
    override fun getTitle(): CharSequence = ""

    /**
     * Do nothing since items don't have views.
     */
    override fun getTitleCondensed(): CharSequence = ""
}

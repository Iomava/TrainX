package com.flamecode.trainx.easter

import android.view.View
import android.widget.LinearLayout
import com.flamecode.trainx.fragments.IntroFragment

/**
 * Animation for IntroFragment, like a easter egg
 */
fun easteronLongClickLister(trainLink: LinearLayout): View.OnLongClickListener {

    return View.OnLongClickListener {

        trainLink.animate()
            .x(-IntroFragment.transitionX)
            .setDuration(1000L)
            .startDelay = 150

        return@OnLongClickListener false
    }
}

/**
 * Animation for IntroFragment, like a easter egg
 */
fun easteronClickLister(trainLink: LinearLayout): View.OnClickListener {

    return View.OnClickListener {

        trainLink.animate()
            .x(IntroFragment.transitionX)
            .setDuration(1000L)
            .startDelay = 150
    }
}
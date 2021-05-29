package com.flamecode.trainx.manager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.flamecode.trainx.R

const val containerViewID : Int = R.id.mainLayout

fun moveTo(newFragment: Fragment, fragmentManager: FragmentManager){

    fragmentManager.beginTransaction()
        .add(containerViewID, newFragment)
        .commit()
}

fun removeFragment(removeFragment: Fragment, fragmentManager: FragmentManager){

    fragmentManager.beginTransaction()
        .remove(removeFragment)
        .commit()
}
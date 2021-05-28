package com.flamecode.trainx.extensions

import android.graphics.Rect
import android.view.View

fun View.isOnScreen() : Boolean{

//    if (isShown) {
//        return false
//    }
    val actualPosition = Rect()
    getGlobalVisibleRect(actualPosition)
    val screen = screenWidth()?.let {
            screenWidth -> screenHeight()?.let {
            screenHeight -> Rect(0, 0, screenWidth, screenHeight)
            }
    }
    return screen?.let { actualPosition.intersect(it) } == true
}
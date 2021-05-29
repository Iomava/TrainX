package com.flamecode.trainx.extensions

import android.graphics.Rect
import android.view.View

fun View.isOnScreen() : Boolean{

    val actualPosition = Rect()
    getGlobalVisibleRect(actualPosition)
    val screen = screenWidth()?.let {
            screenWidth -> screenHeight()?.let {
            screenHeight -> Rect(0, 0, screenWidth, screenHeight)
            }
    }
    return screen?.let { actualPosition.intersect(it) } == true
}

fun View.bounceAnim() {

    fun reverseAnim(){

        animate().scaleX(1f).scaleY(1f).duration = 300
    }

    animate().scaleX(1.15f).scaleY(1.15f).setDuration(300)
        .withEndAction {
            reverseAnim()
        }
}
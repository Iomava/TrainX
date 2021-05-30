package com.flamecode.trainx.extensions

import android.graphics.Rect
import android.view.View

const val newScale = 1.1f
const val initScale = 1f

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

/**
 * Create an bounce animation
 */
fun View.bounceAnim() {

    fun reverseAnim(){

        animate().scaleX(initScale).scaleY(initScale).duration = 300
    }

    animate().scaleX(newScale).scaleY(newScale).setDuration(300)
        .withEndAction {
            reverseAnim()
        }
}
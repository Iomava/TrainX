package com.flamecode.trainx.extensions

import android.content.res.Resources

fun screenWidth() : Int? = Resources.getSystem().displayMetrics?.widthPixels
fun screenHeight() : Int? = Resources.getSystem().displayMetrics?.heightPixels

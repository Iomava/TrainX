package com.flamecode.trainx.extensions

import com.flamecode.trainx.Time
import java.lang.StringBuilder

fun Time.parseToString() : String{

    val buildString = StringBuilder()
    if (hour < 10) {
        buildString.append(0)
        buildString.append(hour)
    } else {
        buildString.append(hour)
    }

    buildString.append(':')

    if (minute < 10) {
        buildString.append(0)
        buildString.append(minute)
    } else {
        buildString.append(minute)
    }

    return buildString.toString()
}
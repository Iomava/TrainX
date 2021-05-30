package com.flamecode.trainx.crypto

import android.app.AlertDialog
import android.os.Handler
import com.flamecode.trainx.Ticket
import com.google.gson.Gson
import es.dmoral.toasty.Toasty
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.concurrent.thread

/**
 * Send a specific amount of FGX tokens to an email address
 *
 * @param email - receiver email address
 *
 *@author Iomava
 */

const val baseURL = "http://192.168.0.110:8080"

fun sendTokenToAnAddress(ticket: Ticket, email: String, dialog: AlertDialog.Builder) {

    try {
        thread {
            val gson = Gson()
            ticket.email = email
            val json = gson.toJson(ticket)

            val client = OkHttpClient()
            val request = Request.Builder()
                .url("$baseURL/train/purchase")
                .addHeader("Content-Type", "application/json")
                .post(json.toRequestBody())
                .build()

            val response = client.newCall(request).execute()
            val body = response.body
        }
    } catch (er: Error) {

        displayError(dialog)

    }
}

private fun displayError(dialog: AlertDialog.Builder) {

    val mainHandler = dialog.context?.mainLooper?.let { Handler(it) };

    val runnable = Runnable {

        Toasty.error(dialog.context, "Error occurred").show()

    }
    mainHandler?.post(runnable)
}

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

fun sendTokenToAnAddress(
    ticket: Ticket,
    email: String,
    dialog: AlertDialog,
    actualDialog: AlertDialog?
) {

    try {
        thread {
            val gson = Gson()
            ticket.email = email
            val json = gson.toJson(ticket)

            val mainHandler = dialog.context.mainLooper?.let { Handler(it) }
            val runnable = Runnable {
                Toasty.warning(dialog.context, "Your payment is processing...").show()
            }
            mainHandler?.post(runnable)

            val client = OkHttpClient()
            val request = Request.Builder()
                .url("$baseURL/train/purchase")
                .addHeader("Content-Type", "application/json")
                .post(json.toRequestBody())
                .build()

            val newCall = client.newCall(request)
            val response = newCall.execute()

            if (response.isSuccessful) {

                val runnableIsSuccessful = Runnable {
                    Toasty.success(dialog.context, "You will receive the ticket on email").show()
                }
                mainHandler?.post(runnableIsSuccessful)

                newCall.cancel()
                dialog.cancel()
                actualDialog?.cancel()
            } else {

                displayError(dialog, actualDialog)
                dialog.cancel()
                actualDialog?.cancel()
            }

        }
    } catch (er: Error) {

        displayError(dialog, actualDialog)
    }
}

private fun displayError(dialog: AlertDialog, actualDialog: AlertDialog?) {

    val mainHandler = dialog.context.mainLooper?.let { Handler(it) }

    val runnable = Runnable {

        Toasty.error(dialog.context, "Error occurred").show()

    }
    mainHandler?.post(runnable)
    dialog.cancel()
    actualDialog?.cancel()
}

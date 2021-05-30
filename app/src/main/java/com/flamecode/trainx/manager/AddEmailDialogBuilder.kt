package com.flamecode.trainx.manager

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.flamecode.trainx.R
import com.flamecode.trainx.crypto.sendTokenToAnAddress
import com.flamecode.trainx.extensions.bounceAnim
import com.flamecode.trainx.extensions.isEmailValid
import es.dmoral.toasty.Toasty

class AddEmailDialogBuilder(private val context : Context, private val amount : Double) {

    fun show(){

        val dialog = AlertDialog.Builder(context, R.style.full_screen_dialog)
        val context = dialog.context

        val view = LayoutInflater.from(context).inflate(R.layout.add_email_dialog, null)

        val emailAddress = view.findViewById<EditText>(R.id.emailAddress)
        val sendToken = view.findViewById<Button>(R.id.sendToken)

        setUpCloseListenerDialog(dialog, context)
        setClickListerForSendTokenButton(sendToken, emailAddress, context)

        createAlertDialog(dialog, view)
    }

    private fun setClickListerForSendTokenButton(
        sendToken: Button,
        emailAddress: EditText,
        context: Context
    ) {

        sendToken.setOnClickListener {

            it.bounceAnim()
            val email = emailAddress.text.toString().trim()
            if (email.isEmailValid()) {

                sendTokenToAnAddress(amount, email)
                Toasty.success(context, "You will receive the ticket on email").show()
            } else {

                Toasty.error(context, "Email is bad formatted").show()
            }
        }
    }

    private fun setUpCloseListenerDialog(
        dialog: AlertDialog.Builder,
        context: Context
    ) {

        dialog.setOnDismissListener {

            Toasty.error(context, "Transaction failed").show()
        }

        dialog.setOnCancelListener {

            Toasty.error(context, "Transaction failed").show()
        }
    }

    private fun createAlertDialog(dialog: AlertDialog.Builder, view: View): AlertDialog? {

        val alertDialog = dialog.create()
        alertDialog.show()
        alertDialog.setContentView(view)

        return alertDialog
    }
}
package com.flamecode.trainx.manager

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.flamecode.trainx.R
import com.flamecode.trainx.extensions.bounceAnim
import es.dmoral.toasty.Toasty

class ConfirmationDialogBuilder(private val context : Context) {

    fun show(){

        val dialog = AlertDialog.Builder(context, R.style.full_screen_dialog)
        val context = dialog.context

        val view = LayoutInflater.from(context).inflate(R.layout.confirmation_dialog, null)

        val buyButton = view.findViewById<Button>(R.id.buyButton)
        val amountOfToken = view.findViewById<TextView>(R.id.token)

        val amount = amountOfToken.text.toString().trim().toDouble()

        setUpCloseListenerDialog(dialog, context)

        val addEmailDialogBuilder = AddEmailDialogBuilder(context, amount)
        buyButton.setOnClickListener {

            it.bounceAnim()
            addEmailDialogBuilder.show()
        }

        createAlertDialog(dialog, view)
    }

    private fun setUpCloseListenerDialog(
        dialog: AlertDialog.Builder,
        context: Context
    ) {

        dialog.setOnDismissListener {

            Toasty.warning(context, "Transaction was stopped").show()
        }
        dialog.setOnCancelListener {

            Toasty.warning(context, "Transaction was stopped").show()
        }
    }

    private fun createAlertDialog(dialog: AlertDialog.Builder, view: View): AlertDialog? {

        val alertDialog = dialog.create()
        alertDialog.show()
        alertDialog.setContentView(view)

        return alertDialog
    }
}
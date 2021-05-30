package com.flamecode.trainx.manager

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.flamecode.trainx.R
import com.flamecode.trainx.Ticket
import com.flamecode.trainx.extensions.bounceAnim
import com.flamecode.trainx.extensions.round
import es.dmoral.toasty.Toasty

class ConfirmationDialogBuilder(private val ticket : Ticket, private val context : Context) {

    fun show() {

        val dialog = AlertDialog.Builder(context, R.style.full_screen_dialog)
        val context = dialog.context

        val view = LayoutInflater.from(context).inflate(R.layout.confirmation_dialog, null)

        val distance = view.findViewById<TextView>(R.id.distance)
        distance.text = "${ticket.distance.round(2)} km"

        val price = view.findViewById<TextView>(R.id.price)
        price.text = "${ticket.price.round(2)} $"

        val buyButton = view.findViewById<Button>(R.id.buyButton)

        val amountOfToken = view.findViewById<TextView>(R.id.token)
        amountOfToken.text = ticket.rewardToken.round(4).toString()

        setUpCloseListenerDialog(dialog, context)

        val addEmailDialogBuilder = AddEmailDialogBuilder(context, ticket, dialog)
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

         //   Toasty.warning(context, "Transaction was stopped").show()
        }
    }

    private fun createAlertDialog(dialog: AlertDialog.Builder, view: View): AlertDialog? {

        val alertDialog = dialog.create()
        alertDialog.show()
        alertDialog.setContentView(view)

        return alertDialog
    }
}
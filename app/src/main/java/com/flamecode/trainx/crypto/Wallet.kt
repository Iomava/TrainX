package com.flamecode.trainx.crypto

import android.widget.TextView
import com.flamecode.trainx.R
import java.lang.StringBuilder

/**
 * Get amount of FGX from a email address or wallet address
 *
 */

fun getAmountOfFGX(amount: TextView?, email : String, wallet : String){

    if (amount != null) {

        val context = amount.context
        val baseString = context.resources.getString(R.string.amount_0_00)
        val stringBuilder = StringBuilder()
        stringBuilder.append(baseString)
        stringBuilder.append(" ")

        // make the call to get the amount of FGX
        if (email == ""){

            amountBasedOnWalletAddress()
        }

        if (wallet == "") {

            amountBasedOnEmail()
        }

        amount.text = stringBuilder.toString()
    }
}

private fun amountBasedOnEmail(){

}

private fun amountBasedOnWalletAddress(){

}
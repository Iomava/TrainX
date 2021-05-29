package com.flamecode.trainx

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val fromText = itemView.findViewById<TextView>(R.id.fromText)
    val platform = itemView.findViewById<TextView>(R.id.platform)
    val toText = itemView.findViewById<TextView>(R.id.toText)
    val leaveAt = itemView.findViewById<TextView>(R.id.leaveAt)
    val arriveAt = itemView.findViewById<TextView>(R.id.arriveAt)
    val price = itemView.findViewById<TextView>(R.id.price)
    val buyButton = itemView.findViewById<Button>(R.id.buyButton)
    val main = itemView.findViewById<LinearLayout>(R.id.main)

}
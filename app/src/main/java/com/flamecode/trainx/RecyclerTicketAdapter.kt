package com.flamecode.trainx

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Space
import androidx.recyclerview.widget.RecyclerView
import com.flamecode.trainx.extensions.bounceAnim
import com.flamecode.trainx.extensions.parseToString
import com.flamecode.trainx.extensions.round
import com.flamecode.trainx.manager.ConfirmationDialogBuilder
import kotlin.math.round

class RecyclerTicketAdapter(private val tickets : List<Ticket>) : RecyclerView.Adapter<TicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val ticketView = inflater.inflate(R.layout.ticket, parent, false)
        return TicketViewHolder(ticketView)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {

        val ticket = tickets[position]
        val context = holder.arriveAt.context
        holder.arriveAt.text = ticket.timeArrive.parseToString()
        holder.leaveAt.text = ticket.timeLeave.parseToString()
        holder.fromText.text = ticket.startDestination
        holder.toText.text = ticket.endDestination
        holder.platform.text = ticket.platform
        holder.price.text = ticket.price.round(2).toString() + " $"

        val confirmationDialogBuilder = ConfirmationDialogBuilder(ticket, context)

        holder.buyButton.setOnClickListener {

            it.bounceAnim()
            confirmationDialogBuilder.show()
        }

        addSpaceBelowLastItem(position, context, holder)
    }

    private fun addSpaceBelowLastItem(
        position: Int,
        context: Context?,
        holder: TicketViewHolder
    ) {

        if (position == itemCount - 1) {

            for (i in 0..5) {

                val button = Button(context)
                button.text = context?.getString(R.string.test)
                button.visibility = View.INVISIBLE
                holder.main.addView(button)
            }
        }
    }

    override fun getItemCount(): Int = tickets.size

}
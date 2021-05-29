package com.flamecode.trainx.mock

import com.flamecode.trainx.Ticket
import com.flamecode.trainx.Time

fun mockListTickets() : List<Ticket> = listOf(
    Ticket(
        startDestination = "Gara Mare Onesti",
        endDestination = "Gara Dofteana",
        platform = "2",
        distance = 27.65,
        timeLeave = Time(29, 5, 4, 32),
        timeArrive = Time(29, 5, 5, 8),
        price = 0.5
    ),
    Ticket(
        startDestination = "Gara Mare Onesti",
        endDestination = "Gara Dofteana",
        platform = "2",
        distance = 27.65,
        timeLeave = Time(29, 5, 4, 32),
        timeArrive = Time(29, 5, 5, 8),
        price = 0.5
    ),
    Ticket(
        startDestination = "Gara Mare Onesti",
        endDestination = "Gara Dofteana",
        platform = "2",
        distance = 27.65,
        timeLeave = Time(29, 5, 4, 32),
        timeArrive = Time(29, 5, 5, 8),
        price = 0.5
    )
)
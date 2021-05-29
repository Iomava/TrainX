package com.flamecode.trainx

data class Ticket(
    val startDestination : String,
    val endDestination : String,
    val platform : String,
    val timeLeave : Time,
    val timeArrive : Time,
    val distance : Double,
    val price : Double
)

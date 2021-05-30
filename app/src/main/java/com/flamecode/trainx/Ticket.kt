package com.flamecode.trainx

data class Ticket(
    val startDestination : String,
    val endDestination : String,
    val platform : String,
    var email : String? = null,
    val timeLeave : Time,
    val timeArrive : Time,
    val distance : Double,
    val price : Double,
    val rewardToken : Double
) {
    constructor() : this(
        "",
        "",
        "0",
        "",
        Time(0, 0, 0, 0),
        Time(0, 0, 0, 0),
        0.0,
        0.0,
        0.0
    )
}

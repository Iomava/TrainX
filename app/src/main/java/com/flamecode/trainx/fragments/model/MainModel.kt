package com.flamecode.trainx.fragments.model

import android.view.View
import com.flamecode.trainx.RecyclerTicketAdapter

interface MainModel {

    fun search(startDestination : String, endDestination : String, searchItem : View)
    fun showLoading(icon : View)
    fun hideLoading(icon : View)
    fun loadData(startDest : String, stopDest : String, adapter : RecyclerTicketAdapter)
}
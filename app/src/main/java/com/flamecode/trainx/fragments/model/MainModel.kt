package com.flamecode.trainx.fragments.model

import android.view.View

interface MainModel {

    fun search(startDestination : String, endDestination : String, searchItem : View)
    fun showLoading(icon : View)
    fun hideLoading(icon : View)
    fun loadData()
}
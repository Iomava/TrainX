package com.flamecode.trainx.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.flamecode.trainx.R
import com.flamecode.trainx.RecyclerTicketAdapter
import com.flamecode.trainx.Ticket
import com.flamecode.trainx.crypto.baseURL
import com.flamecode.trainx.databinding.FragmentMainBinding
import com.flamecode.trainx.extensions.bounceAnim
import com.flamecode.trainx.fragments.model.MainModel
import com.flamecode.trainx.manager.moveTo
import com.google.gson.Gson
import es.dmoral.toasty.Toasty
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


class MainFragment : Fragment(), MainModel {

    private var binding: FragmentMainBinding? = null
    lateinit var recyclerTicketAdapter: RecyclerTicketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        setUpTicketsRecycler()

        val searchButton = binding?.searchButton
        val startDestinationEditText = binding?.searchStartDestinationTickets
        val endDestinationEditText = binding?.searchEndDestinationTickets

        searchButton?.setOnClickListener {

            val startDestination = startDestinationEditText?.text?.trim().toString()
            val endDestination = endDestinationEditText?.text?.trim().toString()

            validateStartEndDestination(startDestination, endDestination, searchButton)
        }

        val droneXApp = binding?.droneXApp
        val marketplace = binding?.marketPlace

        setDroneXClick(droneXApp)
        setMarketPlaceClick(marketplace)

        return binding?.root
    }

    private fun setUpTicketsRecycler() {

        val recyclerView = binding?.recyclerView

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("$baseURL/train/tickets?startDest=Bucuresti&stopDest=Gara Onesti")
            .get()
            .build()

        try {

            thread {

                val response = client.newCall(request).execute()
                val body = response.body?.string()
                val gson = Gson()
                val list = gson.fromJson(body, Array<Ticket>::class.java).toList()

                val mainHandler = context?.mainLooper?.let { Handler(it) };

               val runnable =  Runnable {

                   recyclerTicketAdapter = RecyclerTicketAdapter(list.toMutableList())
                   recyclerView?.adapter = recyclerTicketAdapter
                   recyclerView?.layoutManager = LinearLayoutManager(context)
                }
                mainHandler?.post(runnable)
            }
        } catch (er: Error) {
        }

    }

    private fun setDroneXClick(droneXApp: ImageView?) {
        droneXApp?.setOnClickListener {

            it.bounceAnim()
        }
    }

    private fun setMarketPlaceClick(marketplace: ImageView?) {
        marketplace?.setOnClickListener {

            it.bounceAnim()
            moveTo(MarketPlaceFragment(), parentFragmentManager)
        }
    }

    private fun validateStartEndDestination(
        startDestination: String,
        endDestination: String,
        searchButton: ImageView?
    ) {
        var correctData = true
        if (startDestination.isEmpty() || startDestination.isBlank()) {

            context?.let { it1 ->
                Toasty.error(it1, getString(R.string.error_start_destination)).show()
            }
            correctData = false
        }
        if (endDestination.isEmpty() || endDestination.isBlank()) {

            context?.let { it1 ->
                Toasty.error(it1, getString(R.string.error_end_destination)).show()
            }
            correctData = false
        }

        if (correctData) {
            if (searchButton != null) {

                search(startDestination, endDestination, searchButton)
            }
        }
    }

    override fun search(startDestination: String, endDestination: String, searchItem: View) {
        
        loadData(startDestination, endDestination, recyclerTicketAdapter)

    }

    override fun showLoading(icon: View) {
        TODO("Not yet implemented")
    }

    override fun hideLoading(icon: View) {
        TODO("Not yet implemented")
    }


    override fun loadData(startDest : String, stopDest : String, adapter : RecyclerTicketAdapter) {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("$baseURL/train/tickets?startDest=$startDest&stopDest=$stopDest")
            .get()
            .build()

        try {

            thread {

                val response = client.newCall(request).execute()
                val body = response.body?.string()
                val gson = Gson()
                val list = gson.fromJson(body, Array<Ticket>::class.java).toList()

                val mainHandler = context?.mainLooper?.let { Handler(it) };

                val runnable =  Runnable {

                    adapter.tickets = list.toMutableList()
                    adapter.notifyDataSetChanged()
                }
                mainHandler?.post(runnable)
            }
        } catch (er: Error) {
        }
    }
}
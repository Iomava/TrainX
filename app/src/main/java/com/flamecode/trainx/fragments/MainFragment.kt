package com.flamecode.trainx.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.flamecode.trainx.R
import com.flamecode.trainx.RecyclerTicketAdapter
import com.flamecode.trainx.databinding.FragmentMainBinding
import com.flamecode.trainx.extensions.bounceAnim
import com.flamecode.trainx.fragments.model.MainModel
import com.flamecode.trainx.manager.moveTo
import com.flamecode.trainx.mock.mockListTickets
import es.dmoral.toasty.Toasty

class MainFragment : Fragment(), MainModel {

    private var binding : FragmentMainBinding? = null

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
        val mockList = mockListTickets()
        recyclerView?.adapter = RecyclerTicketAdapter(mockList)
        recyclerView?.layoutManager = LinearLayoutManager(context)
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

            context?.let { it1 -> Toasty.error(it1, getString(R.string.error_start_destination)).show() }
            correctData = false
        }
        if (endDestination.isEmpty() || endDestination.isBlank()) {

            context?.let { it1 -> Toasty.error(it1, getString(R.string.error_end_destination)).show() }
            correctData = false
        }

        if (correctData) {
            if (searchButton != null) {

                search(startDestination, endDestination, searchButton)
            }
        }
    }

    override fun search(startDestination: String, endDestination: String, searchItem: View) {

        searchItem.setOnClickListener {

            it.bounceAnim()
        }
    }

    override fun showLoading(icon: View) {
        TODO("Not yet implemented")
    }

    override fun hideLoading(icon: View) {
        TODO("Not yet implemented")
    }


    override fun loadData() {
        TODO("Not yet implemented")
    }
}
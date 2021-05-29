package com.flamecode.trainx.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.flamecode.trainx.R
import com.flamecode.trainx.RecyclerTicketAdapter
import com.flamecode.trainx.databinding.FragmentMainBinding
import com.flamecode.trainx.extensions.bounceAnim
import com.flamecode.trainx.fragments.model.MainModel
import com.flamecode.trainx.mock.mockListTickets

class MainFragment : Fragment(), MainModel {

    var binding : FragmentMainBinding? = null

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

        val recyclerView = binding?.recyclerView
        val mockList = mockListTickets()
        recyclerView?.adapter = RecyclerTicketAdapter(mockList)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        val searchButton = binding?.searchButton
        val startDestinationEditText = binding?.searchStartDestinationTickets
        val endDestinationEditText = binding?.searchEndDestinationTickets
        searchButton?.setOnClickListener {

            val startDestination = startDestinationEditText?.text?.trim().toString()
            val endDestination = endDestinationEditText?.text?.trim().toString()
            search(startDestination, endDestination, searchButton)
        }

        return binding?.root
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
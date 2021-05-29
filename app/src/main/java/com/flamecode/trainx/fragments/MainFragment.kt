package com.flamecode.trainx.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import com.flamecode.trainx.R
import com.flamecode.trainx.databinding.FragmentIntroBinding
import com.flamecode.trainx.databinding.FragmentMainBinding
import com.flamecode.trainx.fragments.model.MainModel


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
        recyclerView?.adapter = // TODO

        return binding?.root
    }

    override fun search() {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun loadData() {
        TODO("Not yet implemented")
    }
}
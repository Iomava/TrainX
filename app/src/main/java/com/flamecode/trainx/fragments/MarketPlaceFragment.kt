package com.flamecode.trainx.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import com.flamecode.trainx.R
import com.flamecode.trainx.crypto.getAmountOfFGX
import com.flamecode.trainx.databinding.FragmentMarketPlaceBinding
import com.flamecode.trainx.extensions.bounceAnim
import com.flamecode.trainx.manager.removeFragment

class MarketPlaceFragment : Fragment() {

    private var binding : FragmentMarketPlaceBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMarketPlaceBinding.inflate(inflater, container, false)

        val checkButton = binding?.checkButton
        val amount = binding?.userAmount
        checkButton?.setOnClickListener {

            it.bounceAnim()
            getAmountOfFGX(amount, "", "")
        }

        exchangeMethodsOffer1()
        exchangeMethodsOffer2()

        setUpBackButton()

        return binding?.root
    }

    private fun exchangeMethodsOffer2() {

        val exchange2 = binding?.exchange2
        exchange2?.setOnClickListener {
            it.bounceAnim()
            buyOffer2()
        }
    }

    private fun exchangeMethodsOffer1() {

        val exchange = binding?.exchange
        exchange?.setOnClickListener {

            it.bounceAnim()
            buyOffer1()
        }
    }

    private fun buyOffer2() {

    }

    private fun buyOffer1() {

    }

    private fun setUpBackButton() {
        val back = binding?.back

        back?.setOnClickListener {

            it.bounceAnim()
            removeFragment(this, parentFragmentManager)
        }
    }
}
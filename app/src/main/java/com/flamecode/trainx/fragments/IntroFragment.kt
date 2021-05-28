package com.flamecode.trainx.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.transition.TransitionInflater
import com.flamecode.trainx.R
import com.flamecode.trainx.databinding.FragmentIntroBinding
import com.flamecode.trainx.easter.easteronClickLister
import com.flamecode.trainx.easter.easteronLongClickLister

class IntroFragment : Fragment() {

    companion object{

        const val transitionX = 1500F
        const val delay = 250L
    }

    var binding : FragmentIntroBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentIntroBinding.inflate(inflater, container, false)

        val trainLink = binding?.trainLink
        trainLink?.animate()?.
        x(transitionX)?.
        setStartDelay(delay)?.
        duration = 2000

        trainLink?.children?.forEach {

            it.setOnClickListener(easteronClickLister(trainLink))
            it.setOnLongClickListener(easteronLongClickLister(trainLink))
        }

        return binding?.root
    }



}
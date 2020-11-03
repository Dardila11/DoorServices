package edu.unicauca.doorservices.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.unicauca.doorservices.R
import kotlinx.android.synthetic.main.fragment_explore.*

class ExploreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExploreFragment()

    }
}
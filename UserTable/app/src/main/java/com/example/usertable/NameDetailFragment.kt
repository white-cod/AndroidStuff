package com.example.usertable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NameDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_name_detail, container, false)

        val name = arguments?.getString(ARG_NAME) ?: return view
        val nameModel = (activity as MainActivity).findNameModel(name)

        nameModel?.let {
            view.findViewById<TextView>(R.id.nameText).text = it.name
            view.findViewById<TextView>(R.id.dateText).text = it.date
            view.findViewById<TextView>(R.id.valueText).text = it.value
        }

        return view
    }

    companion object {
        private const val ARG_NAME = "name"

        fun newInstance(name: String) = NameDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_NAME, name)
            }
        }
    }
}
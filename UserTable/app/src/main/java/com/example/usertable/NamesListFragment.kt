package com.example.usertable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.fragment.app.Fragment

class NamesListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_names_list, container, false)

        val gridView = view.findViewById<GridView>(R.id.gridView)
        val names = arrayOf("Emma", "Liam", "Olivia", "Noah", "Ava")

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            names
        )

        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val selectedName = names[position]
            val detailFragment = NameDetailFragment.newInstance(selectedName)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
package com.example.cityapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cityapp.databinding.FragmentMasterBinding

class ShowCitiesFragment : Fragment() {
    private lateinit var binding: FragmentMasterBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMasterBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CityRepository.init()
        var adapter = ShowCitiesAdapter( { city -> goToActivity1(city) },  { pos -> changeBackgroundColor(pos) })
        adapter.submitList(CityRepository.city)
        binding.recyclerview.adapter = adapter

        binding.btnGoToFragmentTwo.setOnClickListener {
            findNavController().navigate(R.id.action_showCitiesFragment_to_detailFragment)
        }

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                CityRepository.city.removeAt(pos)
                adapter.notifyItemRemoved(pos)
            }
        }



        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)


//        val countObserver = Observer<List<City>>{ list ->
////            binding.tvCount.text = count.toString()
//
//        }
//        viewModel.listForFragmentTwo.observe(viewLifecycleOwner, countObserver)
    }

    private fun changeBackgroundColor(pos: Int) {

        if (!CityRepository.city[pos].isSelected) {
            CityRepository.city[pos].isSelected = true
            CityRepository.NextFragmentCity.add( CityRepository.city[pos])

        } else {
            CityRepository.city[pos].isSelected = false
            CityRepository.NextFragmentCity.remove( CityRepository.city[pos])

        }
//        CityRepository.city[CityRepository.city.indexOf(city)].isSelected = true
//        CityRepository.NextFragmentCity.add(CityRepository.city[CityRepository.city.indexOf(city)])
    }


    private fun goToActivity1(city: City) {
        findNavController().navigate(R.id.action_showCitiesFragment_to_detailFragment)
    }
}
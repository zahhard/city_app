package com.example.cityapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

typealias DetailClickHandler = (City) -> Unit
typealias ItemClickHandler = (Int) -> Unit

class ShowCitiesAdapter (
    private var onShowQuestionClick : DetailClickHandler ,
    private var onItemClick : ItemClickHandler ,
) : ListAdapter<City, ShowCitiesAdapter.ViewHolder>(QuestionDiffCallback)
{

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val tvCityName = view.findViewById<TextView>(R.id.tv_city_name)
        val btnShow = view.findViewById<Button>(R.id.button)

        fun bind(City: City, onShowCityClick: DetailClickHandler? ){
            tvCityName.text = City.name

            btnShow.setOnClickListener {
                onShowCityClick?.invoke(City)
            }
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ShowCitiesAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.city_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowCitiesAdapter.ViewHolder, position: Int) {
        if (getItem(position).isSelected){
            holder.itemView.setBackgroundColor(Color.parseColor("#E6A7CE"))
        }
        holder.bind(getItem(position) , onShowQuestionClick)

        holder.itemView.setOnClickListener{
            var pos = CityRepository.city.indexOf(getItem(position))
            if (!CityRepository.city[pos].isSelected) {
                holder.itemView.setBackgroundColor(Color.parseColor("#E6A7CE"))
            } else {
                CityRepository.city[pos].isSelected = false
                holder.itemView.setBackgroundColor(Color.parseColor("#A7FFEB"))
            }
            onItemClick(position)
        }
    }

    object QuestionDiffCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }
    }
}
package com.example.houselist_with_di.pagination

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View.inflate
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.houselist_with_di.HousesRecyclerAdapter
import com.example.houselist_with_di.R
import com.example.houselist_with_di.UI.SingleHouse
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.response.DataX
import com.example.houselist_with_di.network.response.Pagination


class PagingAdapter (diffCallback: DiffUtil.ItemCallback<House>): PagingDataAdapter<House, HousesRecyclerAdapter.HouseViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: HousesRecyclerAdapter.HouseViewHolder, position: Int) {
        val datax: House? = getItem(position)
        holder.bind(datax)
        Log.v("nmg", datax.toString())
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HousesRecyclerAdapter.HouseViewHolder {
        val items: HousesRecyclerAdapter.HouseViewHolder = HousesRecyclerAdapter.HouseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_house_post, parent, false),
        )
        return items
    }


    object HouseComparator : DiffUtil.ItemCallback<House>() {
        override fun areItemsTheSame(oldItem: House, newItem: House): Boolean {
            return oldItem.id == newItem.id
            Log.v("jbg", "oldItem.toString()")
        }

        override fun areContentsTheSame(oldItem: House, newItem: House): Boolean {
            return oldItem == newItem
        }

    }
}

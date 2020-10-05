package com.example.houselist_with_di.pagination

import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.houselist_with_di.HousesRecyclerAdapter
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.response.Pagination


class PagingAdapter (diffCallback: DiffUtil.ItemCallback<House>): PagingDataAdapter<House, HousesRecyclerAdapter.HouseViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: HousesRecyclerAdapter.HouseViewHolder, position: Int) {
        val house: House? = getItem(position)
        holder.bind(house)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HousesRecyclerAdapter.HouseViewHolder {
        return HousesRecyclerAdapter.HouseViewHolder(parent)
    }
}

object HouseComparator : DiffUtil.ItemCallback<House>() {
    override fun areItemsTheSame(oldItem: House, newItem: House): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: House, newItem: House): Boolean {
        return oldItem == newItem
    }

}

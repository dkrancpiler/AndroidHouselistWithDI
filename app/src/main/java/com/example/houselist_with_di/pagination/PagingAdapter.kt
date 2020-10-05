package com.example.houselist_with_di.pagination

import android.util.Log
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.houselist_with_di.HousesRecyclerAdapter
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.response.DataX
import com.example.houselist_with_di.network.response.Pagination


class PagingAdapter (diffCallback: DiffUtil.ItemCallback<DataX>): PagingDataAdapter<DataX, HousesRecyclerAdapter.HouseViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: HousesRecyclerAdapter.HouseViewHolder, position: Int) {
        val datax: DataX? = getItem(position)
        holder.bind(datax)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HousesRecyclerAdapter.HouseViewHolder {
        return HousesRecyclerAdapter.HouseViewHolder(parent)
    }


    object HouseComparator : DiffUtil.ItemCallback<DataX>() {
        override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean {
            return oldItem.id == newItem.id
            Log.v("jbg", "oldItem.toString()")
        }

        override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean {
            return oldItem == newItem
        }

    }
}

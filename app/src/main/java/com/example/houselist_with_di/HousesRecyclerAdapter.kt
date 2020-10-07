package com.example.houselist_with_di

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.houselist_with_di.UI.MainViewModel
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.HousesNetworkCall
import com.example.houselist_with_di.network.NetworkMapper
import com.example.houselist_with_di.network.response.Cover
import com.example.houselist_with_di.network.response.DataX
import com.example.houselist_with_di.network.response.Pagination
import com.example.houselist_with_di.utility.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.single_house_post.view.*
import javax.inject.Inject

class HousesRecyclerAdapter()
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<House>()
    private var houses: MutableList<House> = mutableListOf<House>()
    private var pages = mutableListOf<Pagination>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.v("nmg", "datax.toString()")
        return HouseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_house_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HouseViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList (houseList: List<House>){
        //items.clear()
        val startPosition = items.size
        items.addAll(houseList)
        notifyItemRangeInserted(startPosition, startPosition + houseList.size)
    }
    fun submitPage (pagelist: Pagination) {
        val startPosition = pages.size
        pages.add(pagelist)
        notifyItemRangeInserted(startPosition, startPosition + pages.size)
    }

    class HouseViewHolder (
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        val house_title = itemView.title_short_text
        val house_image = itemView.house_image
        val house_description = itemView.description_short_text
        val house_address = itemView.address_text
        val house_price = itemView.price_text
        fun bind(house: House?){
            val url = "https://homehapp-api.jsteam.gaussx.com/api/media/" + house?.image + "/small"
            if (house?.title_short != null) house_title.setText(house.title_short)
            else house_title.setText(house?.title)
            if(house?.description_short != null) house_description.setText(house.description_short)
            else house_description.setText(house?.description)
            house_address.setText(house?.address)
            if(house?.price != null) house_price.setText("Price: " + house.price.toString() + "€")
            else house_price.apply {
                setBackgroundColor(Color.parseColor("#4FE91E63"))
                setText("Price for this object is unavailable")
            }

            Glide.with(itemView.context)
                .load(url)
                .placeholder(R.drawable.image_unavailable)
                .error(R.drawable.image_unavailable)
                .into(house_image)

        }

    }
}
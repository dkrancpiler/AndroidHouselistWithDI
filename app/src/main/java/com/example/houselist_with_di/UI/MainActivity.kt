package com.example.houselist_with_di.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.houselist_with_di.HousesRecyclerAdapter
import com.example.houselist_with_di.R
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.response.Cover
import com.example.houselist_with_di.network.response.Pagination
import com.example.houselist_with_di.pagination.PagingAdapter
import com.example.houselist_with_di.utility.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()

    private lateinit var  houseAdapter: HousesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pagingAdapter = PagingAdapter(PagingAdapter.HouseComparator)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = pagingAdapter
        initRecyclerView()
//        addDataSet()
//        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetHousesEvents)

        fun giveHouses() {
            lifecycleScope.launch {
                viewModel.flow.collectLatest { pagingData -> pagingAdapter.submitData(pagingData) }
            }
        }
        giveHouses()
    }


    private fun addDataSet(){
        viewModel.successResponse.observe(this, Observer {
            it?.let {
                houseAdapter.submitList(it)
            }
        })
        viewModel.success2.observe(this, Observer {
            it?.let { houseAdapter.submitPage(it) }
        })
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            houseAdapter = HousesRecyclerAdapter()
            adapter = houseAdapter
        }
    }

//    private fun subscribeObservers(){
//        val picture = "https://homehapp-api.jsteam.gaussx.com/api/media/59983/small"
//        viewModel.dataState.observe(this, Observer{ dataState ->
//            when(dataState){
//                is DataState.Success<List<House>> -> {
//                    displayProgressBar(false)
//                    appendHousetitles(dataState.data)
////                    Glide.with(this)
////                        .load(picture)
////                        .into(image)
//                }
//                is DataState.Error -> {
//                    displayProgressBar(false)
//                    displayError(dataState.exception.message)
//                }
//                is DataState.Loading -> {
//                    displayProgressBar(true)
//                }
//            }
//        })
//    }
//
//    private fun displayError(message: String?) {
//        if(message != null) {
//            textView.text = message
//        }
//        else {
//            textView.text = "Unknown error"
//        }
//    }
//
//    private fun displayProgressBar(isDisplayed: Boolean) {
//        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
//    }
//    private fun appendHousetitles(houses: List<House>) {
//        val sb = StringBuilder()
//        for(house in houses){
//            sb.append(house.image + "\n")
//        }
//        textView.text = sb.toString()
//    }

}
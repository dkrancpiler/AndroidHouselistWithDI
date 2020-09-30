package com.example.houselist_with_di.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.houselist_with_di.R
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.response.Cover
import com.example.houselist_with_di.utility.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.StringBuilder
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetHousesEvents)
    }

    private fun subscribeObservers(){
        val picture = "https://homehapp-api.jsteam.gaussx.com/api/media/59983/small"
        viewModel.dataState.observe(this, Observer{ dataState ->
            when(dataState){
                is DataState.Success<List<House>> -> {
                    displayProgressBar(false)
                    appendHousetitles(dataState.data)
                    Glide.with(this)
                        .load(picture)
                        .into(image)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if(message != null) {
            textView.text = message
        }
        else {
            textView.text = "Unknown error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }
    private fun appendHousetitles(houses: List<House>) {
        val sb = StringBuilder()
        for(house in houses){
            sb.append(house.title + "\n")
        }
        textView.text = sb.toString()
    }

}
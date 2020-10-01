package com.example.houselist_with_di.UI

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.lifecycle.Transformations.map
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.repository.MainRepo
import com.example.houselist_with_di.utility.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepo: MainRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<House>>> = MutableLiveData()
    val successResponse = map(_dataState) {
        if(it is DataState.Success) {
            it.data
        } else null
    }

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetHousesEvents -> {
                    mainRepo.getHouse()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetHousesEvents: MainStateEvent()
}
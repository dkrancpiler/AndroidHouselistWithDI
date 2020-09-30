package com.example.houselist_with_di.repository

import com.example.houselist_with_di.db.HouseDao
import com.example.houselist_with_di.db.dbMapper
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.HousesNetworkCall
import com.example.houselist_with_di.network.NetworkMapper
import com.example.houselist_with_di.utility.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepo constructor(
    private val houseDao: HouseDao,
    private val housesNetworkCall: HousesNetworkCall,
    private val networkMapper: NetworkMapper,
    private val dbMapper: dbMapper
){
    suspend fun  getHouse(): Flow<DataState<List<House>>> = flow {
        emit(DataState.Loading)
        try {
            val networkHouses = housesNetworkCall.getHouses()
            val houses = networkMapper.mapFromEntityList(networkHouses)
            for(house in houses) {
                houseDao.insert(dbMapper.mapToEntity(house))
            }
            val storedHouses = houseDao.get()
            emit(DataState.Success(dbMapper.mapFromEntityList(storedHouses)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
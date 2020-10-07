package com.example.houselist_with_di.repository

import android.util.Log
import androidx.paging.PagingSource
import com.example.houselist_with_di.db.HouseDao
import com.example.houselist_with_di.db.dbMapper
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.HousesNetworkCall
import com.example.houselist_with_di.network.NetworkMapper
import com.example.houselist_with_di.network.response.DataX
import com.example.houselist_with_di.network.response.Pagination
import com.example.houselist_with_di.pagination.PaginationMapper
import com.example.houselist_with_di.utility.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class MainRepo constructor(
    private val houseDao: HouseDao,
    private val housesNetworkCall: HousesNetworkCall,
    private val networkMapper: NetworkMapper,
    private val dbMapper: dbMapper,
){
    suspend fun  getHouse(): Flow<DataState<List<House>, Pagination>> = flow {
        emit(DataState.Loading)
        try {
            val networkHouses = housesNetworkCall.getHouses()
            val houses = networkMapper.mapFromEntityList(networkHouses.data.data)
            val pages = networkHouses.data.pagination
            for (house in houses) {
                houseDao.insert(dbMapper.mapToEntity(house))
            }
            val storedHouses = houseDao.get()
            emit(DataState.Success(dbMapper.mapFromEntityList(storedHouses), pages))
            Log.v("jbg", "mainStateEvent.toString()")
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
    class PagingSourceClass(
        private val housesNetworkCall: HousesNetworkCall,
        private val networkMapper: NetworkMapper
    ): PagingSource<Int, House>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, House> {
            try {
                val nextPageNumber = params.key ?: 1
                val response = housesNetworkCall.getHouses()
                val pages = response.data.pagination
                val house= response.data.data
                val result = networkMapper.mapFromEntityList(house)
                val pagelist: List<Pagination> = listOf(pages)
                return LoadResult.Page(
                    data = result,
                    prevKey = pages.page!!.toInt() - 1,
                    nextKey = pages.page!!.toInt() + 1
                )
            }catch (e: IOException) {
                return LoadResult.Error(e)
            }
        }
    }
}
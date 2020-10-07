package com.example.houselist_with_di.repository

import android.util.Log
import androidx.paging.PagingSource
import com.example.houselist_with_di.db.HouseDao
import com.example.houselist_with_di.db.dbMapper
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.HousesNetworkCall
import com.example.houselist_with_di.network.NetworkMapper
import com.example.houselist_with_di.network.response.Pagination
import com.example.houselist_with_di.utility.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.Query
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
            val networkHouses = housesNetworkCall.getHouses("1")
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
        var i = 1
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, House> {
            try {
                var page: String = "0"
                val nextPageNumber = params.key ?: 1
                val response = housesNetworkCall.getHouses(i.toString())
                val pages = response.data.pagination
                val house= response.data.data
                val result = networkMapper.mapFromEntityList(house)
                page = pages.page.toString()
                val pagelist: List<Pagination> = listOf(pages)
                i++
                Log.v("test", i.toString())
                return LoadResult.Page(
                    data = result,
                    prevKey = null,
                    nextKey = pages.page!!.toInt() + 1
                )
            }catch (e: IOException) {
                return LoadResult.Error(e)
                i++
            }
        }
    }
}
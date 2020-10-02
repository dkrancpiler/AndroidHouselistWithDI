package com.example.houselist_with_di.pagination

import androidx.activity.viewModels
import androidx.paging.PagingSource
import com.example.houselist_with_di.UI.MainActivity
import com.example.houselist_with_di.UI.MainViewModel
import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.HousesNetworkCall
import com.example.houselist_with_di.network.response.Pagination
import com.example.houselist_with_di.repository.MainRepo
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

//@AndroidEntryPoint
//class PagingSourceClass(
//    val backend: HousesNetworkCall,
//    val paginationMapper: PaginationMapper
//): PagingSource<Int, House>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, House> {
//        try {
//            val nextPageNumber = params.key ?: 1
//            val response = backend.getHouses()
//            val pagelist: List<Pagination> = response.a
//            val pages = paginationMapper.mapFromEntityList(response.data.pagination)
//            return LoadResult.Page(
//
//            )
//        }catch (e: Exception) {}
//    }
//}
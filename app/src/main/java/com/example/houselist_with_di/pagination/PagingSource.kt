//package com.example.houselist_with_di.pagination
//
//import androidx.activity.viewModels
//import androidx.paging.PagingSource
//import com.example.houselist_with_di.UI.MainActivity
//import com.example.houselist_with_di.UI.MainViewModel
//import com.example.houselist_with_di.models.House
//import com.example.houselist_with_di.network.HousesNetworkCall
//import com.example.houselist_with_di.network.response.Pagination
//import com.example.houselist_with_di.repository.MainRepo
//import java.io.IOException
//import java.lang.Exception
//
//class PagingSourceClass(
//    val pagination: Pagination,
//): PagingSource<Int, Pagination>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pagination> {
//        try {
//            val nextPageNumber = params.key ?: 1
//            val pages: Pagination = pagination
//            val pagelist: List<Pagination> = listOf(pages)
//
//            return LoadResult.Page(
//                data = pagelist,
//                prevKey = pages.page!!.toInt() - 1,
//                nextKey = pages.page!!.toInt() + 1
//            )
//        }catch (e: IOException) {
//            return LoadResult.Error(e)
//        }
//    }
//}
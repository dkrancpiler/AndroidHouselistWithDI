package com.example.houselist_with_di.pagination

import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.models.PagesModel
import com.example.houselist_with_di.network.response.DataX
import com.example.houselist_with_di.network.response.Pagination
import com.example.houselist_with_di.utility.Entity_Model_Mapper
import javax.inject.Inject

class PaginationMapper @Inject constructor(): Entity_Model_Mapper <Pagination, PagesModel> {
    override fun mapFromEntity(entity: Pagination): PagesModel {
        return PagesModel(
            lastPage = entity.lastPage,
            page = entity.page,
            perPage = entity.perPage
        )
    }

    override fun mapToEntity(domainModel: PagesModel): Pagination {
        return Pagination(
            lastPage = domainModel.lastPage,
            page = domainModel.page,
            perPage = domainModel.perPage
        )
    }

    fun mapFromEntityList(entities: List<Pagination>): List<PagesModel> {
        return entities.map { mapFromEntity(it) }
    }
}
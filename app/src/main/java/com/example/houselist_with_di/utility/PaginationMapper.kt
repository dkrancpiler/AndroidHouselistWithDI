package com.example.houselist_with_di.utility

import com.example.houselist_with_di.db.PageEntity
import com.example.houselist_with_di.models.Pagenum
import com.example.houselist_with_di.network.response.Pagination
import javax.inject.Inject

class PaginationMapper @Inject constructor(): Entity_Model_Mapper<Pagination, Pagenum> {
    override fun mapFromEntity(entity: Pagination): Pagenum {
        return Pagenum(
            lastPage = entity.lastPage,
            perPage = entity.perPage,
            page = entity.page
        )
    }

    override fun mapToEntity(domainModel: Pagenum): Pagination {
        return Pagination(
            lastPage = domainModel.lastPage,
            page = domainModel.page,
            perPage = domainModel.page
        )
    }
}
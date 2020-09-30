package com.example.houselist_with_di.network

import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.network.response.Cover
import com.example.houselist_with_di.network.response.DataX
import com.example.houselist_with_di.utility.Entity_Model_Mapper
import javax.inject.Inject

class NetworkMapper @Inject constructor (): Entity_Model_Mapper<DataX, House>{
    override fun mapFromEntity(entity: DataX): House {
        return House(
            id = entity.id,
            title = entity.title,
            title_short = entity.seo_title,
//            image = entity.cover.id.toString(),
            description = entity.description,
            description_short = entity.seo_desc,
            price = entity.price,
            address = entity.address
        )
    }

    override fun mapToEntity(domainModel: House): DataX {
        return DataX(
//            cover = Cover("", "", domainModel.image, "", ""),
            id = domainModel.id,
            title = domainModel.title,
            seo_title = domainModel.title_short,
            description = domainModel.description,
            seo_desc = domainModel.description_short,
            price = domainModel.price,
            address = domainModel.address
        )
    }
     fun mapFromEntityList (entities: List<DataX>): List<House> {
         return entities.map {mapFromEntity(it)}
     }
}
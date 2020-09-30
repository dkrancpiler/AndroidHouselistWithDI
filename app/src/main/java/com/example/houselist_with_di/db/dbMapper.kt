package com.example.houselist_with_di.db

import com.example.houselist_with_di.models.House
import com.example.houselist_with_di.utility.Entity_Model_Mapper
import javax.inject.Inject

class dbMapper @Inject constructor ():  Entity_Model_Mapper<HouseDbEntity, House>{
    override fun mapFromEntity(entity: HouseDbEntity): House {
        return House (
            id = entity.id,
            title = entity.title,
            title_short = entity.title_short,
            image = entity.image,
            description_short = entity.description_short,
            description = entity.description,
            price = entity.price,
            address = entity.address
        )
    }

    override fun mapToEntity(domainModel: House): HouseDbEntity {
        return HouseDbEntity (
            id = domainModel.id,
            title = domainModel.title,
            title_short = domainModel.title_short,
            image = domainModel.image,
            description_short = domainModel.description_short,
            description = domainModel.description,
            price = domainModel.price,
            address = domainModel.address
        )
    }
    fun mapFromEntityList (entities: List<HouseDbEntity>): List<House> {
        return entities.map {mapFromEntity(it)}
    }
}
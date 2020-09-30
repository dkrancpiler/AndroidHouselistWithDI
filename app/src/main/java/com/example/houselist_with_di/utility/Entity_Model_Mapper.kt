package com.example.houselist_with_di.utility


interface Entity_Model_Mapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}
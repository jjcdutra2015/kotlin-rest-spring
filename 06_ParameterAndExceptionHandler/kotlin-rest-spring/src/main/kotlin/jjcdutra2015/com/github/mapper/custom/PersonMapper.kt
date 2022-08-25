package jjcdutra2015.com.github.mapper.custom

import jjcdutra2015.com.github.data.vo.v2.PersonVO
import jjcdutra2015.com.github.model.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonMapper {

    fun mapEntityToVO(person: Person): PersonVO {
        val vo = PersonVO()
        vo.id = person.id
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.address = person.address
        vo.gender = person.gender
        vo.birthday = Date()
        return vo
    }

    fun mapVOToEntity(vo: PersonVO): Person {
        val entity = Person()
        entity.id = vo.id
        entity.firstName = vo.firstName
        entity.lastName = vo.lastName
        entity.address = vo.address
        entity.gender = vo.gender
//        entity.birthday = vo.birthday
        return entity
    }
}
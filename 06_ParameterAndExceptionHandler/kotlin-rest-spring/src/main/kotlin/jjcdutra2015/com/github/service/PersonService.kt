package jjcdutra2015.com.github.service

import jjcdutra2015.com.github.controller.PersonController
import jjcdutra2015.com.github.data.vo.v1.BookVO
import jjcdutra2015.com.github.data.vo.v1.PersonVO
import jjcdutra2015.com.github.exceptions.RequiredObjectIsNullException
import jjcdutra2015.com.github.exceptions.ResourceNotFoundException
import jjcdutra2015.com.github.mapper.DozerMapper
import jjcdutra2015.com.github.mapper.custom.PersonMapper
import jjcdutra2015.com.github.model.Person
import jjcdutra2015.com.github.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var mapper: PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): ArrayList<PersonVO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
        val vos = DozerMapper.parseListObjects(persons, PersonVO::class.java)
        for (person in vos) {
            val withSelfRel = linkTo(PersonController::class.java).slash(person.key).withSelfRel()
            person.add(withSelfRel)
        }
        return vos
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")
        val person = repository.findById(id).orElseThrow { ResourceNotFoundException("No records for this ID!") }
        val personVO = DozerMapper.parseObject(person, PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun create(person: PersonVO?): PersonVO {
        person ?: throw RequiredObjectIsNullException()
        logger.info("Creating one person with name ${person.firstName}!")
        val entity = DozerMapper.parseObject(person, Person::class.java)
        val personVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

//    fun createV2(person: PersonVOV2): PersonVOV2 {
//        logger.info("Creating one person with name ${person.firstName}!")
//        val entity = mapper.mapVOToEntity(person)
//        return mapper.mapEntityToVO(repository.save(entity))
//    }

    fun update(person: PersonVO?): PersonVO {
        person ?: throw RequiredObjectIsNullException()
        logger.info("Updating person with ID ${person.key}!")
        val entity =
            repository.findById(person.key).orElseThrow { ResourceNotFoundException("No records for this ID!") }
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        val personVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun delete(id: Long) {
        logger.info("Deleting person with ID $id!")
        val entity =
            repository.findById(id).orElseThrow { ResourceNotFoundException("No records for this ID!") }
        repository.delete(entity)
    }
}
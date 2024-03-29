package jjcdutra2015.com.github.controller

import jjcdutra2015.com.github.data.vo.v1.PersonVO
import jjcdutra2015.com.github.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/person/v1")
class PersonController {

    @Autowired
    private lateinit var service: PersonService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }

    @CrossOrigin(origins = ["http://localhost:8080"])
    @GetMapping(
        value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun findById(@PathVariable(value = "id") id: Long): PersonVO {
        return service.findById(id)
    }

    @CrossOrigin(origins = ["http://localhost:8080", "https://erudio.com.br"])
    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun create(@RequestBody person: PersonVO): PersonVO {
        return service.create(person)
    }

//    @PostMapping(
//        value = ["/v2"],
//        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
//        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
//    )
//    fun createV2(@RequestBody person: PersonVOV2): PersonVOV2 {
//        return service.createV2(person)
//    }

    @PutMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
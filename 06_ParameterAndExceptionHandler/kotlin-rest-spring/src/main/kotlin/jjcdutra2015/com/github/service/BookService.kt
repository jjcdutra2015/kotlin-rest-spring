package jjcdutra2015.com.github.service

import jjcdutra2015.com.github.controller.BookController
import jjcdutra2015.com.github.data.vo.v1.BookVO
import jjcdutra2015.com.github.exceptions.RequiredObjectIsNullException
import jjcdutra2015.com.github.exceptions.ResourceNotFoundException
import jjcdutra2015.com.github.mapper.DozerMapper
import jjcdutra2015.com.github.mapper.custom.BookMapper
import jjcdutra2015.com.github.model.Book
import jjcdutra2015.com.github.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BookService {

    @Autowired
    private lateinit var repository: BookRepository

    @Autowired
    private lateinit var mapper: BookMapper

    private val logger = Logger.getLogger(BookService::class.java.name)

    fun findAll(): ArrayList<BookVO> {
        logger.info("Find all books")
        val books = repository.findAll()
        val vos = DozerMapper.parseListObjects(books, BookVO::class.java)
        for (book in vos) {
            val withSelfRel = linkTo(BookController::class.java).slash(book.key).withSelfRel()
            book.add(withSelfRel)
        }
        return vos
    }

    fun findById(id: Long): BookVO {
        logger.info("Find book by id")
        val book = repository.findById(id).orElseThrow { ResourceNotFoundException("No records for this ID!") }
        val vo = DozerMapper.parseObject(book, BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(vo.key).withSelfRel()
        vo.add(withSelfRel)
        return vo
    }

    fun create(bookVO: BookVO?): BookVO {
        bookVO ?: throw RequiredObjectIsNullException()
        logger.info("Creating a book with author ${bookVO.author}")
        val book = DozerMapper.parseObject(bookVO, Book::class.java)
        val vo = DozerMapper.parseObject(repository.save(book), BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(vo.key).withSelfRel()
        vo.add(withSelfRel)
        return vo
    }

    fun update(bookVO: BookVO?): BookVO {
        bookVO ?: throw RequiredObjectIsNullException()
        logger.info("Updating a book with author ${bookVO.author}")
        val entity =
            repository.findById(bookVO.key).orElseThrow { ResourceNotFoundException("No records for this ID!") }
        entity.author = bookVO.author
        entity.launchDate = bookVO.launchDate
        entity.price = bookVO.price
        entity.title = bookVO.title
        val vo = DozerMapper.parseObject(repository.save(entity), BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(vo.key).withSelfRel()
        vo.add(withSelfRel)
        return vo
    }

    fun delete(id: Long) {
        logger.info("Deleting book with id $id")
        val book = repository.findById(id).orElseThrow { ResourceNotFoundException("No records for this ID!") }
        repository.delete(book)
    }
}
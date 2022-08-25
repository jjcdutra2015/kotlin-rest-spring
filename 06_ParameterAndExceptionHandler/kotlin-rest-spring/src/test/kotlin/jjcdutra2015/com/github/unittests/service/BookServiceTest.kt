package jjcdutra2015.com.github.unittests.service

import jjcdutra2015.com.github.unittests.MockBook
import jjcdutra2015.com.github.exceptions.RequiredObjectIsNullException
import jjcdutra2015.com.github.repository.BookRepository
import jjcdutra2015.com.github.service.BookService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class BookServiceTest {

    private lateinit var inputObject: MockBook

    @InjectMocks
    private lateinit var service: BookService

    @Mock
    private lateinit var repository: BookRepository

    @BeforeEach
    fun setUp() {
        inputObject = MockBook()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findAll() {
        val list = inputObject.mockEntityList()

        `when`(repository.findAll()).thenReturn(list)

        val books = service.findAll()

        assertNotNull(books)
        assertEquals(14, books.size)

        val bookOne = books[1]

        assertNotNull(bookOne)
        assertNotNull(bookOne.key)
        assertNotNull(bookOne.links)
        assertTrue(bookOne.links.toString().contains("</api/book/v1/1>;rel=\"self\""))
        assertEquals("Author Test1", bookOne.author)
        assertEquals(LocalDateTime.of(2022, 8, 15, 21, 6), bookOne.launchDate)
        assertEquals(BigDecimal.ZERO, bookOne.price)
        assertEquals("Title Test1", bookOne.title)
    }

    @Test
    fun findById() {
        val book = inputObject.mockEntity(1)
        book.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(book))

        val vo = service.findById(1)

        assertNotNull(vo.key)
        assertNotNull(vo)
        assertTrue(vo.links.toString().contains("</api/book/v1/1>;rel=\"self\""))
        assertEquals(book.id, vo.key)
        assertEquals(book.author, vo.author)
        assertEquals(book.launchDate, vo.launchDate)
        assertEquals(book.price, vo.price)
        assertEquals(book.title, vo.title)
    }

    @Test
    fun create() {
        val book = inputObject.mockEntity(1)

        val persisted = book.copy()
        persisted.id = 1

        `when`(repository.save(book)).thenReturn(persisted)

        val bookVO = inputObject.mockVO(1)
        val vo = service.create(bookVO)

        assertNotNull(vo.key)
        assertNotNull(vo)
        assertTrue(vo.links.toString().contains("</api/book/v1/1>;rel=\"self\""))
        assertEquals(bookVO.author, vo.author)
        assertEquals(bookVO.launchDate, vo.launchDate)
        assertEquals(bookVO.price, vo.price)
        assertEquals(bookVO.title, vo.title)

    }

    @Test
    fun createWithNullBook() {
        val exception: Exception = assertThrows(
            RequiredObjectIsNullException::class.java
        ) {service.create(null)}

        assertEquals("It is not allowed persist a null object", exception.message)
    }

    @Test
    fun update() {
        val book = inputObject.mockEntity(1)

        val persisted = book.copy()
        persisted.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(book))
        `when`(repository.save(book)).thenReturn(persisted)

        val bookVO = inputObject.mockVO(1)
        val vo = service.update(bookVO)

        assertNotNull(vo.key)
        assertNotNull(vo)
        assertTrue(vo.links.toString().contains("</api/book/v1/1>;rel=\"self\""))
        assertEquals(bookVO.author, vo.author)
        assertEquals(bookVO.launchDate, vo.launchDate)
        assertEquals(bookVO.price, vo.price)
        assertEquals(bookVO.title, vo.title)
    }

    @Test
    fun delete() {
        val book = inputObject.mockEntity(1)

        `when`(repository.findById(1)).thenReturn(Optional.of(book))

        service.delete(1)
    }
}
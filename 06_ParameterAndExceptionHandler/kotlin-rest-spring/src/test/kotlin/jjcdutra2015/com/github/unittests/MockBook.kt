package jjcdutra2015.com.github.unittests

import jjcdutra2015.com.github.data.vo.v1.BookVO
import jjcdutra2015.com.github.model.Book
import org.assertj.core.util.DateUtil
import java.math.BigDecimal
import java.time.LocalDateTime

class MockBook {
    fun mockEntity(): Book {
        return mockEntity(0)
    }

    fun mockVO(): BookVO {
        return mockVO(0)
    }

    fun mockEntityList(): ArrayList<Book> {
        val books: ArrayList<Book> = ArrayList()
        for (i in 0..13) {
            books.add(mockEntity(i))
        }
        return books
    }

    fun mockVOList(): ArrayList<BookVO> {
        val books: ArrayList<BookVO> = ArrayList()
        for (i in 0..13) {
            books.add(mockVO(i))
        }
        return books
    }

    fun mockEntity(number: Int): Book {
        val book = Book()
        book.author = "Author Test$number"
        book.launchDate = LocalDateTime.of(2022, 8, 15, 21, 6)
        book.price = BigDecimal.ZERO
        book.id = number.toLong()
        book.title = "Title Test$number"
        return book
    }

    fun mockVO(number: Int): BookVO {
        val book = BookVO()
        book.author = "Author Test$number"
        book.launchDate = LocalDateTime.of(2022, 8, 15, 21, 6)
        book.price = BigDecimal.ZERO
        book.key = number.toLong()
        book.title = "Title Test$number"
        return book
    }
}
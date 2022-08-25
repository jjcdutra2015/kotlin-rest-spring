package jjcdutra2015.com.github.mapper.custom

import jjcdutra2015.com.github.data.vo.v1.BookVO
import jjcdutra2015.com.github.model.Book
import org.springframework.stereotype.Service

@Service
class BookMapper {

    fun mapEntityToVO(book: Book): BookVO {
        val vo = BookVO()
        vo.key = book.id
        vo.author = book.author
        vo.launchDate = book.launchDate
        vo.price = book.price
        vo.title = book.title
        return vo
    }

    fun mapVOToEntity(vo: BookVO): Book {
        val entity = Book()
        entity.id = vo.key
        entity.author = vo.author
        entity.launchDate = vo.launchDate
        entity.price = vo.price
        entity.title = vo.title
        return entity
    }
}
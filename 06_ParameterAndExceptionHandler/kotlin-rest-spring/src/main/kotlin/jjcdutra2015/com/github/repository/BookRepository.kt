package jjcdutra2015.com.github.repository

import jjcdutra2015.com.github.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long?>
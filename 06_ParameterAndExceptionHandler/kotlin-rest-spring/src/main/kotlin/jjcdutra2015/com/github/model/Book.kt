package jjcdutra2015.com.github.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "books")
data class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, length = 80)
    var author: String = "",

    @Column(name = "launch_date")
    var launchDate: LocalDateTime? = LocalDateTime.now(),

    @Column(nullable = false)
    var price: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false, length = 255)
    var title: String = ""
)
package jjcdutra2015.com.github.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.time.LocalDateTime

data class BookVO(

    @Mapping("id")
    @JsonProperty("id")
    var key: Long = 0,

    var author: String = "",

    var launchDate: LocalDateTime? = LocalDateTime.now(),

    var price: BigDecimal = BigDecimal.ZERO,

    var title: String = ""
) : RepresentationModel<BookVO>()
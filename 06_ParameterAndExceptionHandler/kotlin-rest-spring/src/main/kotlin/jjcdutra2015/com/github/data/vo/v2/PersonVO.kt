package jjcdutra2015.com.github.data.vo.v2

import java.util.*

data class PersonVO(
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
    var birthday: Date? = null
)
package jjcdutra2015.com.github.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class RequiredObjectIsNullException : RuntimeException {
    constructor() : super("It is not allowed persist a null object")
    constructor(ex: String?) : super(ex)
}
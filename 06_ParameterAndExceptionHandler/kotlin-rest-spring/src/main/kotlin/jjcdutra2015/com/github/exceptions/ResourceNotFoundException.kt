package jjcdutra2015.com.github.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(exception: String?) : RuntimeException(exception)
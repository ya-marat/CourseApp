package com.example.domain.domain.result

sealed class DomainError {

    //Common Errors

    object NotFound: DomainError()
    object Unknown: DomainError()

    //Remote Errors

    object NoInternet: DomainError()
    data class Server(val code: Int, val message: String): DomainError()

    //Local errors
    object LocalSaveError: DomainError()

}
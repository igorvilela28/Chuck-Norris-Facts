package com.igorvd.chuckfacts.domain.exceptions

/**
 * Utilize essa exceção quando der um erro no servidor, e a mensagem de erro também é retornada
 * pelo servidor
 * @author Igor Vilela
 * @since 11/01/2018
 */
class MyServerErrorException : MyException {

    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)

}
package com.igorvd.chuckfacts.domain


/**
 * Interface for a Interactor from Clean Architecture.
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract)
 * @author Igor Vilela
 * @since 28/12/17
 */

interface Interactor<T, Params> {

    suspend fun execute(params: Params): T

}
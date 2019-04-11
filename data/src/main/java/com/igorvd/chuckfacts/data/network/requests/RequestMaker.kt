package com.igorvd.chuckfacts.data.network.requests

import com.igorvd.chuckfacts.domain.exceptions.MyException
import retrofit2.Call
import retrofit2.Retrofit


/**
 * Makes a HTTP request for some data using the [Retrofit]
 * @author Igor Vilela
 * @since 13/10/17
 */

interface RequestMaker {

    @Throws(MyException::class)
    suspend fun <T : Any> getResult(call: Call<T>): T

}
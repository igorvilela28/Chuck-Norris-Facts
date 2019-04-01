package com.igorvd.chuckfacts.data.network.requests

import com.igorvd.chuckfacts.domain.exceptions.MyException
import com.igorvd.chuckfacts.domain.exceptions.MyIOException
import com.igorvd.chuckfacts.domain.exceptions.MyServerErrorException
import retrofit2.Call
import retrofit2.Response
import ru.gildor.coroutines.retrofit.awaitResponse
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


/**
 * Makes a HTTP request for some data. Useful to avoid logic duplication for API Calls.
 * @author Igor Vilela
 * @since 13/10/17
 */
class RequestMakerImpl @Inject constructor() : RequestMaker {

    override suspend fun <T> getResult(call: Call<T>): T {

        try {

            val response: Response<T> = call.awaitResponse()
            return parseResponse(call, response)

        } catch (e: IOException) {

            val url = call.request().url().toString()
            val message = "IOError in call, url: $url"
            Timber.d(message)
            throw MyIOException(message, e)

        } catch (e: Exception) {

            //we are only retrowing it to be more clear while debugging
            Timber.e(e, "Exception %s on SyncRequestManagerImpl", e.javaClass.simpleName)
            throw e
        }

    }

    @Throws(MyException::class)
    private fun <T> parseResponse(call: Call<T>, response: Response<T>): T {

        val url = call.request().url().toString()

        if (response.isSuccessful) {

            Timber.d("call completed successfuly for url: $url")

            return response.body()!!

        } else {
            val message = "Http error, code: ${response.code()}, url: $url"
            throw MyServerErrorException(message)

            //TODO: altera quando precisarmos tratar códigos de erro http em separado.
        }
    }
}
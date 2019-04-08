package com.igorvd.chuckfacts.data.network.requests

import com.igorvd.chuckfacts.domain.exceptions.MyHttpErrorException
import com.igorvd.chuckfacts.domain.exceptions.MyIOException
import retrofit2.Call
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


/**
 * Makes a HTTP request for some data. Useful to avoid logic duplication for API Calls.
 * @author Igor Vilela
 * @since 13/10/17
 */
class RequestMakerImpl @Inject constructor() : RequestMaker {

    override suspend fun <T : Any> getResult(call: Call<T>): T {

        try {

            val result = call.await()
            return result

        } catch (e: HttpException) {

            val exception = when (e.code()) {
                in 400..499 -> MyHttpErrorException.HttpClientErrorException(e.message(), e.code(), e)
                else -> MyHttpErrorException.HttpServerErrorException(e.message(), e.code(), e)
            }
            throw exception

        } catch (e: IOException) {

            val url = call.request().url().toString()
            val message = "IOError in call, url: $url"
            Timber.d(message)
            throw MyIOException(message, e)

        } catch (e: Exception) {

            //we are only retrowing it to be more clear while debugging
            Timber.e(e, "unexpected exception on request maker ${e.javaClass.simpleName}")
            throw e
        }
    }
}
package com.igorvd.chuckfacts.data.network.requests

import com.igorvd.chuckfacts.domain.exceptions.HttpClientErrorException
import com.igorvd.chuckfacts.domain.exceptions.HttpServerErrorException
import com.igorvd.chuckfacts.domain.exceptions.MyIOException
import retrofit2.Call
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await
import timber.log.Timber
import java.io.IOException


/**
 * Makes a HTTP request for some data. Useful to avoid logic duplication for API Calls.
 * @author Igor Vilela
 * @since 13/10/17
 */
class RequestMakerImpl constructor(private val callRetryDelays: CallRetryDelays) : RequestMaker {

    override suspend fun <T : Any> getResult(call: Call<T>): T = withRetry(call, callRetryDelays) {

        try {

            val result = it.await()
            result

        } catch (e: HttpException) {

            val exception = when (e.code()) {
                in 400..499 -> HttpClientErrorException(e.message(), e.code(), e)
                else -> HttpServerErrorException(e.message(), e.code(), e)
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
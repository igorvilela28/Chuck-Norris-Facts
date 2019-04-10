package com.igorvd.chuckfacts.testutils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import java.util.concurrent.TimeUnit

/**
 * Useful enqueues to mock our web server responses
 * @author Igor Vilela
 * @since 18/01/18
 */

private const val BODY_DELAY = 0L

fun MockWebServer.enqueue200Response(body: String) {

    enqueue(
        MockResponse()
        .setResponseCode(200)
        .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
        .setBody(body))

}

fun MockWebServer.enqueue500Response(body: String) {

    enqueue(
        MockResponse()
            .setResponseCode(500)
            .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
            .setBody(body))

}

fun MockWebServer.enqueueIOError(body: String) {

    enqueue(MockResponse()
        .setResponseCode(200)
        .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
        .setSocketPolicy(SocketPolicy.NO_RESPONSE)
        .setBody(body))

}
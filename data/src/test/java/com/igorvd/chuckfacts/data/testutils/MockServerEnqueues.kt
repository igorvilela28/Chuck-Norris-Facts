package com.igorvd.chuckfacts.data.testutils



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
    enqueueResponse(body, 200)
}

fun MockWebServer.enqueue400Response(body: String) {
    enqueueResponse(body, 400)
}

fun MockWebServer.enqueue500Response(body: String) {
    enqueueResponse(body, 500)
}

fun MockWebServer.enqueueIOError(body: String) {

    enqueue(MockResponse()
        .setResponseCode(200)
        .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
        .setSocketPolicy(SocketPolicy.NO_RESPONSE)
        .setBody(body))

}

private fun MockWebServer.enqueueResponse(body: String, code: Int) {
    enqueue(MockResponse()
        .setResponseCode(code)
        .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
        .setBody(body))
}
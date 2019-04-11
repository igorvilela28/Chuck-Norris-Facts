package com.igorvd.chuckfacts.data.network.requests

private const val DEFAULT_FIRST_ATTEMPT_DELAY_MILLS = 4000L
private const val DEFAULT_SECOND_ATTEMPT_DELAY_MILLS = 8000L

data class CallRetryDelays(
    val firstDelayInMills: Long = DEFAULT_FIRST_ATTEMPT_DELAY_MILLS,
    val secondDelayInMills: Long = DEFAULT_SECOND_ATTEMPT_DELAY_MILLS
) {

    fun getTotalDelay(): Long = firstDelayInMills + secondDelayInMills

}
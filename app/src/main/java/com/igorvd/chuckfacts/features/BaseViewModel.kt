package com.igorvd.chuckfacts.features

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.igorvd.chuckfacts.domain.exceptions.MyIOException
import com.igorvd.chuckfacts.domain.exceptions.MyServerErrorException
import com.igorvd.chuckfacts.utils.SingleLiveEvent
import com.igorvd.chuckfacts.utils.extensions.throwOrLog

/**
 * @author Igor Vilela
 * @since 28/12/17
 */
abstract class BaseViewModel() : ViewModel() {

    val showProgressEvent = SingleLiveEvent<Void>()
    val hideProgressEvent = SingleLiveEvent<Void>()

    /**
     * This method should be used when we the view model is asked to do some long running task.
     * Because we're running a long task, the user should see a progress indicator, and when the
     * job completes we need to remove the progress. This method is useful to avoid duplication of
     * this process
     *
     * param[work] a function to be executed between the showProgress and hideProgress events
     */
    protected suspend fun doWorkWithProgress(work: suspend () -> Unit) {

        showProgressEvent.call()
        try {
            work()
        } catch (e: MyIOException) {

            //TODO: show network error message

        } catch (e: MyServerErrorException) {

            //TODO: show server error message

        } catch (e: Exception) {
            e.throwOrLog()
        } finally {
            hideProgressEvent.call()
        }
    }

    fun observe(
            owner: LifecycleOwner, showProgress: () -> Unit, hideProgress: () -> Unit
    ) {

        showProgressEvent.observe(owner, showProgress)
        hideProgressEvent.observe(owner, hideProgress)
    }
}


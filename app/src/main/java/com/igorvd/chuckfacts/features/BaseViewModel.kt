package com.igorvd.chuckfacts.features

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
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

    protected val _showProgressEvent = SingleLiveEvent<Void>()
    val showProgressEvent: LiveData<Void>
        get() = _showProgressEvent

    protected val _hideProgressEvent = SingleLiveEvent<Void>()
    val hideProgressEvent: LiveData<Void>
        get() = _hideProgressEvent

    /**
     * This method should be used when we the view model is asked to do some long running task.
     * Because we're running a long task, the user should see a progress indicator, and when the
     * job completes we need to remove the progress. This method is useful to avoid duplication of
     * this process
     *
     * param[work] a function to be executed between the showProgress and hideProgress events
     */
    protected suspend fun doWorkWithProgress(work: suspend () -> Unit) {

        _showProgressEvent.call()
        try {
            work()
        } catch (e: MyIOException) {

            //TODO: show network error message

        } catch (e: MyServerErrorException) {

            //TODO: show server error message

        } catch (e: Exception) {
            e.throwOrLog()
        } finally {
            _hideProgressEvent.call()
        }
    }
}


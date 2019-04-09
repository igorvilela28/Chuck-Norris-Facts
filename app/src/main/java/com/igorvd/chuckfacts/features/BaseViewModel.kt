package com.igorvd.chuckfacts.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorvd.chuckfacts.domain.exceptions.MyHttpErrorException
import com.igorvd.chuckfacts.domain.exceptions.MyIOException
import com.igorvd.chuckfacts.utils.SingleLiveEvent
import com.igorvd.chuckfacts.utils.extensions.throwOrLog

abstract class BaseViewModel : ViewModel() {

    protected val _showProgressEvent = SingleLiveEvent<Void>()
    val showProgressEvent: LiveData<Void>
        get() = _showProgressEvent

    protected val _hideProgressEvent = SingleLiveEvent<Void>()
    val hideProgressEvent: LiveData<Void>
        get() = _hideProgressEvent

    protected val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState>
        get() = _screenState

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
            _screenState.value = NetworkError
        } catch (e: MyHttpErrorException) {
            _screenState.value = HttpError
        } catch (e: Exception) {
            e.throwOrLog()
        } finally {
            _hideProgressEvent.call()
        }
    }
}


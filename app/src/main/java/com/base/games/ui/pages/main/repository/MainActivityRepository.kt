package com.base.games.ui.pages.main.repository

import com.base.core.extensions.failed
import com.base.core.networking.DataFetchResult
import com.base.core.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class MainActivityRepository(
    private val remote: MainActivityRemoteData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : MainActivityContract.Repository {

    override fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        dataFetchResult.failed(error)
        Timber.e(error.localizedMessage)
    }
}

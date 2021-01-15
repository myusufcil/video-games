package com.base.games.ui.pages.detail.repository

import com.base.core.extensions.failed
import com.base.core.networking.DataFetchResult
import com.base.core.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber


class DetailActivityRepository(
    private val remote: DetailActivityRemoteData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : DetailActivityContract.Repository {

    override fun <T> handleError(
        dataFetchResult: PublishSubject<DataFetchResult<T>>,
        error: Throwable
    ) {
        dataFetchResult.failed(error)
        Timber.e(error.localizedMessage)
    }

}
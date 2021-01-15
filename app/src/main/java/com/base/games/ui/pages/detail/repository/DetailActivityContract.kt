package com.base.games.ui.pages.detail.repository

import com.base.core.networking.DataFetchResult
import io.reactivex.subjects.PublishSubject


interface DetailActivityContract {

    interface Repository {
        fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {

    }
}
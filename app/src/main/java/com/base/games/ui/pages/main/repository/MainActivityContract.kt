package com.base.games.ui.pages.main.repository

import com.base.core.networking.DataFetchResult
import io.reactivex.subjects.PublishSubject

interface MainActivityContract {

    interface Repository {
        fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {

    }

}

package com.base.games.ui.pages.home.repository

import com.base.core.extensions.*
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.DataFetchResult
import com.base.core.networking.Scheduler
import com.base.data.response.GamesListResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/

@FragmentScope
class HomeFragmentRepository(
    private val remote: HomeFragmentRemoteData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : HomeFragmentContract.Repository {

    override var gamesList: PublishSubject<DataFetchResult<GamesListResponse>> =
        PublishSubject.create<DataFetchResult<GamesListResponse>>()

    override fun getGamesList() {
        gamesList.loading(true)
        remote.getGamesList()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    gamesList.success(it)
                },
                { _error ->
                    handleError(gamesList, _error)
                }
            ).addTo(compositeDisposable)
    }

    override fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error.localizedMessage)
    }
}









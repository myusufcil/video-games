package com.base.games.ui.pages.detailgames.repository

import com.base.core.extensions.*
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.DataFetchResult
import com.base.core.networking.Scheduler
import com.base.data.database.model.FavGamesDTO
import com.base.data.response.GamesDetailResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/

@FragmentScope
class DetailGamesFragmentRepository(
    private val remote: DetailGamesFragmentRemoteData,
    private val locale: DetailGamesFragmentLocalData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : DetailGamesFragmentContract.Repository {

    override fun insertFav(dataFav: FavGamesDTO) {
       return locale.insertFav(dataFav)
    }

    override fun deleteById(pk: Int?) {
        return locale.deleteById(pk)
    }


    override var gamesDetail: PublishSubject<DataFetchResult<GamesDetailResponse>> =
        PublishSubject.create<DataFetchResult<GamesDetailResponse>>()

    override fun getGameDetail(pk: Int?) {
        gamesDetail.loading(true)
        remote.getGamesDetail(pk)
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    gamesDetail.success(it)
                },
                { _error ->
                    handleError(gamesDetail, _error)
                }
            ).addTo(compositeDisposable)
    }


    override fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error.localizedMessage)
    }
}
package com.base.games.ui.pages.favoritess.repository

import com.base.core.extensions.*
import com.base.core.networking.DataFetchResult
import com.base.core.networking.Scheduler
import com.base.data.database.model.FavGamesDTO
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/
class FavoritesFragmentRepository(
    private val remote: FavoritesFragmentRemoteData,
    private val locale: FavoritesFragmentLocaleData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : FavoritesFragmentContract.Repository {

    override val getAllFavDataResult =
        PublishSubject.create<DataFetchResult<List<FavGamesDTO>>>()

    override fun getAllFav() {
        getAllFavDataResult.loading(true)
        locale.getAllFav()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    getAllFavDataResult.success(it)
                },
                { error ->
                    handleError(getAllFavDataResult, error)
                })
            .addTo(compositeDisposable)
    }

    override fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error.localizedMessage)
    }
}
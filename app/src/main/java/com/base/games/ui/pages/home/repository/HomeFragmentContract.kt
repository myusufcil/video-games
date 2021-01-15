package com.base.games.ui.pages.home.repository

import com.base.core.networking.DataFetchResult
import com.base.data.response.GamesListResponse
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/
interface HomeFragmentContract {

    interface Repository {
        var gamesList:PublishSubject<DataFetchResult<GamesListResponse>>
        fun getGamesList()
        fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable)
    }
    interface Remote{
        fun getGamesList(): Single<GamesListResponse>
    }
}
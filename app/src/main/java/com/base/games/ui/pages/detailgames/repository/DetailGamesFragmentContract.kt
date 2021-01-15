package com.base.games.ui.pages.detailgames.repository

import com.base.core.networking.DataFetchResult
import com.base.data.database.model.FavGamesDTO
import com.base.data.response.GamesDetailResponse
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/
interface DetailGamesFragmentContract {

    interface Repository {
        fun insertFav(dataFav:FavGamesDTO)
        fun deleteById(pk:Int?)
        var gamesDetail: PublishSubject<DataFetchResult<GamesDetailResponse>>
        fun getGameDetail(pk: Int?)
        fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {
        fun getGamesDetail(pk: Int?): Single<GamesDetailResponse>
    }

    interface Locale {
        fun insertFav(favData: FavGamesDTO)
        fun deleteById(favId:Int?)
    }

}
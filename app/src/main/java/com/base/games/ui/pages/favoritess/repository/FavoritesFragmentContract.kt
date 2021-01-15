package com.base.games.ui.pages.favoritess.repository

import com.base.core.networking.DataFetchResult
import com.base.data.database.model.FavGamesDTO
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/
interface FavoritesFragmentContract {

    interface Repository {
        val getAllFavDataResult: PublishSubject<DataFetchResult<List<FavGamesDTO>>>
        fun getAllFav()
        fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable)
    }
    interface Remote{

    }

    interface Locale{
        fun getAllFav(): Single<List<FavGamesDTO>>
    }

}
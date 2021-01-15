package com.base.games.ui.pages.favoritess.repository

import com.base.core.ioc.scopes.FragmentScope
import com.base.data.database.dao.FavoritesDao
import com.base.data.database.model.FavGamesDTO
import io.reactivex.Single

/*
Created by Muhammed Yusuf Çil
on 1/14/21
*/

@FragmentScope
class FavoritesFragmentLocaleData(
    private var favDao: FavoritesDao
) : FavoritesFragmentContract.Locale {

    override fun getAllFav(): Single<List<FavGamesDTO>> {
        return favDao.getAllGamesCard()
    }

}
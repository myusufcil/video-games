package com.base.games.ui.pages.detailgames.repository

import com.base.core.ioc.scopes.FragmentScope
import com.base.data.database.dao.FavoritesDao
import com.base.data.database.model.FavGamesDTO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
Created by Muhammed Yusuf Çil
on 1/13/21
*/
@FragmentScope
class DetailGamesFragmentLocalData(
    private var favDao: FavoritesDao
) : DetailGamesFragmentContract.Locale {

    override fun insertFav(favData: FavGamesDTO) {
        GlobalScope.launch {
            favDao.insert(favData)
        }
    }

    override fun deleteById(favId: Int?) {
        GlobalScope.launch {
            favDao.deleteById(favId)
        }
    }

}
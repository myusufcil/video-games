package com.base.games.ui.pages.home.repository

import com.base.data.request.GamesApiInterface
import com.base.data.response.GamesListResponse
import io.reactivex.Single

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/
class HomeFragmentRemoteData(
    private val apiInterface: GamesApiInterface
) :
    HomeFragmentContract.Remote {

    override fun getGamesList(): Single<GamesListResponse> =
        apiInterface.getListOfGames()


}
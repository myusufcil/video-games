package com.base.games.ui.pages.detailgames.repository

import com.base.data.request.GamesApiInterface
import com.base.data.response.GamesDetailResponse
import com.base.data.response.GamesListResponse
import io.reactivex.Single

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/
class DetailGamesFragmentRemoteData(
    private val apiInterface: GamesApiInterface
) : DetailGamesFragmentContract.Remote {

    override fun getGamesDetail(pk: Int?): Single<GamesDetailResponse> =
        apiInterface.getDetailOfGame(pk = pk)

}
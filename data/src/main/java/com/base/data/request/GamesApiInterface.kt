package com.base.data.request

import com.base.core.constants.AppConstants
import com.base.data.response.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GamesApiInterface {

    @GET("games")
    fun getListOfGames(
        @Header("x-rapidapi-key") apiKey: String = AppConstants.API_KEY,
        @Header("x-rapidapi-host") host: String = AppConstants.API_HOST
    ): Single<GamesListResponse>

    @GET("games/{game_pk}")
    fun getDetailOfGame(
        @Path("game_pk") pk: Int?,
        @Header("x-rapidapi-key") apiKey: String = AppConstants.API_KEY
    ): Single<GamesDetailResponse>

}
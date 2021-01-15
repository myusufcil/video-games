package com.base.games.ui.pages.detail.repository

import com.base.data.request.GamesApiInterface

class DetailActivityRemoteData(private val apiInterface: GamesApiInterface) :
    DetailActivityContract.Remote {
}
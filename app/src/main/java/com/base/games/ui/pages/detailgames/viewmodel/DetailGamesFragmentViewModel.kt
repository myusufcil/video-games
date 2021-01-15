package com.base.games.ui.pages.detailgames.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.base.component.ui.detailgames.DetailGamesDTO
import com.base.core.extensions.toLiveData
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.DataFetchResult
import com.base.core.ui.recyclerview.DisplayItem
import com.base.data.database.model.FavGamesDTO
import com.base.data.response.GamesDetailResponse
import com.base.games.ui.base.viewmodel.BaseFragmentViewModel
import com.base.games.ui.pages.detailgames.repository.DetailGamesFragmentRepository
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/

@FragmentScope
class DetailGamesFragmentViewModel @Inject constructor(
    private val repository: DetailGamesFragmentRepository
) : BaseFragmentViewModel() {

    var gameDetailPublishSubject = PublishSubject.create<List<DisplayItem>>()

    private val homePageList = mutableListOf<DisplayItem>()

    val getGameDetailResult: LiveData<DataFetchResult<GamesDetailResponse>> =
        Transformations.map(repository.gamesDetail.toLiveData(disposables)) { _data ->
            when (_data) {
                is DataFetchResult.Success -> {
                    fillDetail(_data.data)
                }
            }
            _data
        }

    fun getDetail(pk: Int?) {
        repository.getGameDetail(pk)
    }

    private fun fillDetail(data: GamesDetailResponse) {
        homePageList.add(
            DetailGamesDTO(
                metacriticRate = data.metacritic,
                releasedDate = data.released,
                description = data.description,
                imagePath = data.background_image,
                nameOfGame = data.name,
                gameId = data.id
            )
        )
        gameDetailPublishSubject.onNext(homePageList)
    }

    fun insertFav(dataFav: FavGamesDTO) {
        repository.insertFav(dataFav)
    }

    fun deleteById(pk: Int?) {
        repository.deleteById(pk)
    }
}
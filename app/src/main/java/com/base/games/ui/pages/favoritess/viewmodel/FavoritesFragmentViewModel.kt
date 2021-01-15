package com.base.games.ui.pages.favoritess.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.base.component.ui.gamescard.HomePageCardDTO
import com.base.core.extensions.toLiveData
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.DataFetchResult
import com.base.core.ui.recyclerview.DisplayItem
import com.base.data.database.model.FavGamesDTO
import com.base.games.ui.base.viewmodel.BaseFragmentViewModel
import com.base.games.ui.pages.favoritess.repository.FavoritesFragmentRepository
import io.reactivex.Single
import javax.inject.Inject


/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/

@FragmentScope
class FavoritesFragmentViewModel @Inject constructor(
    private val repository: FavoritesFragmentRepository
) : BaseFragmentViewModel() {

    fun getData(){
        repository.getAllFav()
    }

    val savedNewsDataStatusResult: LiveData<DataFetchResult<*>> =
        Transformations.map(repository.getAllFavDataResult.toLiveData(disposables)) { result ->
            when (result) {
                is DataFetchResult.Progress -> {
                    result
                }
                is DataFetchResult.Failure -> {
                    result
                }
                is DataFetchResult.Success -> {
                    getMyListData(result)
                }
            }
        }

    private fun getMyListData(data: DataFetchResult.Success<List<FavGamesDTO>>): DataFetchResult<MutableList<DisplayItem>> {
        val myList = mutableListOf<DisplayItem>()
        data.data.let {
            it.forEach { _localData ->
                myList.add( HomePageCardDTO(
                    photoPath = _localData.photoPath,
                    gamesName = _localData.gameName,
                    gamesRate = _localData.metacriticRate,
                    gamesReleased = _localData.releasedDate,
                    id = _localData.gameId?.toInt() ?: 0
                ))
            }
        }
        return DataFetchResult.success(myList)
    }
}
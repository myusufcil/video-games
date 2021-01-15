package com.base.games.ui.pages.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.base.component.ui.gamescard.HomePageCardDTO
import com.base.component.ui.homepageslider.HomePageSliderDTO
import com.base.component.ui.homepageslider.SliderItem
import com.base.component.ui.nodatafound.NoDataFoundDTO
import com.base.core.extensions.toLiveData
import com.base.core.helpers.LocalPrefManager
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.DataFetchResult
import com.base.core.ui.recyclerview.DisplayItem
import com.base.data.response.GamesListResponse
import com.base.data.response.Results
import com.base.games.ui.base.viewmodel.BaseFragmentViewModel
import com.base.games.ui.pages.home.repository.HomeFragmentRepository
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/

@FragmentScope
class HomeFragmentViewModel @Inject constructor(
    var repository: HomeFragmentRepository
) : BaseFragmentViewModel() {

    @Inject
    lateinit var localPrefManager: LocalPrefManager

    var gamesListPublishSubject = PublishSubject.create<List<DisplayItem>>()

    val homePageList = mutableListOf<DisplayItem>()

    val liveFilterList = MutableLiveData<List<Results?>>()

    private var filterList = mutableListOf<DisplayItem>()

    val gamesListDataResult: LiveData<DataFetchResult<GamesListResponse>> =
        Transformations.map(repository.gamesList.toLiveData(disposables)) { _data ->
            when (_data) {
                is DataFetchResult.Success -> {
                    fillHomePageItems(_data.data.results)
                    liveFilterList.value = _data.data.results
                }
            }
            _data
        }

    fun change(data: List<Results?>): List<DisplayItem> {
        filterList.clear()
        data.map { _results ->
            filterList.add(
                HomePageCardDTO(
                    id = _results?.id ?: 0,
                    photoPath = _results?.background_image,
                    gamesName = _results?.name,
                    gamesRate = _results?.rating.toString(),
                    gamesReleased = _results?.released
                )
            )
        }
        return filterList.toSet().toList()
    }

    private fun fillHomePageItems(data: List<Results?>) {
        val sliderItemList = arrayListOf<SliderItem>()
        data.take(3).map { _results ->
            sliderItemList.add(
                SliderItem(
                    photoPath = _results?.background_image,
                    id = _results?.id
                )
            )
        }
        if (!sliderItemList.isNullOrEmpty()) {
            homePageList.add(HomePageSliderDTO(sliderItemList))
        }

        data.drop(3).map { _results ->
            homePageList.add(
                HomePageCardDTO(
                    id = _results?.id ?: 0,
                    photoPath = _results?.background_image,
                    gamesName = _results?.name,
                    gamesRate = _results?.rating.toString(),
                    gamesReleased = _results?.released
                )
            )
        }
        gamesListPublishSubject.onNext(homePageList)
    }

    fun noDataFound(): List<DisplayItem> {
        var noDataFoundList = mutableListOf<DisplayItem>()
        noDataFoundList.add(
            NoDataFoundDTO(
                "Üzgünüz, aradığınız oyun bulunamadı!"
            )
        )
        return noDataFoundList
    }

    fun getGamesList() {
        repository.getGamesList()
    }
}



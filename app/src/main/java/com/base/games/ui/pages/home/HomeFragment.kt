package com.base.games.ui.pages.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.base.component.GamesRecyclerviewAdapter
import com.base.component.ui.gamescard.HomePageCardDTO
import com.base.component.ui.homepageslider.SliderItem
import com.base.core.extensions.afterTextChanged
import com.base.core.extensions.setup
import com.base.core.helpers.LocalPrefManager
import com.base.core.networking.DataFetchResult
import com.base.data.response.GamesListResponse
import com.base.games.R
import com.base.games.ui.base.fragment.BaseViewModelFragment
import com.base.games.ui.common.navigation.NavigationHelper
import com.base.games.ui.pages.home.viewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/

class HomeFragment : BaseViewModelFragment<HomeFragmentViewModel>() {

    override val viewModelClass = HomeFragmentViewModel::class.java
    override val layoutViewRes = R.layout.fragment_home

    @Inject
    lateinit var adapter: GamesRecyclerviewAdapter

    @Inject
    lateinit var localPrefManager: LocalPrefManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_page_recyclerview.setup(adapter = adapter.getAdapter())
        bindObserver()
        adapter.getAdapter().itemClickListener = { _item, _position ->
            val navigationHelper = NavigationHelper(requireActivity())
            when (_item) {
                is HomePageCardDTO -> navigationHelper.navigate(_item)
                is SliderItem -> navigationHelper.navigate(_item)
            }
        }

        et_search.afterTextChanged {
            if (et_search.text.length >= 3) {
                viewModel.liveFilterList.value.let {
                    var filter = it?.filter {
                        it?.name?.toLowerCase()!!.contains(et_search.text.toString().toLowerCase())
                    }
                    if (filter!!.isEmpty()) {
                        adapter.getAdapter().updateAllItems(viewModel.noDataFound())
                    } else {
                        adapter.getAdapter().updateAllItems(viewModel.change(filter!!))
                    }
                }
            } else if (et_search.text.isEmpty()) {
                viewModel.homePageList.clear()
                viewModel.getGamesList()

            }
        }
    }

    private fun bindObserver() {
        viewModel.getGamesList()
        viewModel.gamesListPublishSubject.subscribe {
            adapter.getAdapter().updateAllItems(it)
        }
        viewModel.gamesListDataResult.observe(
            viewLifecycleOwner,
            Observer<DataFetchResult<GamesListResponse>> {
                when (it) {
                    is DataFetchResult.Progress -> {
                    }
                    is DataFetchResult.Failure -> {
                    }
                    is DataFetchResult.Success -> {
                    }
                }
            })
    }

}